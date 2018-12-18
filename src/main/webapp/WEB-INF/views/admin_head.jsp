<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/20
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="login.jsp"%>
<html>
<!--header-->
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
                                <li class="menu__item"><a href="/admin_lookUser" class="menu__link"><span class="menu__helper">用户管理</span></a></li>
                                <li class="menu__item"><a href="/admin_lookUnUser" class="menu__link"><span class="menu__helper">用户审核</span></a></li>
                                <li class="menu__item"><a href="Personal.jsp" class="menu__link"><span class="menu__helper">基金管理</span></a></li>
                                <li class="menu__item"><a href="News.jsp" class="menu__link"><span class="menu__helper">资讯管理</span></a></li>
                                <li class="menu__item"><a href="contact.html" class="menu__link"><span class="menu__helper">新闻管理</span></a></li>
                            </ul>
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
