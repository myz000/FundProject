<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/24
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="head.jsp"%>
<html>
<head>
    <title>我的基金</title>
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
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><span>我的基金</span></h3>
    </div>
</div>
<!--banner-->

<div class="MyFund">
  <div class="ny_cont zml_time">
  <label>${invest.fundname}(${invest.fundcode})</label>
        <table class="table fund_table" id="MF_tab">
            <tr class="ft_row">
               <th class="ft_h">定投首日日期</th>
               <th class="ft_h">定投方式</th>
               <th class="ft_h">每次定投金额</th>
               <th class="ft_h">已实现收益</th>
            </tr>
            <tr class="ft_row">
               <td>${invest.firstdate}</td>
               <td>${invest.investmode}</td>
               <td>${invest.amountofinvest}(元)</td>
               <td>${invest.alreadyincome}(元)</td>
            </tr>
            </table>

         <table class="table fund_table" id="MF_tab">
                    <tr class="ft_row">
                       <th class="ft_h">卖出时手续费</th>
                       <th class="ft_h">卖出到账天数</th>
                       <th class="ft_h">结算延迟天数</th>
                       <th class="ft_h">购买平台</th>
                    </tr>
                    <tr class="ft_row">
                       <td>${invest.fee}(%)</td>
                       <td>${invest.receiveddays}</td>
                       <td>${invest.delaydays}</td>
                       <td>${invest.platform}</td>
                    </tr>
                    </table>
         <table class="table fund_table" id="MF_tab">
                    <tr class="ft_row">
                       <th class="ft_h3">更新日期</th>
                       <th>资产</th>
                       <th>涨跌幅</th>
                       <th>持仓盈亏</th>
                       <th>定投天数</th>
                       <th>收益率</th>
                       <th>首日年化</th>
                       <th>xirr年化</th>
                       <th>本轮收益</th>
                       <th>投入成本</th>
                       <th>单位净值</th>
                       <th>购买份额</th>
                       <th>总份额</th>
                    </tr>
            <c:forEach items="${trendList}" var="trend" varStatus="status">
                <tr class="ft_row" id="tr_${status.index}">
                    <td>${trend.currentdate}</td>
                    <td>${trend.property}</td>
                    <td>${trend.zhangdiefu}</td>
                    <td>${trend.chicangyingkui}</td>
                    <td>${trend.investdays}</td>
                    <td>${trend.shouyirate}</td>
                    <td>${trend.shourinianhua}</td>
                    <td>${trend.xirr}</td>
                    <td>${trend.profit}</td>
                    <td>${trend.investcost}</td>
                    <td>${trend.unitval}</td>
                    <td>${trend.unitshare}</td>
                    <td>${trend.totalshare}</td>
                    </tr>
            </c:forEach>
        </table>
  </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
