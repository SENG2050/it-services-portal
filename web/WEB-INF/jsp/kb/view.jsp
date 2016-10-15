<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<t:page>
    <jsp:attribute name="title">
       FILLER ${issue.getTitle()}
    </jsp:attribute>
    <jsp:attribute name="breadcrumbs">
        <ol class="breadcrumb pull-right">
            <li>
                <a href="/">Home</a>
            </li>
            <li>
                <a href="/issues">Issues</a>
            </li>
            <li class="active">FILLER${issue.getTitle()}</li>
        </ol>
    </jsp:attribute>
    <jsp:body>
        <h1>ARTICLE NAME${issue.getTitle()}</h1>
        <p>TIME RESOLVED:${issue.getResolved()}</p>
        <h2>Category
                ${issue.getCategory()}
        </h2>
        <h2>${issue.getResolution()}</h2>
        <h2>Description</h2>
        <p>Fill${issue.getDescription()}</p>
    </jsp:body>
</t:page>