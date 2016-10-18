<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>
<%@attribute name="styles" fragment="true" %>

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

<jsp:doBody/>

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