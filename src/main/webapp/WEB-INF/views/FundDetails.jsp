<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/8
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="head.jsp"%>
<html>
<head>
    <title>基金详细信息</title>
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
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><a href="index.html">主页</a> / <span>基金信息·详细信息</span></h3>
    </div>
</div>
<!--banner-->

<div class="ny_cont zml_time">
    <div class="Funddetails">
    <span>基本信息</span>
   <li>基金代码：<span>${fundInfor.get("fundcode")}</span></li>
    <li>基金名称：<span>${fundInfor.get("commonName")}</span></li>
    <li>基金成立日期：<span>${fundInfor.get("setupDate")}</span></li>
    <li>投资类型：<span>${fundInfor.get("investType")}</span></li>
    <li>投资风格：<span>${fundInfor.get("investStyle")}</span></li>
    <li>首次投资规模：<span>${fundInfor.get("firstScale")}</span></li>
    <li>最新投资规模：<span>${fundInfor.get("latestScale")}</span></li>
    <li>基金托管公司：<span>${fundInfor.get("tgCom")}</span></li>
    <li>基金管理公司：<span>${fundInfor.get("managerCom")}</span></li>
    <li>日常申购费率：<span>${fundInfor.get("buyRate")}</span></li>
    <li>基金赎回费率：<span>${fundInfor.get("redeemRate")}</span></li>
        <br><br>
    <span>经理人信息</span>
    <li>姓名：<span>${manager.get("managerName")}</span></li>
    <li>学历/职务：<span>${manager.get("profession")}</span></li>
    <li>任职日期：<span>${manager.get("postDate")}</span></li>
    <li>相关介绍：<span>${manager.get("introduceInfo")}</span></li>
        <br><br>
    <span>基金管理公司详情</span>
    <li>管理公司名称：<span>${managerCom.get("managerComName")}</span></li>
    <li>成立日期：<span>${managerCom.get("setupDate")}</span></li>
    <li>法人代表：<span>${managerCom.get("corporateRepre")}</span></li>
    <li>总经理：<span>${managerCom.get("generalManager")}</span></li>
    <li>公司地址：<span>${managerCom.get("adde")}</span></li>
    <li>邮政编码：<span>${managerCom.get("zipCode")}</span></li>
    <li>email：<span>${managerCom.get("email")}</span></li>
    <li>公司电话：<span>${managerCom.get("linkPhone")}</span></li>
    <li>客服电话：<span>${managerCom.get("servicePhone")}</span></li>

        <br><br>
    <span>会计事务所</span>
    <li><span>${details.get("accountingCom")}</span></li>

        <br><br>
    <span>法律事务所</span>
    <li><span>${details.get("lawCom")}</span></li>

        <br><br>
    <span>投资目标</span>
    <li><span>${details.get("investTarget")}</span></li>

        <br><br>
    <span>投资原则及比例</span>
    <li><span>${details.get("investPrinciple")}</span></li>

        <br><br>
    <span>收益分配原则</span>
    <p><span>${details.get("allPrin")}</span></p>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
