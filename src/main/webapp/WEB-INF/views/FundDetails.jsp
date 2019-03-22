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
     <c:choose>
                               <c:when test="${empty Fund.fundFullInfo}">
                               <div class="error_info">
                               <br><br>
                               <h2>未找到该基金信息！</h2>
                               <br><br>
                               </div>
                               </c:when>
                              <c:otherwise>

    <div class="FundDetails">

    <span style="font-size: x-large;">${Fund.fundFullInfo.fundName}(${Fund.fundFullInfo.fundCode})</span>
    <span>更新日期：${Fund.fundFullInfo.netValueDate}</span>
    <a href='WriteInvestInfor?fundname=${Fund.fundFullInfo.fundName}&fundcode=${Fund.fundFullInfo.fundCode}' style="padding: 5px 15px;background: #02B875;color: white;">添加</a>
    <div class="baseInfoTable">
    <table>
    <tr>
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
    <div>
    <img src="http://j4.dfcfw.com/charts/pic6/${Fund.fundFullInfo.fundCode}.png" style="margin-left: 15%;">
    <div class="fundGradeTable">
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
    </table>
    </div>

    <div class="fundExternalInfoTable">
    <table>
    <tr>
    <td class="t">基金类型</td>
    <td>${Fund.fundFullInfo.fundType}</td>
    <td class="t">基金公司名称</td>
    <td>${Fund.fundFullInfo.fundCompanyName}</td>
    </tr>
    <tr>
        <td class="t">销售状态</td>
        <td>${Fund.fundFullInfo.saleStatus}</td>
        <td class="t">可否购买</td>
        <td>${Fund.fundFullInfo.saleEnable}</td>
        </tr>
    <tr>
        <td class="t">基金管理人</td>
        <td>
        <c:forEach items="${Fund.fundManagers}" var="manager">
        <a href="#" data-toggle="modal" data-target="#managerModal" onclick="Values('${manager.name}')">${manager.name}&nbsp</a>
        </c:forEach>
        </td>
        <td class="t">风险级别</td>
        <td>${Fund.fundFullInfo.riskLevel}</td>
        </tr>
    <tr>
        <td class="t">买入费率</td>
        <td>${Fund.fundFullInfo.manageRate}</td>
        <td class="t">托管费</td>
                    <td>${Fund.fundFullInfo.trusteeRate}</td>
        </tr>
    </table>
    </div>

    <div class="fundStrategy">
    <label>投资理念</label>
    <p style="color: black;">${Fund.fundFullInfo.investPhilosophy}</p>
    <label style="margin-top: 20px;">基金策略</label>
    <p style="color: black;margin-bottom: 20px;">${Fund.fundFullInfo.investStrategy}</p>
    </div>
</div>
</c:otherwise>
</c:choose>
</div>
</div>
</div>
<jsp:include page="footer.jsp"/>

<div class="modal fade" id="managerModal" tabindex="-1" role="dialog">
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
                               <input type="text" id="managerText" name="managerText" value="">
                            </form>
                        </div>
                    </div>
                    <p>By logging in you agree to our <a href="#">Terms</a> and <a href="#">Conditions</a> and <a href="#">Privacy Policy</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
function Values(manager){
  console.log("enter Values(manager)");
  $("#managerText").val(manager);

}
</script>
</body>
</html>
