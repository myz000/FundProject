
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/3
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
 <script src="../static/js/Cookie.js"></script>
 <script src="../static/js/PhoneVerify.js"></script>
 <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<!--header-->

            <div class="header-top">
                <div class="container">
                <div class="deatils">
                    <ul>
                       <c:choose>
                           <c:when test="${!(sessionScope.username eq null)}">
                               <li><i class="glyphicon glyphicon-user"></i><a>欢迎您，${sessionScope.username}!   </a></li>
                               <li><i class="glyphicon glyphicon-log-out"></i><a href="javascript:logout();">注销</a></li>
                           </c:when>
                          <c:otherwise>
                              <li><i class="glyphicon glyphicon-log-in" aria-hidden="true"></i><a href="#" data-toggle="modal" data-target="#myModal">登录   </a></li>
                              <li><i class="glyphicon glyphicon-lock" aria-hidden="true"></i><a href="#" data-toggle="modal" data-target="#myModal1" >注册</a></li>
                          </c:otherwise>
                       </c:choose>
                        </ul>
                </div>
                <div class="clearfix"></div>
                <div class="logo">
                    <h1><a href="Welcome.jsp">Financial Service<span>Your Fund Helper</span></a></h1>
                </div>
            </div>
    </div>

<!--signin-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content modal-info">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body real-spa">
                <div class="login-grids">
                    <div class="login">
                        <div class="login-right">
                            <form  method="post" name="loginForm" id="loginForm">
                                <h3>登录</h3>
                                <input type="text" name="Name"  placeholder="请输入用户名">
                                <input type="password" name="Password" placeholder="请输入密码">
                                <div class="informdiv">
                                <label name="inform" readonly="true" id="inform"></label>
                                </div>
                                <h4><a href="/findPasswordBack">忘记密码？</a></h4>
                                <div class="single-bottom">
                                    <input type="checkbox" name="Check" id="brand" value="1">
                                    <label for="brand"><span></span>保持登陆</label>
                                </div>
                                <input type="button" value="Sign In" onclick="login()">
                            </form>
                        </div>
                    </div>
                    <p>By logging in you agree to our <a href="#">Terms</a> and <a href="#">Conditions</a> and <a href="#">Privacy Policy</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
<!--signin-->

<!--Register-->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" name="myModal1">
    <div class="modal-dialog" role="document">
        <div class="modal-content modal-info">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body real-spa">
                <div class="login-grids">
                    <div class="login">
                        <div class="login-right">
                            <form  method="post" name="registerForm" id="registerForm" >
                                <h3>注册 </h3>
                                <input type="text" name="Name"  placeholder="用户名" id="name">
                                <input type="text" name="Phone"  placeholder="电话号码" id="tel">
                                <input type="text" name="Email"  placeholder="邮箱" id="email">
                                <input type="text" name="Id"  placeholder="身份证号" id="id">
                                <div class="sexDiv">
                                <input type="radio" name="Sex" value="男" checked>男
                                <input type="radio" name="Sex" value="女">女
                                </div>
                                <input type="password" name="Password1"  placeholder="密码" id="password1">
                                <input type="password" name="Password2"  placeholder="再次输入密码" id="password2">
                                 <div class="VerificationDiv">
                                 <input type="text" name="Verification"  placeholder="验证码" id="verification">
                                 <input type="button" value="获取验证码" onclick="phoneVerify()" id="VerifyButton" name="VerifyButton">
                                 </div>
                                <div class="informdiv">
                                <label name="r-inform" readonly="true" id="r-inform"></label>
                                </div>
                                <input type="button" value="Register Now" onclick="register()" >
                            </form>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <p>By logging in you agree to our <a href="#">Terms</a> and <a href="#">Conditions</a> and <a href="#">Privacy Policy</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
<!--Register-->
 <script type="text/javascript">
        function login() {
            $.ajax({
            //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: 'json',//预期服务器返回的数据类型
                url: "/Login" ,//url
                data: $('#loginForm').serialize(),
                success: function (result) {
                    console.log("log:"+result);//打印服务端返回的数据(调试用)
                    if (result.stateCode==200) {
                    if(result.role=="user"){
                         location.reload();
                         }
                         else {
                            window.location.href='/';
                         }
                    }
                    else {
                       var label=document.getElementById("inform");
                       label.innerText=result.msg;
                    }
                    ;
                },
                error : function(msg) {
                     console.log("msg:------------------ "+msg);
                    		  	  },
            });
        }

        function register() {
                    $.ajax({
                    //几个参数需要注意一下
                        type: "POST",//方法类型
                        dataType: 'json',//预期服务器返回的数据类型
                        url: "/Register" ,//url
                        data: $('#registerForm').serialize(),
                        success: function (result) {
                            console.log("log"+result);//打印服务端返回的数据(调试用)
                            if (result.stateCode==200) {
                                 location.reload();
                                 alert("注册成功!");
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

                function logout() {
                            $.ajax({
                            //几个参数需要注意一下
                                type: "POST",//方法类型

                                url: "/Logout" ,//url
                                success: function (result) {
                                    console.log("log:"+result);//打印服务端返回的数据(调试用)
                                    if (result=="user") {
                                         location.reload();
                                    }
                                    else{
                                       window.location.href='/';
                                    }
                                },
                                error : function(msg) {
                                     console.log("msg:------------------ "+msg);
                                    		  	  },
                            });
                        }

        function phoneVerify(){
                         $.ajax({
                         //几个参数需要注意一下
                         type: "POST",//方法类型
                         dataType: 'json',//预期服务器返回的数据类型
                         url: "/PhoneVerify" ,//url
                         data: $('#registerForm').serialize(),
                         success: function (result) {
                                console.log("log"+result);//打印服务端返回的数据(调试用)
                                if (result.error_code=="0") {
                                var label=document.getElementById("r-inform");
                                label.innerText="";
                                var button=document.registerForm.VerifyButton;
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
 </script>
</html>
