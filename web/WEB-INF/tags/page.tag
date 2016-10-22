<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>
<%@attribute name="styles" fragment="true" %>
<%@attribute name="breadcrumbs" fragment="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <title>
        <jsp:invoke fragment="title"/>
    </title>

    <!-- CSS -->
    <link href="${baseURL}theme/css/preload.css" rel="stylesheet">
    <link href="${baseURL}theme/css/vendors.css" rel="stylesheet">
    <link href="${baseURL}theme/css/syntaxhighlighter/shCore.css" rel="stylesheet">
    <link href="${baseURL}theme/css/style-blue2.css" rel="stylesheet" title="default">
    <link href="${baseURL}theme/css/width-full.css" rel="stylesheet" title="default">

    <link href="${baseURL}css/it-services-portal.css" rel="stylesheet" title="default">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${baseURL}theme/js/html5shiv.min.js"></script>
    <script src="${baseURL}theme/js/respond.min.js"></script>
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
                    <a id="ar-brand" class="navbar-brand" href="${baseURL}">IT Services Portal</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="${baseURL}kb">
                                <i class="fa fa-fw fa-search"></i>
                                Knowledge Base
                            </a>
                        </li>

                        <li>
                            <a href="${baseURL}issues">
                                <i class="fa fa-fw fa-question"></i>
                                Issues
                            </a>
                        </li>

                        <c:if test='${loggedIn == false || user.isAdmin() == false}'>
                            <li>
                                <a href="${baseURL}issues/new">
                                    <i class="fa fa-fw fa-plus"></i>
                                    New Issue
                                </a>
                            </li>
                        </c:if>

                        <c:choose>
                            <c:when test='${loggedIn == true}'>
                                <li>
                                    <a href="${baseURL}logout">
                                        <i class="fa fa-fw fa-sign-out"></i>
                                        Logout
                                    </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="${baseURL}login">
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
            <c:if test='${notification != null && notification.equals("") == false}'>
                <div class="alert alert-primary">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <strong><i class="fa fa-comments-o"></i></strong>
                    ${notification}
                </div>
            </c:if>

            <jsp:doBody/>
        </div>
    </div>
</div>

<div id="back-top">
    <a href="#header"><i class="fa fa-chevron-up"></i></a>
</div>

<!-- Scripts -->
<script src="${baseURL}theme/js/vendors.js"></script>
<script src="${baseURL}theme/js/syntaxhighlighter/shCore.js"></script>
<script src="${baseURL}theme/js/syntaxhighlighter/shBrushXml.js"></script>
<script src="${baseURL}theme/js/syntaxhighlighter/shBrushJScript.js"></script>
<script src="${baseURL}theme/js/DropdownHover.js"></script>
<script src="${baseURL}theme/js/app.js"></script>
<script src="${baseURL}theme/js/holder.js"></script>
<script src="${baseURL}js/it-services-portal.js"></script>
<jsp:invoke fragment="scripts"/>
</body>
</html>