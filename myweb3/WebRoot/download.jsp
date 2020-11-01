<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Download</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/download_style.css">
        <script>
            function getvalue(c){
                location.href="download.do?Id=" + c;
            }
        </script>
        <script src="<%=request.getContextPath()%>/js/downloadstar.js">
        </script>
    </head>
    <body onload="starview('${file1.star}','${file2.star}','${file3.star}')">
        <div class="bar">
            <a class="logo">Hello</a>
        </div>
        <div class="border-area">
            <div class="download-area">
                 <img src="${file1.image}" width="50px" class="iconimg">
                 <div class="appwrap">
                    <h2 class="icontext">${file1.name}</h2>
                    <div class="info">
                        <label>大小: ${file1.size}KB </label>
                        <label>星级: </label>
                        <img id="1star1" width="20px">
                        <img id="1star2" width="20px">
                        <img id="1star3" width="20px">
                        <img id="1star4" width="20px">
                        <img id="1star5" width="20px">
                        <button id="button" type="submit" onclick=getvalue(1)>立即下载</button>
                    </div>
                    <p>${file1.description}</p> 
                 </div>
            </div>
            <div class="download-area">
                <img src="${file2.image}" width="50px" class="iconimg">
                <div class="appwrap">
                   <h2 class="icontext">${file2.name}</h2>
                   <div class="info">
                        <label>大小: ${file2.size}KB </label>
                        <label>星级: </label>
                        <img id="2star1" width="20px">
                        <img id="2star2" width="20px">
                        <img id="2star3" width="20px">
                        <img id="2star4" width="20px">
                        <img id="2star5" width="20px">
                        <button id="button" type="submit" onclick=getvalue(2)>立即下载</button>
                    </div>
                   <p>${file2.description}</p> 
        
                </div>
           </div>
            <div class="download-area">
                <img src="${file3.image}" width="50px" class="iconimg">
                <div class="appwrap">
                <h2 class="icontext">${file3.name}</h2>
                    <div class="info">
                        <label>大小: ${file3.size}KB </label>
                        <label>星级: </label>
                        <img id="3star1" width="20px">
                        <img id="3star2" width="20px">
                        <img id="3star3" width="20px">
                        <img id="3star4" width="20px">
                        <img id="3star5" width="20px">
                        <button id="button" type="submit" onclick=getvalue(3)>立即下载</button>
                    </div>
               <p>${file3.description}</p> 
            </div>
       </div>
        </div>
    </body>
</html>