<%@ page import="com.demo.entity.News" %>
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
    <meta name="referrer" content="no-referrer"/>
</head>

<body>
<!--banner-->
<div class="banner1">
    <div class="container">
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><a href="index.html">主页</a> / <span>新闻中心</span></h3>
    </div>
</div>
<!--banner-->

<!--Search-->
<div class="content">
    <div class="serach-w3agile">
        <div class="container">
        </div>
    </div>
</div>
<!--Fund-->

<div class="ny_cont zml_time" >
     <table class="table fund_table">
     <thead>
             <tr class="ft_row">
                 <th class="ft_h1">序号</th>
                 <th class="ft_h2">标题</th>
                 <th></th>
                 <th class="ft_h2">来源网站</th>
                 <th class="ft_h">日期</th>
             </tr>
             </thead>
            <tbody id="NewsBody">
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
                   var result = [];
                   for (var i = 1; i < 1000; i++) {
                       result.push(i);
                   }
                   done(result);
                },
    jump:true,
    coping:true,
    pageSize: 10,
    callback:function(data,pagination){
    console.log(pagination.pageNumber);
    var page=pagination.pageNumber;
    var pageSize=10;
     $.ajax({
          type: 'GET',
          url: '/newsList?page='+page+"&pageSize="+pageSize,
          success: function(data) {
          if(data.ret_code!=0){
          alert(data.error_message);
          }
           else{
           var item=data.pagebean.contentlist;
           var html="";
                for(i in item){
                console.log(item[i].imageurls);
                html+="<tr><td>";
                if(item[i].imageurls.length!=0){
                                html+="<img src='"+item[i].imageurls[0].url+"' onerror='ImgException(this)' width='200' height='200'>";
                                }
                                else{
                                html+="<img src='../static/images/news.jpg' width='200' height='200'>";
                                }

                html+="<td><a href='"+item[i].link+"' target='_blank'>"+item[i].title+"</a></td>"+
                       "<td>"+item[i].desc+"</td>"+
                       "<td>"+item[i].source+"</td>"+
                       "<td>"+item[i].pubDate+"</td>"+
                       "</tr>";
                                                             }
               var label=document.getElementById("NewsBody");
               label.innerHTML=html;
          }
           }
          });
    }
});

  function ImgException(object){
    object.src="../static/images/news.jpg";
  }
</script>
</html>
