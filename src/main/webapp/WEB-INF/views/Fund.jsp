<%@ page import="com.demo.entity.Fund" %>
<%@ page import="com.demo.api.FundDataDemo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/23
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="head.jsp"%>
<html>
<head>
    <title>Title</title>
    <!--css-->
    <link href="../static/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8"/>
    <link href="../static/css/style.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8" />
    <link href="../static/css/pagination.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8" />
    <!--css-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!--js-->
    <script src="../static/js/jquery.min.js"></script>
    <script src="../static/js/bootstrap.min.js"></script>
     <script src="../static/js/pagination.js"></script>
    <!--js-->
</head>

<body>
<!--banner-->
<div class="banner1">
    <div class="container">
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><a href="index.html">主页</a> / <span>基金信息</span></h3>
    </div>
</div>
<!--banner-->

<!--Search-->
<form action="" method="post">
<div class="content">

    <div class="serach-w3agile">
        <div class="container">
            <div class="place-grids">
                <div class="col-md-3 place-grid">
                    <input class="sel" type="text" name="code">
                </div>
                <div class="col-md-2 place-grid">
                    <select class="sel">
                        <option value="">基金代码</option>
                        <option value="">基金名称</option>
                    </select>
                </div>
                <div class="col-md-2 place-grid">
                        <input type="submit" value="Search">
                </div>
                <div class="clearfix"></div>
            </div>

        </div>

    </div>

</div>
</form>
<!--Fund-->

<div class="ny_cont zml_time" >
<table class="table fund_table">
<thead>
        <tr class="ft_row">
            <th class="ft_h1">序号</th>
            <th class="ft_h2">基金代码</th>
            <th class="ft_h3" name="FundName">基金简称</th>
            <th class="ft_h">单位净收益    （元）</th>
            <th class="ft_h">单位可分配收益（元）</th>
            <th class="ft_h">净资产收益率  （%）</th>
            <th class="ft_h">净值增长率    （%）</th>
            <th class="ft_h">累计净值增长率（%）</th>
            <th class="ft_h">购买基金</th>
        </tr>
        </thead>
       <tbody id="FundListBody">
       <tr>
       <td>
       </td>
       <tr>
       </tbody>
    </table>
     <div class="pageSide">
        <div class="M-box" id="M-box"></div>
    </div>
    </div>


<jsp:include page="footer.jsp"/>
</body>
<script>
    $('#M-box').pagination({
    dataSource:function(done){
                   $.ajax({
                                          type: 'GET',
                                          url: '/GetPageFundList',
                                          success: function(response) {
                                              done(response);
                                          }
                                      });
                   },
    pageCount:5,
    jump:true,
    coping:true,
    pageSize: 10,
    callback:function(data,pagination){
     var html="";
     for(i in data){
     var index=(pagination.pageNumber-1)*pagination.pageSize+parseInt(i)+1;
     html+=
        "<tr class='ft_row'>"+
                                                      "<td>"+index+"</td>"+
                                                      "<td><a href='GetFundDetails?FundCode="+data[i].code+"'>"+data[i].code+"</a></td>"+
                                                      "<td><a href='GetFundDetails?FundCode="+data[i].name+"'>"+data[i].name+"</a></td>"+
                                                      "<td>"+data[i].netincome+"</td>"+
                                                      "<td>"+data[i].assincome+"</td>"+
                                                      "<td>"+data[i].netassrate+"</td>"+
                                                      "<td>"+data[i].netgrowrate+"</td>"+
                                                      "<td>"+data[i].tonetgrora+"</td>"+
                                                      "<td><a href='WriteInvestInfor?fundname="+data[i].name+"&fundcode="+data[i].code+"'>购买</a></td>"+
                                                  "</tr>";
                                                  }
    var label=document.getElementById("FundListBody");
    label.innerHTML=html;;
    }
});




</script>




</html>
