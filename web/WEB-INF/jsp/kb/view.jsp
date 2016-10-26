<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:page>
    <jsp:attribute name="title">

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
        <h2 class="section-title">${issue.getTitle()}</h2>
        <h3>Category: ${issue.getCategory().getTitle()}</h3>
        <h2 class="section-title">Description</h2>
        <div class="panel panel-primary-dark">
            <div class="panel-body">
                <p>${issue.getDescription()}</p>
            </div>
        </div>
        <h2 class="section-title">Resolution</h2>
        <p>Time Resolved: <fmt:formatDate type="both" value="${issue.getResolved()}"/></p>
        <div class="panel panel-primary-dark">
            <div class="panel-body">
                <p>${issue.getResolution()}</p>
            </div>
        </div>
        <h2 class="section-title">Properties</h2>
        <div class="panel panel-primary-dark">
            <div class="panel-body">
                <p>Article ID: ${issue.getIssueId()} - Resolution: <fmt:formatDate type="both" value="${issue.getResolved()}"/></p>
                <p>Category: ${issue.getCategory().getTitle()}</p>
            </div>
        </div>
    </jsp:body>
</t:page>