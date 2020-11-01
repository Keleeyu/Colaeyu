<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
	<meta http-equiv="refresh" content='10;url=${pageContext.request.contextPath }/Login.html'>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/error_style.css">
	<title>ERROR</title>
		<script>
			var second = 10;
			var timer;
			function changetime(){
				second--;
				if(second>-1){
					document.getElementById("second").innerHTML=second;
					timer = setTimeout('changetime()',1000);
				}
				else{
					clearTimeout(timer);
				}
			}
		</script>
</head>
<body onload="changetime()">
	<div class="background">
		<div class="errormsg">
			<h1>${info}</h1>
			<label id="second" class="num">10</label>
			<h2 class="msgn">秒后自动返回登录页面</h2>
			<h2 class="msgm">不能跳转，请</h2>
			<h2 class="click" onclick="location='<%=request.getContextPath()%>/Login.html'">点击这儿</h2>
		</div>
	</div>
</body>
</html>