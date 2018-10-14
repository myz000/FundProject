<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/21
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp"%>
<html>
<head>
    <title>日日基金</title>
    <!--css-->
    <link href="../static/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8"/>
    <link href="../static/css/style.css" rel="stylesheet" type="text/css" media="all" charset="UTF-8" />
    <!--css-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Home Plat Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <link rel="stylesheet" href="../static/css/flexslider.css" type="text/css" media="screen" />
    <!--js-->
    <script src="../static/js/jquery.min.js"></script>
    <script src="../static/js/bootstrap.min.js"></script>
    <!--js-->
    <!--webfonts-->
    <link href='http://fonts.googleapis.com/css?family=Josefin+Sans:400,700italic,700,600italic,600,400italic,300italic,300,100italic,100' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    <!--webfonts-->
    <script src="../static/js/responsiveslides.min.js"></script>
    <script>
        $(function () {
            $("#slider").responsiveSlides({
                auto: true,
                nav: true,
                speed: 500,
                namespace: "callbacks",
                pager: true,
            });
        });
    </script>
</head>

<body>
<!--header-->
<!--banner-->
<div class="banner-section" >
    <div class="slider">
        <div class="callbacks_container">
            <ul class="rslides" id="slider">
                <li>
                    <img src="../static/images/welcome_1.jpg"  alt="" >
                    <div class="caption">
                        <h3>Investment and Finance</h3>
                        <p>“If a business is worth a dollar and I can buy it for 40 cents,
                            something good may happen to me.”</p>
                    </div>
                </li>
                <li>
                    <img src="../static/images/welcome_2.jpg"  alt="" >
                    <div class="caption">
                        <h3>Information control</h3>
                        <p> Jack Bogle：“Performance comes and goes,but costs roll on forever.”</p>
                    </div>
                </li><li>
                <img src="../static/images/welcome_3.jpg"  alt="" >

            </li>
            </ul>
        </div>
    </div>
</div>
<!--banner-->

<!--featured-->
<div class="featured-w3l">
    <div class="container">
        <h3 class="tittle1">Our Services</h3>
        <div class="feature-grids">
            <div class="col-md-4 fer-grid">
                <div class="icons">
                    <i class="glyphicon glyphicon-home" aria-hidden="true"></i>
                </div>
                <h4>Culpa qui officia des</h4>
                <p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore oluptate velit es pariatur.Quis autem vel eum iure reprehenderit qui.</p>
            </div>
            <div class="col-md-4 fer-grid">
                <div class="icons">
                    <i class="glyphicon glyphicon-signal" aria-hidden="true"></i>
                </div>
                <h4>Ncidunt ut labore et</h4>
                <p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore oluptate velit es pariatur.Quis autem vel eum iure reprehenderit qui.</p>
            </div>
            <div class="col-md-4 fer-grid">
                <div class="icons">
                    <i class="glyphicon glyphicon-cog" aria-hidden="true"></i>
                </div>
                <h4>Ut wisi enim ada </h4>
                <p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore oluptate velit es pariatur.Quis autem vel eum iure reprehenderit qui.</p>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="feature-grids">
            <div class="col-md-4 fer-grid">
                <div class="icons">
                    <i class="glyphicon glyphicon-globe" aria-hidden="true"></i>
                </div>
                <h4>Culpa qui officia des</h4>
                <p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore oluptate velit es pariatur.Quis autem vel eum iure reprehenderit qui.</p>
            </div>
            <div class="col-md-4 fer-grid">
                <div class="icons">
                    <i class="glyphicon glyphicon-signal" aria-hidden="true"></i>
                </div>
                <h4>Ncidunt ut labore et</h4>
                <p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore oluptate velit es pariatur.Quis autem vel eum iure reprehenderit qui.</p>
            </div>
            <div class="col-md-4 fer-grid">
                <div class="icons">
                    <i class="glyphicon glyphicon-cog" aria-hidden="true"></i>
                </div>
                <h4>Ut wisi enim ada </h4>
                <p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore oluptate velit es pariatur.Quis autem vel eum iure reprehenderit qui.</p>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!--featured-->
<jsp:include page="footer.jsp"/>

</body>
<script>
    var msg="${requestScope.get('msg')}";
    if(msg!=""){
        alert(msg);
    }
</script>
</html>
<script src="../static/js/AskLogin.js"></script>