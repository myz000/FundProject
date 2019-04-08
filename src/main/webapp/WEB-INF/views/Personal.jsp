<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/22
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="head.jsp" %>
<html>
<head>
    <title>Title</title>
    <!--css-->
    <link href="../static/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8"/>
    <link href="../static/css/style.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8"/>
    <!--css-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!--js-->
    <script src="../static/js/jquery.min.js"></script>
    <script src="../static/js/bootstrap.min.js"></script>
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
            <div class="personal">

                <div class="personal-name"><b>姓名：</b></div>
                <div class="personal-info"><input type="text" value="${User.username}" readonly style="border:none">
                </div>

            </div>
            <div class="personal">

                <div class="personal-name"><b>电话号码：</b></div>
                <div class="personal-info"><input type="text" value="${User.telephone}" readonly
                                                  style="border:none"></div>

            </div>
            <div class="personal">
                <div class="personal-name"><b>邮箱：</b></div>
                <div class="personal-info"><input type="text" value="${User.email}" readonly style="border:none">
                </div>
            </div>
            <div class="personal">
                <div class="personal-name"><b>角色：</b></div>
                <div class="personal-info"><input type="text" value="${User.role}" readonly style="border:none">
                </div>
            </div>
            <div class="personal">
                <div class="personal-name"><b>性别：</b></div>
                <div class="personal-info"><input type="text" value="${User.sex}" readonly style="border:none">
                </div>
            </div>
            <div class="personal">
                <div class="personal-name"><b>身份证号：</b></div>
                <div class="personal-info"><input type="text" value="${User.id}" readonly style="border:none"></div>
            </div>
        </ul>
    </div>
    <div class="clearfix"></div>
</div>
<div class="sub-class-personal">
    <div class="sub-class-1">
        <a href="#" data-toggle="modal" data-target="#myModal3">修改个人信息</a>
        &nbsp&nbsp&nbsp
        <a href="#" data-toggle="modal" data-target="#myModal2">修改密码</a>
    </div>
</div>

<jsp:include page="footer.jsp"/>

<!--ChangePassword-->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content modal-info">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body real-spa">
                <div class="login-grids">
                    <div class="login">
                        <div class="changeInformation">
                            <form action="#" method="post" id="cpForm" name="cpForm">
                                <h3>修改密码</h3>
                                <label id="cpMsg" style="color:red;"></label><br>
                                <br>
                                请输入旧密码：
                                <input type="password" name="oldPassword">
                                <br>
                                请输入新密码：
                                <input type="password" name="Password1">
                                <br>
                                请再次输入新密码：
                                <input type="password" name="Password2">
                                <input type="button" value="确认" onclick="cp()">
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body real-spa">
                <div class="login-grids">
                    <div class="login">
                        <div class="changeInformation">
                            <form action="#" method="post" id="ciForm" name="ciForm">
                                <h3>修改个人信息 </h3>
                                <label id="ciMsg" style="color:red;"></label><br>
                                姓名:<input type="text" name="Name" value="${User.username}">
                                电话号码：<input type="text" name="Phone" value="${User.telephone}">
                                邮箱：<input type="text" name="Email" value="${User.email}">
                                身份证号：<input type="text" name="Id" value="${User.id}">
                                性别：
                                <div class="sexDiv">
                                    <input type="radio" name="Sex" value="男"
                                           <c:if test="${User.sex== '男'}">checked="checked"</c:if>>男
                                    <input type="radio" name="Sex" value="女"
                                           <c:if test="${User.sex== '女'}">checked="checked"</c:if>>女
                                </div>
                                <br>
                                <input type="button" value="确认" onclick="ci()">
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
<script>
    function cp() {
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: 'json',//预期服务器返回的数据类型
            url: "/user/change-password",//url
            data: $('#cpForm').serialize(),
            success: function (result) {
                if (result.hasOwnProperty("state")) {
                    alert(result.msg);
                    location.reload();

                } else {
                    var label = document.getElementById("cpMsg");
                    label.innerText = result.msg;
                }
            },
            error: function (msg) {
                console.log("msg:------------------ " + msg);
            },
        });

    }

    function ci() {
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: 'json',//预期服务器返回的数据类型
            url: "/user/change-info",//url
            data: $('#ciForm').serialize(),
            success: function (result) {
                if (result.hasOwnProperty("state")) {

                    alert(result.msg);
                    location.reload();
                } else {
                    var label = document.getElementById("ciMsg");
                    label.innerText = result.msg;
                }
            },
            error: function (msg) {
                console.log("msg:------------------ " + msg);
            },
        });
    }


</script>


</body>
</html>
