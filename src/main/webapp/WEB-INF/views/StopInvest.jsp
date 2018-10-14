<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/25
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="head.jsp"%>
<html>
<head>
    <title>停止定投</title>
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
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><a href="MFund.jsp">我的基金</a> / <span>停止定投</span></h3>
    </div>
</div>
<!--banner-->

<div  class="stopInvest">
    <!--banner-->
    <span class="ST_title">基金信息</span>
    <form action="#" method="post">
        <div class="BF_text">
            <span class="BF_MENU">基金代码 ：</span>
            <label class="BF_TT">${StopTrend.code}</label>
        </div>
        <div class="BF_text">
            <span class="BF_MENU">基金名称 ：</span>
            <label class="BF_TT">${StopTrend.name}</label>
        </div>
        <div class="BF_text">
            <span class="BF_MENU">购买平台 ：</span>
            <label class="BF_TT">${StopTrend.platform}</label>
        </div>
        <div class="BF_text">
            <span class="BF_MENU">本轮投入天数 ：</span>
            <label class="BF_TT">${StopTrend.days}</label>
        </div>
        <div class="BF_text">
            <span class="BF_MENU">投入成本（元） ：</span>
            <label class="BF_TT">${StopTrend.cost}</label>
        </div>
        <div class="BF_text">
            <span class="BF_MENU">当天XIRR年化（%） ：</span>
            <label class="BF_TT">${StopTrend.xirr}</label>
        </div>
        <div class="BF_text">
            <span class="BF_MENU">首日年化（%） ：</span>
            <label class="BF_TT">${StopTrend.shouRi}</label>
        </div>
        <div class="BF_text">
            <span class="BF_MENU">本轮收益（元） ：</span>
            <label class="BF_TT">${StopTrend.profit}</label>
        </div>
        <div class="clearfix"> </div>

        <div id="SI_click">
            <input type="submit" value="停止定投" id="btlogin">
        </div>
    </form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
