<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="admin_head.jsp"%>
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
<!--banner-->
<div class="banner1">
    <div class="container">
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><a href="/admin/lookUser">主页</a> / <span>用户管理</span></h3>
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
    <form method="post" action="/admin/chauthor">
        <table class="table fund_table">
            <tr class="ft_row">
                <th class="ft_h1">序号</th>
                <th class="ft_h">用户名</th>
                <th class="ft_h1">身份证</th>
                <th class="ft_h1">性别</th>
                <th class="ft_h3">电话号码</th>
                <th class="ft_h3">邮箱</th>
                <th class="ft_h2">角色</th>
                <th class="ft_h2">状态</th>
                <th class="ft_h2">审核</th>
            </tr>
            <c:forEach items="${userlist}" var="user" varStatus="status">
                <tr class="ft_row">
                    <td>${status.index+1}</td>
                    <td>${user.getUsername()}</td>
                    <td>${user.getId()}</td>
                    <td>${user.getSex()}</td>
                    <td>${user.getTelephone()}</td>
                    <td>${user.getEmail()}</td>
                    <td>${user.getRole()}</td>
                    <c:choose>
                        <c:when test="${user.getState() eq 1}">
                            <td>正常</td>
                        </c:when>
                        <c:when test="${user.getState() eq 2}">
                            <td>冻结</td>
                        </c:when>
                        <c:otherwise>
                            <td>待审核</td>
                        </c:otherwise>
                    </c:choose>
                    <td><input type="radio" name="radio" value="${status.index+1}"/> </td>
                </tr>
            </c:forEach>
            <input type="submit" value="通过"/>
        </table>
    </form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
