<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="admin_head.jsp"%>
<html>
<head>
    <title>修改用户密码</title>
    <!--css-->
    <link href="../static/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8"/>
    <link href="../static/css/style.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8" />
    <!--css-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!--js-->
    <script src="../static/js/jquery.min.js"></script>
    <script src="../static/js/bootstrap.min.js"></script>
    <!--js-->
</head>
<body>
<!--banner-->
<div class="banner1">
    <div class="container">
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><a href="/admin/lookUser">用户管理</a> / <span>修改用户密码</span></h3>
    </div>
</div>
<!--banner-->
<form action="/admin/adchange" method="post">
    <div class="authority">
        <!--banner-->
        <div class="banner-info">
            <div class="col-md-12 header-right">
                <h1>Hi !</h1>
                <h6>修改密码</h6>
                <ul class="address">

                    <li>
                        <ul class="address-text">
                            <li><b>姓名:</b></li>
                            <li><b>${user}</b></li>
                        </ul>
                    </li>
                    <li>
                        <ul class="address-text">
                            <li><b>修改密码:</b></li>
                            <li>
                                <input type="password" class="authority_select" id="password1">
                            </li>
                        </ul>
                    </li>
                    <li>
                        <ul class="address-text">
                            <li><b>再次输入密码:</b></li>
                            <li>
                                <input type="password" class="authority_select" id="password2" name="password"  onkeyup="validate()">
                            </li>
                        </ul>
                       <p class="cp_tishi">
                       <span id="tishi"></span>
                       </p>
                    </li>
                    <div class="authority_submit">
                        <input type="submit" value="确定">
                    </div>
                </ul>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</form>
<jsp:include page="footer.jsp"/>
<script>
    function validate() {
        var pwd1 = document.getElementById("password1").value;
        var pwd2 = document.getElementById("password2").value;

        <!-- 对比两次输入的密码 -->
        if(pwd1 == pwd2)
        {
            document.getElementById("tishi").style.display="none";
        }
        else {
            document.getElementById("tishi").style.display="";
            document.getElementById("tishi").innerHTML="<p style='color: red'>两次密码不相同</p>";
        }
    }
</script>
</body>
</html>
