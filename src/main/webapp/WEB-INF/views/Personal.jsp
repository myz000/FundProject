<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/22
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!--css-->
    <link href="./css/bootstrap.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8"/>
    <link href="./css/style.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8" />
    <!--css-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!--js-->
    <script src="./js/jquery.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <!--js-->
</head>
<body>
<!--banner-->
<div class="banner1">
    <div class="container">
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><a href="index.html">主页</a> / <span>个人中心</span></h3>
    </div>
</div>

<!--banner-->
<div class="banner-info">
    <div class="col-md-12 header-right">
        <h1>Hi !</h1>
        <h6>个人信息</h6>
        <ul class="address">

            <li>
                <ul class="address-text">
                    <li><b>姓名</b></li>
                    <li>I'M ROB LEE</li>
                </ul>
            </li>
            <li>
                <ul class="address-text">
                    <li><b>电话号码</b></li>
                    <li>+00 111 222 3333</li>
                </ul>
            </li>
            <li>
                <ul class="address-text">
                    <li><b>邮箱</b></li>
                    <li><a href="mailto:example@mail.com"> mail@example.com</a></li>
                </ul>
            </li>
            <li>
            <ul class="address-text">
                <li><b>角色</b></li>
                <li>普通用户</li>
                </ul>


        </ul>
    </div>
    <div class="clearfix"> </div>
</div>
<div class="sub-class-personal">
    <div class="sub-class-1">
        <a href="#" data-toggle="modal" data-target="#myModal3">修改个人信息</a>
        &nbsp&nbsp&nbsp
        <a href="#" data-toggle="modal" data-target="#myModal2">修改密码</a>
    </div>
</div>





<!--footer-->
<div class="footer-section">
    <div class="container">
        <div class="footer-grids">
            <div class="col-md-3 footer-grid">
                <h4>About </h4>
                <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>
            </div>
            <div class="col-md-3 footer-grid">
                <h4>Property Types</h4>
                <ul>
                    <li>Lorem Post With Image Format</li>
                    <li>Example Video Blog Post</li>
                    <li>Example Post With Gallery Post</li>
                    <li>Example Video Blog Post</li>
                    <li>Lorem Post With Image Format</li>
                    <li>Example Video Blog Post</li>
                </ul>
            </div>
            <div class="col-md-3 footer-grid">
                <h4>Useful links</h4>
                <ul>
                    <li><a href="index.html">Home</a></li>
                    <li><a href="about.html">About</a></li>
                    <li><a href="services.html">Services</a></li>
                    <li><a href="contact.html">Contact</a></li>
                    <li><a href="codes.html">Codes</a></li>
                    <li><a href="projects.html">Projects</a></li>
                </ul>
            </div>
            <div class="col-md-3 footer-grid">
                <h4>Contacts</h4>
                <ul>
                    <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>1 234 567 890</li>
                    <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a href="mailto:example@mail.com"> example@mail.com</a></li>
                    <li><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>1398 W El Camino Real</li>
                    <li><i class="glyphicon glyphicon-time" aria-hidden="true"></i>Mon-Sat 8:00 am to 8:00 pm</li>
                </ul>
            </div>
            <div class="clearfix"> </div>
        </div>

    </div>
</div>
<!--footer-->
<!--copy-->
<div class="copy-section">
    <div class="container">
        <p>&copy; 2016 Home Plat. All rights reserved | Design by <a href="http://www.smallseashell.com">.小贝壳网站模板.</a></p>
    </div>
</div>
<!--copy-->



<!--ChangePassword-->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content modal-info">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body real-spa">
                <div class="login-grids">
                    <div class="login">
                        <div class="changeInformation">
                            <form action="#" method="post">
                                <h3>修改密码</h3>
                                <br>
                               请输入密码：
                                <input type="password" name="Password" value="Password1" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}" required="">
                               <br><br>
                                请再次输入密码：
                                <input type="password" name="Password" value="Password2" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}" required="">
                                <input type="submit" value="确认" >
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--ChangePassword-->
<!--ChangeInformation-->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content modal-info">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body real-spa">
                <div class="login-grids">
                    <div class="login">
                        <div class="changeInformation">
                            <form action="#" method="post">
                                <h3>修改个人信息 </h3>
                                姓名:<input type="text" name="Name">
                                电话号码：<input type="text" name="Phone">
                                邮箱：<input type="text" name="Email">
                                <input type="submit" value="确认" >
                            </form>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--Register-->


</body>
</html>
