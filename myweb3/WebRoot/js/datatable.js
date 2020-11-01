function fillProvince(){
    $.ajax({
        type: "post",
        url: "qureyProvinceCity.do",
        data:{},
        dataType:"json",
        success: function(response){
            var provinceElement = document.getElementById("Inprovince");

            provinceElement.options.length = 0;

            provinceElement.add(new Option("请选择省份",""));

            for(index = 0;index < response.length;index++){
                provinceElement.add(new Option(response[index].provinceName,response[index].provinceCode));
            }
        }
    });
}

$(document).ready(function(){
    fillProvince();
    $("#Inprovince").change(function(e){
        $("#Incity").empty();
        $("#Incity").append($("<option>").val("").text("请选择城市"));
        if($(this).val()==""){
            $("#provinceError").css("color","#c00202");
            $("#provinceError").text("必须选择省份!");
            return;
        }
        province_correct = true;
        $("#provinceError").text("");
        var provinceCode=$("#Inprovince").val();
        $.ajax({
            type: "post",
            url: "qureyProvinceCity.do",
            data: { provinceCode: provinceCode},
            dataType: "json",
            success: function(response){
                for(index = 0;index < response.length;index++){
                    var option = $("<option>").val(response[index].cityCode).text(response[index].cityName);
                    $("#Incity").append(option);
                }
            }
        });
    });
    $.ajax({
        type: "post",
        url: "datatable.do",
        data:{ pageSize:$("#pageSize").val(),pageNumber:$("#pageNumber").html()},
        dataType:"json",
        success: function(response){
            var  rows = response.rows;
            total = response.total;
            pageCount = Math.ceil(total/$("#pageSize").val());
            $("#total").text(total);
            $("#pageCount").text(pageCount);
            $("tbody").empty();
            $.each(rows,function(index,row){
                var s = JSON.stringify(row);
                var str = "<tr data='"+ s + "'>";
                str = str + '<td><input type="checkbox" width="40" value=' + row.userName + '/></td>';
                str = str + '<td>' + row.userName + '</td>';
                str = str + '<td>' + row.chrName + '</td>';
                str = str + '<td>' + row.email + '</td>';
                str = str + '<td>' + row.province + '</td>';
                str = str + '<td>' + row.city + '</td>';
                str = str + '<td><a href="#" id="btnDel" value=' + row.userName + '>删除</a>';
                str = str + '<a href="#" id="btnUpdate">修改</a></td>';
                str = str + '</tr>';
                $("tbody").append(str);
            });
            $('tbody tr:even').addClass('tr_even');
            $('tbody tr:odd').addClass('tr_odd');
            $("tbody").on("mouseover","tr",function(){
                $(this).addClass('tr_hover');
            });
            
            $("tbody").on("mouseout","tr",function(){
                $(this).removeClass('tr_hover');
            });

            $("tbody").on("click","tr input:checkbox",function(){
                if(this.checked==true){
                    $(this).parents("tr").addClass('tr_select');
                }else{
                    $(this).parents("tr").removeClass('tr_select');
                }
            });

        }
    });
});

function searchinfo() {
    $.ajax({
        type: "post",
        url: "searchinfo.do",
        data:{ userName:$("#userName").val(),chrName:$("#chrName").val(),email:$("#email").val(),province:$("#provinceName").val() },
        dataType:"json",
        success:function(response) {
            $("tbody").empty();
            var  rows = response.rows;
            $.each(rows,function(index,row){
                var s = JSON.stringify(row);
                var str = "<tr data='"+ s + "'>";
                str = str + '<td><input type="checkbox" width="40" value=' + row.userName + '/></td>';
                str = str + '<td>' + row.userName + '</td>';
                str = str + '<td>' + row.chrName + '</td>';
                str = str + '<td>' + row.email + '</td>';
                str = str + '<td>' + row.province + '</td>';
                str = str + '<td>' + row.city + '</td>';
                str = str + '<td><a href="#" id="btnDel" value=' + row.userName + '>删除</a>';
                str = str + '<a href="#" id="btnUpdate">修改</a></td>';
                str = str + '</tr>';
                $("tbody").append(str);
            });
            $('tbody tr:even').addClass('tr_even');
            $('tbody tr:odd').addClass('tr_odd');
        }
    });

}

