<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/23
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="head.jsp"%>
<html>
<head>
    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <title>购买基金</title>
    <!--css-->
    <link href="../static/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8"/>
     <!--css-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!--js-->
    <script src="../static/js/jquery.min.js"></script>
    <script src="../static/js/bootstrap.min.js"></script>
    <!--js-->
    <link href="../static/css/style.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8" />
    <link rel="stylesheet" href="../static/css/calendar.css">
    <style type="text/css">
        a {
            text-decoration: none;
        }
        ul, ol, li {
            list-style: none;
            padding: 0;
            margin: 0;
        }

    </style>
</head>
<body>

<!--banner-->
<div class="banner1">
    <div class="container">
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><a href="Fund.jsp">基金信息</a> / <span>购买基金</span></h3>
    </div>
</div>
<!--banner-->

<div class="BuyFund">
    <!--banner-->
    <form action="BuyFund" method="post">
        <div class="col-md-12 header-right">
            <h1>Hi !</h1>
            <h6>定投信息</h6>
            <ul class="address">
            </ul>
        </div>
        <div class="BF_text">
            <span class="BF_MENU">基金代码 ：</span>
            <input class="BF_TT" readonly="true" value="${fundCode}" name="fundCode"/>
        </div>
        <div class="BF_text">
            <span class="BF_MENU">基金名称 ：</span>
            <input class="BF_TT" readonly="true" value="${fundName}" name="fundName"/>
        </div>
        <div class="BF_text">
            <span class="BF_MENU">定投首日日期：</span>
            <input type="text" id="dt" placeholder="请选择日期" name="firstDate">
            <div id="dd"></div>
        </div>
        <div class="BF_text">
            <span class="BF_MENU">定投方式 ：</span>
            <select id="way" name="investMode">
                <option>日投</option>
                <option>周投</option>
                <option>月投</option>
            </select>
        </div>
        <div class="BF_text">
            <span class="BF_MENU">已实现收益（元）：</span>
            <input type="text" id="income" name="alreadyIncome">
        </div>
        <div class="BF_text">
            <span class="BF_MENU">每次投入金额（元）：</span>
            <input type="text" in="amount" name="amountOfInvest">
        </div>
        <div class="BF_text">
            <span class="BF_MENU">卖出时手续费（%）：</span>
            <input type="text" id="fee" name="fee">
        </div>
        <div class="BF_text">
            <span class="BF_MENU">卖出到账工作日天数：</span>
            <input type="text" id="days" name="receivedDays">
        </div>
        <div class="BF_text">
            <span class="BF_MENU">结算延迟天数：</span>
            <input type="text" id="delaydays" name="delayDays">
        </div>
        <div class="BF_text">
            <span class="BF_MENU">购买平台：</span>
            <input type="text" id="platform" name="platform">
        </div>
        <div class="clearfix"> </div>

    <div id="BF_click">
        <input type="submit" value="确定" id="btlogin">
    </div>
</form>
</div>

<script src="../static/js/jquery.js"></script>
<script src="../static/js/calendar.js"></script>
<script>
    $('#ca').calendar({
        width: 320,
        height: 320,
        data: [
            {
                date: '2015/12/24',
                value: 'Christmas Eve'
            },
            {
                date: '2015/12/25',
                value: 'Merry Christmas'
            },
            {
                date: '2016/01/01',
                value: 'Happy New Year'
            }
        ],
        onSelected: function (view, date, data) {
            console.log('view:' + view)
            alert('date:' + date)
            console.log('data:' + (data || 'None'));
        }
    });


    $('#dd').calendar({
        trigger: '#dt',
        zIndex: 999,
        format: 'yyyy-mm-dd',
        onSelected: function (view, date, data) {
            console.log('event: onSelected')
        },
        onClose: function (view, date, data) {
            console.log('event: onClose')
            console.log('view:' + view)
            console.log('date:' + date)
            console.log('data:' + (data || 'None'));
        }
    });
</script>
<jsp:include page="footer.jsp"/>
</body>
</html>
