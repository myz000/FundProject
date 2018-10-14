<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/20
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="admin_head.jsp"%>
<html>
<head>
    <title>权限设置</title>
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
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><a href="/admin_lookUser">用户管理</a> / <span>权限设置</span></h3>
    </div>
</div>
<!--banner-->
<form action="authority" method="post">
<div class="authority">
    <!--banner-->
    <div class="banner-info">
        <div class="col-md-12 header-right">
            <h1>Hi !</h1>
            <h6>个人权限</h6>
            <ul class="address">

                <li>
                    <ul class="address-text">
                        <li><b>姓名:</b></li>
                        <li><b>${user}</b></li>
                    </ul>
                </li>
                <li>
                    <ul class="address-text">
                        <li><b>角色:</b></li>
                        <li>
                            <select class="authority_select">
                                <option>用户</option>
                                <option>经理</option>
                            </select>
                        </li>
                    </ul>
                </li>
                <li>
                    <ul class="address-text">
                        <li><b>状态:</b></li>
                        <li>
                            <select class="authority_select" name="option">
                                <option value="1">正常</option>
                                <option value="2">冻结</option>
                            </select>
                        </li>
                    </ul>
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
</body>
</html>
