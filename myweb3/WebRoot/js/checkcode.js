function changeCode(){
    var codeImg = document.getElementById("verifyCode");
    codeImg.src="CreateVerfifyCodeImage?t="+Math.random();
}
