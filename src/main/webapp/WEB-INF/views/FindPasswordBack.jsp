<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/22
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="head.jsp"%>
<html>
<head>
    <title>Title</title>
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
    <script src="../static/js/PhoneVerify.js"></script>
    <script src="../static/js/Cookie.js"></script>
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
        <h3>通过手机号找回</h3>
        <form name="findPasswordInform" id="findPasswordInform">
        手机号：<input type="text" name="Phone" id="Phone"><br>
        验证码：<input type="text" name="verify" id="verify"><br>
        <label name="r-inform" readonly="true" id="r-inform"></label>
        <input type="button" value="获取验证码" onclick="phoneVerify()" id="VerifyButton" name="VerifyButton">
        <input type="button" value="找回密码" onclick="verifyUser()" >
        </form>
        </ul>

    </div>
    <div class="clearfix"> </div>
</div>
<jsp:include page="footer.jsp"/>

<script>
 function phoneVerify(){
                         $.ajax({
                         //几个参数需要注意一下
                         type: "POST",//方法类型
                         dataType: 'json',//预期服务器返回的数据类型
                         url: "/FindPasswordPhoneVerify" ,//url
                         data: $('#findPasswordInform').serialize(),
                         success: function (result) {
                                console.log("log"+result);//打印服务端返回的数据(调试用)
                                if (result.error_code=="0") {
                                var label=document.getElementById("r-inform");
                                label.innerText="";
                                var button=document.findPasswordInform.VerifyButton;
                                Verify(button,"secondsremained");
                           }else{
                                 var label=document.getElementById("r-inform");
                                 label.innerText=result.reason;
                           }
                           },
                           error : function(msg) {
                           console.log("msg:------------------ "+msg);
                           },
                           });
                           }


function verifyUser() {
                    $.ajax({
                    //几个参数需要注意一下
                        type: "POST",//方法类型
                        dataType: 'json',//预期服务器返回的数据类型
                        url: "/toFindPassword" ,//url
                        data: $('#findPasswordInform').serialize(),
                        success: function (result) {
                            console.log("log"+result);//打印服务端返回的数据(调试用)
                            if (result.status==200) {
                                 window.location.href='/changePassword';
                            }
                            else {
                               var label=document.getElementById("r-inform");
                               label.innerText=result.msg;
                            }
                            ;
                        },
                        error : function(msg) {
                             console.log("msg:------------------ "+msg);
                            		  	  },
                    });
                }
</script>

</body>
</html>
