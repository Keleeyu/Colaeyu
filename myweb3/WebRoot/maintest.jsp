<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Menu</title>
        <link rel="stylesheet" href="css/maintest_style.css">
        <script>
            function exitbtn(){
            	location.href="logout.do"
            }
        </script>
    </head>
    <body>
        <div class="navbar">
            <a href="#" class="logo">Hello</a>
            <ul class="nav">
                <li><a href="#home">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#download">Download</a></li>
                <li><a href="#services">Services</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
            <div class="userinfo">
                <label>欢迎您:</label>
                <label>${currentUser.chrName}</label>
                <label class="exit" onclick=exitbtn()>【安全退出】</label>
            </div>
        </div>
        <div class="banner-area" id="home">
        </div>
        <div class="about-area" id="about">
            <div class="text-part">
                <h1>About</h1>
                <p>
                    Governor's Island is a national historic landmark district long used for military purposes.
                    Now known as "New York's shared space for art and play," the island, which lies between Manhattan 
                    and Brooklyn in Upper New York Bay, is closed to cars but open to summer tourists who flock for
                    festivals, picnics, adventures, as well as these "legal graffiti" sessions.
                </p>
            </div>
        </div>
        <div class="download-area" id="download">
            <div class="text-part">
                <h1>Download</h1>
                <p>
                    Governor's Island is a national historic landmark district long used for military purposes.
                    Now known as "New York's shared space for art and play," the island, which lies between Manhattan 
                    and Brooklyn in Upper New York Bay, is closed to cars but open to summer tourists who flock for
                    festivals, picnics, adventures, as well as these "legal graffiti" sessions.
                </p>
                <p>下载Download:</p>
                <label class="downloadhere" onclick="location='<%=request.getContextPath()%>/getDownloadList.do'">Click Here</label>
            </div>
        </div>
        <div class="service-area" id="services">
            <div class="text-part">
                <h1>Service</h1>
                <p>
                    Governor's Island is a national historic landmark district long used for military purposes.
                    Now known as "New York's shared space for art and play," the island, which lies between Manhattan 
                    and Brooklyn in Upper New York Bay, is closed to cars but open to summer tourists who flock for
                    festivals, picnics, adventures, as well as these "legal graffiti" sessions.
                </p>
            </div>
        </div>
        <div class="contact-area" id="contact">
            <div class="text-part">
                <h1>Contact</h1>
                <p>
                    Governor's Island is a national historic landmark district long used for military purposes.
                    Now known as "New York's shared space for art and play," the island, which lies between Manhattan 
                    and Brooklyn in Upper New York Bay, is closed to cars but open to summer tourists who flock for
                    festivals, picnics, adventures, as well as these "legal graffiti" sessions.
                </p>
            </div>
        </div>
    </body>
</html>