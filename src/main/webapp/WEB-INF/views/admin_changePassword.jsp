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
<form id="cpForm" name="cpForm">
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
                            <li><b><input type="text" value="${user.username}" name="username" readonly style="border:none;"></b></li>
                        </ul>
                    </li>
                    <li>
                        <ul class="address-text">
                            <li><b>修改密码:</b></li>
                            <li>
                                <input type="password"  id="password1" name="password1">
                            </li>
                        </ul>
                    </li>
                    <li>
                        <ul class="address-text">
                            <li><b>再次输入密码:</b></li>
                            <li>
                                <input type="password"  id="password2" name="password2">
                            </li>
                        </ul>
                       <p class="cp_tishi">
                       <span id="tishi" style="color:red;"></span>
                       </p>
                    </li>

                </ul>

            </div>
            <div class="authority_submit">
                <input type="button" value="确定" onclick="validate()">
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</form>
<jsp:include page="footer.jsp"/>
<script>
    function validate() {
          $.ajax({
                            //几个参数需要注意一下
                                type: "POST",//方法类型
                                dataType: 'json',//预期服务器返回的数据类型
                                url: "/admin/changePassword" ,//url
                                data: $('#cpForm').serialize(),
                                success: function (result) {
                                    if(result.hasOwnProperty("state")){
                                        alert(result.msg);
                                        location.reload();
                                    }
                                    else{
                                        var label=document.getElementById("tishi");
                                        label.innerText=result.msg;
                                    }
                                }
                            });
    }
</script>
</body>
</html>
