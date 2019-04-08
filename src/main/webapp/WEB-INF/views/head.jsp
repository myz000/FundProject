
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/3
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="login.jsp"%>
<html>
<div class="header" id="home">
    <div class="container">
        <div class="header-bottom">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <nav class="menu menu--francisco">
                            <ul class="nav navbar-nav menu__list">
                                <li class="menu__item"><a href="/" class="menu__link"><span class="menu__helper">主页</span></a></li>
                                <li class="menu__item"><a href="/GetFundList" class="menu__link"><span class="menu__helper">基金信息</span></a></li>
                                <li class="menu__item"><a href="/news" class="menu__link" ><span class="menu__helper">新闻中心</span></a></li>
                                 <li class="menu__item"><a href="/informs" class="menu__link"><span class="menu__helper">通知公告</span></a></li>
                                <li class="menu__item"><a href="/user/LookBoughtFund" class="menu__link"><span class="menu__helper">我的基金</span></a></li>
                                <li class="menu__item"><a href="/user/personal" class="menu__link"><span class="menu__helper">个人中心</span></a></li>
                                <li class="menu__item"><a href="contact.jsp" class="menu__link"><span class="menu__helper">联系我们</span></a></li> </ul>
                        </nav>

                        <div class="clearfix"></div>
                    </div><!-- /.navbar-collapse -->
                    <!-- /.container-fluid -->
                </div>
            </nav>

        </div>
    </div>
</div>
<!--header-->
</html>
