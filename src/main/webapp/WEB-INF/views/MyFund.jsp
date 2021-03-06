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
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><a href="/informs">基金管理</a> /<span>我的基金</span></h3>
    </div>
</div>
<!--banner-->

<div class="MyFund">
    <div id="MF_click">
        <a href="toUpdateTrend" id="btlogin">更新基金</a>
    </div>
    <!--Fund-->
  <div class="ny_cont zml_time">
        <table class="table fund_table" id="MF_tab">
            <tr class="ft_row">
                <th class="ft_h1">序号</th>
                <th class="ft_h">基金代码</th>
                <th class="ft_h3">基金简称</th>
                <th class="ft_h3">购买平台</th>
                <th class="ft_h">投入自然日</th>
                <th class="ft_h">投入成本（元）</th>
                <th class="ft_h3">当天XIRR年化收益（%）</th>
                <th class="ft_h">首日年化（%）</th>
                <th class="ft_h">本轮收益（元）</th>
                <th class="ft_h">收益率</th>
                <th class="ft_h3">更新日期</th>
                <th class="ft_h4">详细信息</th>
                <th class="ft_h4">停止定投</th>
            </tr>
            <c:forEach items="${showTrendList}" var="trend" varStatus="status">
                <tr class="ft_row" id="tr_${status.index}">
                    <td>${status.index+1}</td>
                    <td><a href="/GetFundDetails?fundCode=${trend.fundcode}">${trend.fundcode}</a></td>
                    <td>${trend.fundname}</td>
                    <td>${trend.platform}</td>
                    <td>${trend.investdays}</td>
                    <td>${trend.investcost}</td>
                    <td>${trend.xirr}</td>
                    <td>${trend.shourinianhua}</td>
                    <td>${trend.profit}</td>
                    <td>${trend.shouyirate}</td>
                    <td>${trend.date}</td>
                    <td style="padding:5px 0" align="center"><a href="/user/FundDetail?investId=${trend.investid}">查看</a></td>
                    <td style="padding:5px 0" align="center"><a href="#" onclick="Stop('${trend.fundname}','${trend.investid}')">停止</a></td>
                </tr>
            </c:forEach>
        </table>
  </div>
</div>
<script type="text/javascript">
        var table = document.getElementById("MF_tab");
        var len = table.rows.length;
        var _row = table.rows;
        for(var i = 0;i < len;i++){
            var _cell = _row[i].cells;
            var d=_cell[7].innerHTML;
            if(d>=0.44){
                table.rows[i].style.backgroundColor = "#FFCC99";
            }
            else if(d>=0.27){
                table.rows[i].style.backgroundColor = "#99CCCC";
            }
            else{
                table.rows[i].style.backgroundColor = "white";
            }
        }

</script>
<script>
    function Stop(name,id){
        var r=confirm("是否停止定投基金【"+name+"】?");
        if (r==true){
        window.location.href = "/user/StopInvest?investId="+id;


        }
    }

</script>

<jsp:include page="footer.jsp"/>
</body>
</html>