function deleteUser(){
    var len = $('tbody tr input:checkbox:checked').length;
    if(len == 0){
        alert("至少需要选择一项!");
        return;
    }
    var vals = [];
    $('tbody tr input:checkbox:checked').each(function(index,item){
        vals.push($(this).val());
    });
    $.ajax({
        type:"post",
        url:"deleteUser.do",
        data:{ ids:vals.join("")},
        dataType:"json",
        success:function(response){
            if(response.code==0){
                reload();
            }
        }
    });
}

function reload(){
    $.ajax({
        type: "post",
        url: "datatable.do",
        data:{ pageSize:$("#pageSize").val(),pageNumber:$("#pageNumber").html() },
        dataType:"json",
        success:function(response){
            $("tbody").empty();
            var  rows = response.rows;
            total = response.total;
            pageCount = Math.ceil(total/$("#pageSize").val());
            $("#total").text(total);
            $("#pageCount").text(pageCount);
            $("tbody").empty();
            $.each(rows,function(index,row){
                var s = JSON.stringify(row);
                var str = "<tr data='"+ s + "'>";
                str = str + '<td><input type="checkbox" width="40" value=' + row.userName + '/></td>';
                str = str + '<td>' + row.userName + '</td>';
                str = str + '<td>' + row.chrName + '</td>';
                str = str + '<td>' + row.email + '</td>';
                str = str + '<td>' + row.province + '</td>';
                str = str + '<td>' + row.city + '</td>';
                str = str + '<td><a href="#" id="btnDel" value=' + row.userName + '>删除</a>';
                str = str + '<a href="#" id="btnUpdate">修改</a></td>';
                str = str + '</tr>';
                $("tbody").append(str);
            });
            $('tbody tr:even').addClass('tr_even');
            $('tbody tr:odd').addClass('tr_odd'); 
        }
    });
}


function ShowDiv(show_div,bg_div){
    document.getElementById(show_div).style.display = "block";
    document.getElementById(bg_div).style.display = "block";

    var windowHeight = $(window).height();
    var windowWidth = $(window).width();
    var popupHeight = $("#"+show_div).height();
    var popupWeight = $("#"+show_div).width();
    var posiTop = (windowHeight - popupHeight)/2;
    var posiLeft = (windowWidth - popupWeight)/2;

    $("#"+show_div).css({"left":posiLeft+"px","top":posiTop+"px","display":"block"});
}

function CloseDiv(show_div,bg_div){
    document.getElementById(show_div).style.display = "none";
    document.getElementById(bg_div).style.display = "none";
    $("#repasswordError").text("");
    $("#provinceError").text("");
    $("#cityError").text("");
    $("#userNameError").text("");
    $("#chrNameError").text("");
    $("#emailError").text("");
}


var userName_correct = false;
var chrName_correct = false;
var mail_correct = false;
var province_correct = false;
var city_correct = false;
var password_correct = false;
var repassword_correct = false;

function Updata(){
    $.ajax({
        type: "post",
        url: "AjaxUpdata.do",
        data:{ InuserName:$("#InuserName").val(),InchrName:$("#InchrName").val(),Inprovince:$("#Inprovince").val(),Incity:$("#Incity").val(),Inpassword:$("#password").val(),Inemail:$("#Inemail").val() },
        dataType:"json",
        success: function(response){
            if (response.code == 0) {
                alert("修改成功!");
                CloseDiv('MyDiv','fade');
                $("#userNameError").text("");
                reload();
            }
            else{
                if(response.checkuser == 0){
                    $("#userNameError").text("");
                }else{
                    $("#userNameError").css("color", "#c00202");
                    $("#userNameError").text("没有改用户");
                }
                if(response.checkprovince == 0){
                    $("#provinceError").text("");
                }else{
                    $("#provinceError").css("color", "#c00202");
                    $("#provinceError").text("请选择省份");
                }
            }
        }
    });
}

function changetable(){
    reload(); 
}


function nextpage(){
    var pageNumber = document.getElementById('pageNumber').innerText;
    if(pageNumber ++ < document.getElementById('pageCount').innerText){
        document.getElementById('pageNumber').innerText = pageNumber;
        reload();
    }
}


function prepage(){
    var pageNumber = document.getElementById('pageNumber').innerText;

    if(pageNumber -- > 1)
    {
        document.getElementById('pageNumber').innerText = pageNumber;
        reload();
    }
}

function fristpage(){
    document.getElementById('pageNumber').innerText = 1;
    reload();
}

function lastpage(){
    document.getElementById('pageNumber').innerText = document.getElementById('pageCount').innerText;
    reload();
}



