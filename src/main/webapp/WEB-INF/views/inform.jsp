<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="head.jsp"%>
<html>
<head>
    <title>Title</title>
    <!--css-->
    <link href="../static/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8"/>
    <link href="../static/css/style.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8" />
    <!--css-->]
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
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><a href="/">主页</a> / <span>通知公告</span></h3>
    </div>
</div>
<!--banner-->

<!--Search-->
<form action="/searchInform" method="POST">
    <div class="content">
        <div class="serach-w3agile">
            <div class="container">
                <div class="place-grids">
                    <div class="col-md-3 place-grid">
                        <input class="sel" type="text" name="searchText">
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

<div class="ny_cont zml_time">
    <table class="table fund_table">
        <tr class="ft_row">
            <th class="ft_h1">序号</th>
            <th class="ft_h3">标题</th>
            <th class="ft_h">时间</th>
        </tr>

        <c:forEach items="${informList}" var="inform" varStatus="status">
            <tr class="ft_row">
                <td>${status.index+1}<input type="hidden" value="${inform.id}" name="id"></td>
                <td><a href="/inform_detail?id=${inform.id}">${inform.title}</a></td>
                <td>${inform.time}</td>
            </tr>
        </c:forEach>

    </table>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
