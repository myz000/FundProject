<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/22
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
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
<div class="banner1">
    <div class="container">
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><a href="index.html">通知管理</a> / <span>修改通知</span></h3>
    </div>
</div>

<form id="updateInformForm" name="updateInformForm">
<div class="inform_add_Div">
<div class="container">

<p>
<label>标题：</label>
    <input type="text" style="width:100%;" name="title" value="${inform.title}">
<input type="hidden"  name="id" value="${inform.id}">
</p>

<p>
<label>内容：</label>
</p>

<p>
<textarea rows="25" cols="163" name="content">
${inform.content}
</textarea>
</p>
<br>

<p>
<label>权限：</label>
<select name="state">
<option value="1" <c:if test="${inform.state==1}">selected</c:if>>所有人可见</option>
<option value="0"<c:if test="${inform.state==0}">selected</c:if>>仅管理员可见</option>
</select>
</p>

<div class="formButton">
      <input type="button" value="确定" onclick="addInform()">
</div>

</div>
</div>
</form>
<script>
function addInform() {
                    $.ajax({
                    //几个参数需要注意一下
                        type: "POST",//方法类型
                        dataType: 'json',//预期服务器返回的数据类型
                        url: "/admin/inform-update" ,//url
                        data: $('#updateInformForm').serialize(),
                        success: function (result) {
                            if(result==true){
                               alert("修改成功！3秒后页面自动跳转!");
                               setTimeout(window.location.href='/admin/informs',3);
                            }
                        }
                    });
                }
</script>
</body>
</html>
