<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/25
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="head.jsp"%>
<html>
<head>
    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <title>更新基金</title>
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
    <link rel="stylesheet" href="../static/css/calendar.css">
    <style type="text/css">
        a {
            text-decoration: none;
        }
        ul, ol, li {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .UP_click{
            display: inline-block;
        }
        .xxx{
            margin-top: 20px;
        }


    </style>
</head>
<body>
<!--banner-->
<div class="banner1">
    <div class="container">
        <h3 class="animated wow slideInLeft" data-wow-delay=".5s"><a href="/user/LookBoughtFund">我的基金</a> / <span>更新基金</span></h3>
    </div>
</div>
<!--banner-->
<form method="post" name="updateForm" id="updateForm">
<div class="UpdateMyFund">
    <div class="UP_click">
        <label>日期：</label><input type="text" id="dt" name="date" placeholder="请选择日期" readonly>
        <input type="button"  value="更新基金" onclick="update()"/>
         <label id="message" name="message" style="color:red;background:white;"></label>
        <div id="dd"></div>
    </div>
    <div class="xxx"></div>
    <div class="ny_cont zml_time">
    <table class="table fund_table" id="MF_tab">
        <tr class="ft_row">
            <th class="ft_h1">序号</th>
            <th class="ft_h1"></th>
            <th class="ft_h">基金代码</th>
            <th class="ft_h3">基金简称</th>
            <th class="ft_h3">资产</th>
            <th class="ft_h">涨跌幅</th>
            <th class="ft_h">持仓盈亏</th>
        </tr>
        <c:forEach items="${showTrendList}" var="trend" varStatus="status">
            <tr class="ft_row" id="tr_${status.index}">
                <td>${status.index+1}<input type="hide" name="investid" value="${trend.investid}"></td>
                <td><input type="checkbox" name="check" value="${status.index+1}" checked></td>
                <td><input type="text" value="${trend.fundcode}" readonly name="fundcode" id="fundcode" style="border: none"></td>
                <td>${trend.fundname}</td>
                <td><input type="text" name="property" value=" "></td>
                <td><input type="text" name="zdf" value=" "></td>
                <td><input type="text" name="ccyk" value=" "></td>
            </tr>
        </c:forEach>
    </table>
    </div>
</div>
</form>
<script src="../static/js/jquery.min.js"></script>
<script src="../static/js/calendar.js"></script>
<script>
    $('#ca').calendar({
        width: 320,
        height: 320,
        data: [
            {
                date: '2015/12/24',
                value: 'Christmas Eve'
            },
            {
                date: '2015/12/25',
                value: 'Merry Christmas'
            },
            {
                date: '2016/01/01',
                value: 'Happy New Year'
            }
        ],
        onSelected: function (view, date, data) {
            console.log('view:' + view)
            alert('date:' + date)
            console.log('data:' + (data || 'None'));
        }
    });


    $('#dd').calendar({
        trigger: '#dt',
        zIndex: 999,
        format: 'yyyy-mm-dd',
        onSelected: function (view, date, data) {
            console.log('event: onSelected')
        },
        onClose: function (view, date, data) {
            console.log('event: onClose')
            console.log('view:' + view)
            console.log('date:' + date)
            console.log('data:' + (data || 'None'));
        }
    });
</script>
<script>
 function update() {
          var label=document.getElementById("message");
          if($("#dt").val()==""){
                 label.innerText="请选择日期！";
                 return;
          }
                            $.ajax({
                            //几个参数需要注意一下
                                type: "POST",//方法类型
                                url: "/user/UpdateTrend" ,//url
                                dataType: 'json',//预期服务器返回的数据类型
                                data:$("#updateForm").serialize(),
                                success: function (result) {
                                    console.log("log:"+result);//打印服务端返回的数据(调试用)
                                    if (result.stateCode=="200") {
                                         window.location.href='/user/LookBoughtFund';
                                    }
                                    else{
                                       label.innerText=result.message;
                                    }
                                },
                                error : function(msg) {
                                     console.log("msg:------------------ "+msg);
                                    		  	  },
                            });
                        }
</script>

<jsp:include page="footer.jsp"/>
</body>
</html>
