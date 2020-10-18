function fillProvince(){
    $.ajax({
        type: "post",
        url: "qureyProvinceCity.do",
        data:{},
        dataType:"json",
        success: function(response){
            var provinceElement = document.getElementById("province");

            provinceElement.options.length = 0;

            provinceElement.add(new Option("请选择省份",""));

            for(index = 0;index < response.length;index++){
                provinceElement.add(new Option(response[index].provinceName,response[index].provinceCode));
            }
        }
    });
}
var userName_correct = false;
var chrName_correct = false;
var mail_correct = false;
var province_correct = false;
var city_correct = false;
var password_correct = false;
var repassword_correct = false;

$(document).ready(function(){
    fillProvince();

    $("#province").change(function(e){
        $("#city").empty();
        $("#city").append($("<option>").val("").text("请选择城市"));
        if($(this).val()==""){
            $("#provinceError").css("color","#c00202");
            $("#provinceError").text("必须选择省份!");
            return;
        }
        province_correct = true;
        $("#provinceError").text("");
        var provinceCode=$("#province").val();
        $.ajax({
            type: "post",
            url: "qureyProvinceCity.do",
            data: { provinceCode: provinceCode},
            dataType: "json",
            success: function(response){
                for(index = 0;index < response.length;index++){
                    var option = $("<option>").val(response[index].cityCode).text(response[index].cityName);
                    $("#city").append(option);
                }
            }
        });
    });

    $("#province").blur(function(e) {
        if ($(this).val() == "") {
            $("#provinceError").css("color", " #c00202");
            $("#provinceError").text("省份不能为空");
            province_correct = false;
        } else {
            $("#provinceError").text("");
            province_correct = true;
        }
    });

    $("#city").blur(function(e) {
        if ($(this).val() == "") {
            $("#cityError").css("color", " #c00202");
            $("#cityError").text("城市不能为空");
            city_correct = false;
        } else {
            $("#cityError").text("");
            city_correct = true;
        }
    });
    $('#userName').blur(function(event) {
        if ($(this).val() == "") {
            $("#userNameError").css("color", " #c00202");
            $("#userNameError").text("用户名不能为空");
            return;
        }
        if (/^[a-zA-Z][a-zA-Z\d]{3,14}$/.test(this.value) == false) {
            $("#userNameError").css("color", " #c00202");
            $("#userNameError").text("用户名只能使用英文字母和数字，以字母开头，长度为4到15个字符");
            return;
        }
        $.ajax({
            type: "post",
            url: "checkExist.do",
            data: { userName: $(this).val() },
            dataType: "json",
            success: function(response) {
                if (response.checkuser == 0) {
                    $("#userNameError").css("color", "green");
                    $("#userNameError").text("用户名可以使用");
                    userName_correct = true;
                } else {
                    $("#userNameError").css("color", "#c00202");
                    $("#userNameError").text("用户名已存在");
                    userName_correct = false;
                }
            }
        });
        
    });
    $('#chrName').blur(function(event) {
        if ($(this).val() == "") {
            $("#chrNameError").css("color", " #c00202");
            $("#chrNameError").text("姓名不能为空");
            chrName_correct = false;
            return;
        }
        if (/^[\u4e00-\u9fa5]{2,4}$/.test(this.value) == false) {
            $("#chrNameError").css("color", " #c00202");
            $("#chrNameError").text("真实姓名只能是2-4长度的中文");
            chrName_correct = false;
            return;
        } else {
            chrName_correct = true;
            $("#chrNameError").text("");
        }
    });

    $("#email").blur(function(event) {
        if ($(this).val() == "") {
            $("#emailError").css("color", " #c00202");
            $("#emailError").text("邮箱不能为空");
            mail_correct = false;
            return;
        }
        if (/^[a-zA-Z0-9]+([._\\]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/.test(this.value) == false) {
            $("#emailError").css("color", " #c00202");
            $("#emailError").text("邮箱地址格式不正确");
            mail_correct = false;
            return;
        }

        $.ajax({
            type: "post",
            url: "checkExist.do",
            data: { email: $(this).val() },
            dataType: "json",
            success: function(response) {
                if (response.checkemail == 0) {
                    $("#emailError").css("color", "green");
                    $("#emailError").text("邮箱可以使用");
                    mail_correct = true;
                } else {
                    $("#emailError").css("color", "#c00202");
                    $("#emailError").text("此邮箱已被注册");
                    mail_correct = false;
                }
            }
        });
    });
    $("#password").blur(function() {
        var password_min_length = 4
        if ($("#password").val().length >= password_min_length) {
            $("#passwordError").css("color", "green");
            $("#passwordError").text("密码可以使用");
            password_correct = true;
        } else {
            $("#passwordError").css("color", "#c00202");
            $("#passwordError").text("密码最小长度为4");
            password_correct = false;
        }
    });
    $("#repassword").blur(function() {
        var password_min_length = 4;
        if ($("#password").val() == $("#repassword").val() && $("#password").val().length >= password_min_length) {
            $("#repasswordError").css("color", "green");
            $("#repasswordError").text("密码可以使用");
            repassword_correct = true;
        } else {
            $("#repasswordError").css("color", "#c00202");
            $("#repasswordError").text("密码不一致或长度不够");
            repassword_correct = false;

        }
    });


});

function AjaxRegister(){
    if(!userName_correct || !chrName_correct || !mail_correct || !province_correct || !city_correct || !password_correct || !repassword_correct){
        $("#userName").blur();
        $('#chrName').blur();
        $("#email").blur();
        $("#password").blur();
        $("#repassword").blur();
        $("#province").blur();
        $("#city").blur();
        return;
    }
    $.ajax({
        type: "post",
        url: "register.do",
        data:{ userName:$("#userName").val(),chrName:$("#chrName").val(),password:$("#password").val(),province:$("#province").val(),city:$("#city").val(),email:$("#email").val() },
        dataType:"json",
        success: function(response){
            if (response.code == 0) {
                alert("注册成功即将跳转到登录页面");
                window.location.href = "Login.html";
            }
        }
    });
}

