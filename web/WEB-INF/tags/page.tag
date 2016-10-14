<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>
<%@attribute name="styles" fragment="true" %>
<%@attribute name="breadcrumbs" fragment="true" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <title>
        <jsp:invoke fragment="title"/>
    </title>

    <meta name="description" content="">

    <!-- This can be changed to our Tomcat folder prefix at submission time -->
    <base href="/">

    <!-- CSS -->
    <link href="theme/css/preload.css" rel="stylesheet">
    <link href="theme/css/vendors.css" rel="stylesheet">
    <link href="theme/css/syntaxhighlighter/shCore.css" rel="stylesheet">
    <link href="theme/css/style-blue2.css" rel="stylesheet" title="default">
    <link href="theme/css/width-full.css" rel="stylesheet" title="default">
    <link href="theme/css/custom.css" rel="stylesheet" title="default">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="theme/js/html5shiv.min.js"></script>
    <script src="theme/js/respond.min.js"></script>
    <![endif]-->

    <jsp:invoke fragment="styles"/>
</head>
<body>
<!-- Preloader -->
<div id="preloader">
    <div id="status">&nbsp;</div>
</div>

<div class="sb-site-container">
    <div class="boxed">
        <nav class="navbar navbar-default navbar-inverse yamm navbar-static-top" role="navigation" id="header">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <i class="fa fa-bars"></i>
                    </button>
                    <a id="ar-brand" class="navbar-brand" href="/">IT Services Portal</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="/kb">
                                <i class="fa fa-fw fa-search"></i>
                                Knowledge Base
                            </a>
                        </li>
                        <c:choose>
                            <c:when test='${loggedIn == true}'>
                                <c:choose>
                                    <c:when test='${user.isAdmin()}'>
                                        <li>
                                            <a href="/issues">
                                                <i class="fa fa-fw fa-question"></i>
                                                Issues
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li>
                                            <a href="/issues/new">
                                                <i class="fa fa-fw fa-plus"></i>
                                                New Issue
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>

                                <li>
                                    <a href="/logout">
                                        <i class="fa fa-fw fa-sign-out"></i>
                                        Logout
                                    </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="/issues/new">
                                        <i class="fa fa-fw fa-plus"></i>
                                        New Issue
                                    </a>
                                </li>

                                <li>
                                    <a href="/login">
                                        <i class="fa fa-fw fa-sign-in"></i>
                                        Login
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </nav>

        <header class="main-header">
            <div class="container">
                <h1 class="page-title">
                    <jsp:invoke fragment="title"/>
                </h1>

                <jsp:invoke fragment="breadcrumbs"/>
            </div>
        </header>

        <div class="container">
            <jsp:doBody/>
        </div>
    </div>
</div>

<div id="back-top">
    <a href="#header"><i class="fa fa-chevron-up"></i></a>
</div>

<!-- Scripts -->
<script src="theme/js/vendors.js"></script>
<script src="theme/js/syntaxhighlighter/shCore.js"></script>
<script src="theme/js/syntaxhighlighter/shBrushXml.js"></script>
<script src="theme/js/syntaxhighlighter/shBrushJScript.js"></script>

<script src="theme/js/DropdownHover.js"></script>
<script src="theme/js/app.js"></script>
<script src="theme/js/holder.js"></script>
<jsp:invoke fragment="scripts"/>
</body>
</html>