<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/8
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="head.jsp" %>
<html>
<head>
    <title>基金详细信息</title>
    <!--css-->
    <link href="../static/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8"/>
    <link href="../static/css/style.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8"/>
    <!--css-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!--js-->
    <script src="../static/js/jquery.min.js"></script>
    <script src="../static/js/bootstrap.min.js"></script>
    <!--js-->
</head>
<body>
<!--banner-->
<div class="banner1">
    <div class="container">
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><a href="index.html">主页</a> / <span>基金信息·详细信息</span>
        </h3>
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
        <a href='WriteInvestInfor?fundname=${Fund.fundFullInfo.fundName}&fundcode=${Fund.fundFullInfo.fundCode}'
           style="padding: 5px 15px;background: #02B875;color: white;">添加</a>
        <div class="baseInfoTable">
            <label style="font-size: x-large">净值信息</label>
            <table>
                <tr>
                    <td>单位净值：<span style="font-size: x-large;color: #298A08;">${Fund.fundFullInfo.netValue}</span></td>
                </tr>
                <tr>
                    <td>日增长率：<span>${Fund.fundFullInfo.dayOfGrowth}</span></td>
                    <td>近一周收益率：<span>${Fund.fundFullInfo.lastWeek}</span></td>
                    <td>近一个季度收益率：<span>${Fund.fundFullInfo.lastQuarter}</span></td>
                </tr>
                <tr>
                    <td>上个月收益率：<span>${Fund.fundFullInfo.lastMonth}</span></td>
                    <td>上半年收益率：<span>${Fund.fundFullInfo.lastHalfYear}</span></td>
                    <td>上一年度收益率：<span>${Fund.fundFullInfo.lastYear}</span></td>
                </tr>
            </table>
            <div>
                <img src="http://j4.dfcfw.com/charts/pic6/${Fund.fundFullInfo.fundCode}.png" style="margin: 5% 0">
                <div class="fundGradeTable">
                    <label style="font-size: x-large">评级信息</label>
                    <table>
                        <tr>
                            <th>评级时间</th>
                            <th>级别</th>
                            <th>评级机构</th>
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
                    <label style="font-size: x-large">基本概况</label>
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
                                    <a href="#" data-toggle="modal" data-target="#managerModal"
                                       onclick="Values('${manager.name}','${manager.gender}','${manager.educationLevel}','${manager.gmtCreate}','${manager.gmtModified}','${manager.background}')">${manager.name}&nbsp</a>
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
                    <label style="font-size: x-large">投资理念</label>
                    <p style="color: black;text-align: left">
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${Fund.fundFullInfo.investPhilosophy}</p>
                    <label style="margin-top: 20px;font-size: x-large">基金策略</label>
                    <p style="color: black;margin-bottom: 20px;text-align: left">
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${Fund.fundFullInfo.investStrategy}</p>
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body real-spa">
                <div class="login-grids">
                    <div class="login">
                        <div class="login-right">
                            <form method="post" name="loginForm" id="loginForm">
                                <p><label>姓名</label><input type="text" id="managerName" name="managerName" value=""
                                                           readonly></p>
                                <p><label>性别</label><input type="text" id="sex" name="sex" value="" readonly></p>
                                <p><label>教育程度</label><input type="text" id="edu" name="edu" value="" readonly></p>
                                <p><label>任职日期</label><input type="text" id="gmtCreate" name="gmtCreate" value=""
                                                             readonly></p>
                                <p><label>变动时间</label><input type="text" id="gmtModify" name="modify" value="" readonly>
                                </p>
                                <p><label>背景介绍</label></p>
                                <textarea id="background" name="background" style="width: 100%;height: 300px"
                                          readonly></textarea>
                            </form>
                        </div>
                    </div>
                    <p>By logging in you agree to our <a href="#">Terms</a> and <a href="#">Conditions</a> and <a
                            href="#">Privacy Policy</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function Values(name, sex, edu, gmtCreate, gmtModify, background) {
        console.log("enter Values(manager)");
        $("#managerName").val(name);
        $("#sex").val(sex);
        $("#edu").val(edu);
        $("#gmtCreate").val(gmtCreate);
        $("#gmtModify").val(gmtModify);
        $("#background").val(background);

    }
</script>
<script>
    $.each($("textarea"), function (i, n) {
        $(n).css("height", n.scrollHeight + "px");
    })
</script>
</body>
</html>
