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
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><a href="/admin/lookUser">用户管理</a> / <span>权限设置</span></h3>
    </div>
</div>
<!--banner-->
<form action="/admin/authority" method="post">
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
                        <li><b><input type="text" value="${user.username}" name="username" readonly style="border:none;"></b></li>
                    </ul>
                </li>
                <li>
                    <ul class="address-text">
                        <li><b>角色:</b></li>
                        <li>
                            <input type="radio" name="Role" value="user" <c:if test="${user.role== 'user'}">checked="checked"</c:if>>用户
                            <input type="radio" name="Role" value="manager" <c:if test="${user.role== 'manager'}">checked="checked"</c:if>>经理
                        </li>
                    </ul>
                </li>
                <li>
                    <ul class="address-text">
                        <li><b>状态:</b></li>
                        <li>
                            <input type="radio" name="State" value="0" <c:if test="${user.state== 0}">checked="checked"</c:if>>待审核
                            <input type="radio" name="State" value="1" <c:if test="${user.state== 1}">checked="checked"</c:if>>正常
                            <input type="radio" name="State" value="2" <c:if test="${user.state== 2}">checked="checked"</c:if>>冻结

                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="authority_submit">
            <input type="submit" value="确定">
        </div>
        <div class="clearfix"> </div>
    </div>
</div>
</form>
<jsp:include page="footer.jsp"/>
</body>
</html>
