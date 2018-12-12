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
    <label>${Fund.fundFullInfo.fundName}(${Fund.fundFullInfo.fundCode})</label>
    <table>
    <tr>
    <td> 最新净值日期：${Fund.fundFullInfo.netValueDate}</td>
    <td>单位净值：${Fund.fundFullInfo.netValue}</td>
    <td>日增长率：${Fund.fundFullInfo.dayOfGrowth}</td>
    </tr>
    <tr>
    <td>近一周收益率：${Fund.fundFullInfo.lastWeek}</td>
    <td>近一个季度收益率：${Fund.fundFullInfo.lastQuarter}</td>
    </tr>
    <tr>
    <td>上个月收益率：${Fund.fundFullInfo.lastMonth}</td>
    <td>上半年收益率：${Fund.fundFullInfo.lastHalfYear}</td>
    <td>上一年度收益率：${Fund.fundFullInfo.lastYear}</td>
    </tr>
    </table>

     <table>
     <tr><th>评级时间</th><th>级别</th><th>评级机构</th>
     </tr>
      <c:forEach items="${Fund.fundGrades}" var="grade" varStatus="status">
            <tr>
            <td>${grade.gradeDate}</td>
            <td>${grade.grade}</td>
            <td>${grade.gradeInst}</td>
            </tr>
      </c:forEach>
     </table>

    <table>
    <tr>
    <td>基金类型</td>
    <td>${Fund.fundFullInfo.fundType}</td>
    <td>基金公司名称</td>
    <td>${Fund.fundFullInfo.fundCompanyName}</td>
    </tr>
    <tr>
        <td>销售状态</td>
        <td>${Fund.fundFullInfo.saleStatus}</td>
        <td>可否购买</td>
        <td>${Fund.fundFullInfo.saleEnable}</td>
        </tr>
    <tr>
        <td>基金管理人</td>
        <td>
        <c:forEach items="${Fund.fundManagers}" var="manager">
        <a href="">${manager.name}</a>&nbsp
        </c:forEach>
        </td>
        <td>风险级别</td>
        <td>${Fund.fundFullInfo.riskLevel}</td>
        </tr>
    <tr>
        <td>买入费率</td>
        <td>${Fund.fundFullInfo.manageRate}</td>
        <td>托管费</td>
                    <td>${Fund.fundFullInfo.trusteeRate}</td>
        </tr>
    </table>
    <label>投资理念</label>
    <p>${Fund.fundFullInfo.investPhilosophy}</p>
    <label>基金策略</label>
    <p>${Fund.fundFullInfo.investStrategy}</p>
    </div>

</div>
<jsp:include page="footer.jsp"/>
</body>
</html>