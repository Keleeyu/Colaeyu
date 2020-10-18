var xmlHttp;

function createXmlHttp(){
    if(window.XMLHttpRequest){
        xmlHttp = new XMLHttpRequest();
    }else{
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function ajaxCheckLogin(){
    var userName = document.getElementById("userName").value;
    var password = document.getElementById("password").value;
    var vcode = document.getElementById("vcode").value;
    var autoLogin = document.getElementById("autoLogin").value;
    createXmlHttp();
    xmlHttp.open("post","ajaxLoginCheck.do",true);
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xmlHttp.send("userName=" + userName + "&password=" + password + "&vcode=" + vcode + "&autoLogin=" + autoLogin);
    xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
            var response = xmlHttp.responseText;
            var json = JSON.parse(response);
            if(json.code == 0){
                window.location.href = "maintest.jsp";
            }else{
                document.getElementById("checkError").innerText = json.info;
            }
        }
    }
}

function jqAjaxCheckLogin(){
    /*if($("#check_box").attr("checked")){
        $(this).val() = "yes";
    }
    flag = "yes";*/
    test();
    $.ajax({
        type: "post",
        url: "ajaxLoginCheck.do",
        data: { userName:$("#userName").val(),password:$("#password").val(),flag:$("#flag").val(),vcode:$("#vcode").val() },
        dataType: "json",
        success: function(response) {
            if(response.code == 0)  {
                window.location.href = "maintest.jsp";
            }else{
                $("#checkError").text(response.info);
            }
        }
    });
}

function test(){
    var codeImg = document.getElementById("verifyCode");
    codeImg.src="CreateVerfifyCodeImage?t="+Math.random();
}