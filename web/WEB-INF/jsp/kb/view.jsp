<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<t:page>
    <jsp:attribute name="title">
       ${issue.getTitle()}
    </jsp:attribute>
    <jsp:attribute name="breadcrumbs">
        <ol class="breadcrumb pull-right">
            <li>
                <a href="${baseURL}">Home</a>
            </li>
            <li>
                <a href="${baseURL}kb">Knowledge Base</a>
            </li>
            <li class="active">${issue.getTitle()}</li>
        </ol>
    </jsp:attribute>
    <jsp:body>
        <h2>Category: ${issue.getCategory().getTitle()}</h2>
        <h2>Description</h2>
        <div class="panel panel-primary-dark">
            <div class="panel-body">
                <p>${issue.getDescription()}</p>
            </div>
        </div>
        <h2>Resolution</h2>
        <p>Time Resolved: ${issue.getResolved()}</p>
        <div class="panel panel-primary-dark">
            <div class="panel-body">
                <p>${issue.getResolution()}</p>
            </div>
        </div>
    </jsp:body>
</t:page>