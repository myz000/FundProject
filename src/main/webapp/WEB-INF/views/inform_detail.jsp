<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/22
  Time: 16:41
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
<div class="banner1">
    <div class="container">
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><a href="/informs">通知公告</a> / <span>通知内容</span></h3>
    </div>
</div>



<div class="container">
<div class="inform_detail_div">
<p style="text-align:center;">
    <label style="width:100%;font-size:25px;color:black;text-align: center;margin-top: 20px;">${inform.title}</label><span>${inform.time}</span>
</p>

<p>
<textarea cols="163" style="color:black;border:none;" readonly>
${inform.content}
</textarea>

    <script>
        $.each($("textarea"), function (i, n) {
            $(n).css("height", n.scrollHeight + "px");
        })
    </script>
</p>
<br>
</div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
