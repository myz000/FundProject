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
<form action="/admin/searchUser" method="POST">
    <div class="content">
        <div class="serach-w3agile">
            <div class="container">
                <div class="place-grids">
                    <div class="col-md-3 place-grid">
                        <input class="sel" type="text" name="searchText">
                    </div>
                     <div class="col-md-2 place-grid">
                        <select class="sel" name="Filter">
                           <option value="name">用户名称</option>
                           <option value="id">身份证号</option>
                           <option value="phone">电话号码</option>
                           <option value="email">邮箱</option>
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

<div class="ny_cont zml_time">
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
            <th class="ft_h">权限设置</th>
            <th class="ft_h">重置密码</th>
            <th class="ft_h">删除用户</th>
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
                <td><a href="/admin/to-authority?username=${user.username}">设置</a></td>
                <td><a href="/admin/to-changePassword?username=${user.username}">重置</a></td>
                <td><a href="#" onclick="deleteUser('${user.username}')">删除</a></td>
            </tr>
        </c:forEach>

    </table>
</div>
<jsp:include page="footer.jsp"/>
<script>
function deleteUser(username){
    var r=confirm("是否删除用户【"+username+"】?");
            if (r==true){
            $.ajax({
                                //几个参数需要注意一下
                                    type: "POST",//方法类型
                                    dataType: 'json',//预期服务器返回的数据类型
                                    url: "/admin/deleteUser?userName="+username ,//url
                                    success: function (result) {
                                    if(result.hasOwnProperty("state")){
                                    alert(result.msg+",3秒后页面自动跳转！");
                                    setTimeout(window.location.href='/admin/lookUser',3);
                                    }
                                    else{
                                    alert(result.msg);
                                    }
                                    }
                                });
            }
}
</script>

</body>
</html>
