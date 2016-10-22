<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:page>
    <jsp:attribute name="title">
        Search Results For "${term}"
    </jsp:attribute>
    <jsp:attribute name="breadcrumbs">
        <ol class="breadcrumb pull-right">
            <li>
                <a href="${baseURL}">Home</a>
            </li>
            <li>
                <a href="${baseURL}kb">KnowledgeBase</a>
            </li>
            <li class="active">Search Results For "${term}"</li>
        </ol>
    </jsp:attribute>
    <jsp:body>
        <div class="panel panel-border">
            <div class="panel-body">
                <form method="get" action="/kb/search" class="m-b-none">
                    <div class="row">
                        <div class="col-sm-2">
                            <select class="form-control" name="category">
                                <c:choose>
                                    <c:when test='${category == null || category == "0"}'>
                                        <option value="0" selected="selected">Any Category</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="0">Any Category</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach items="${categories}" var="c">
                                    <c:choose>
                                        <c:when test='${category != null && category == c.getId().toString()}'>
                                            <option value="${c.getId()}" selected="selected">${c.getTitle()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${c.getId()}">${c.getTitle()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    <div class="input-group">
                        <input type="text" class="form-control input-lg" value="${term}"
                               placeholder="Enter a search term..." name="term" required="required">
                        <span class="input-group-btn">
                            <button class="btn btn-ar btn-lg btn-primary" type="submit">Search</button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
        <form method="get" action="${baseURL}kb/search" class="m-b-none">
            <input type="hidden" name="term" value="${term}">

            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th class="table-middle">
                        Article Title
                    </th>
                    <th class="table-middle">
                        Category
                    </th>
                    <th class="table-middle">
                        Time Resolved
                        <span class="pull-right">
                            <c:choose>
                                <c:when test='${column == "time-resolved|asc"}'>
                                    <button type="submit" class="btn btn-link" name="column" value="time-resolved|DESC">
                                        <i class="fa fa-fw fa-sort"></i>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-link" name="column" value="time-resolved|ASC">
                                        <i class="fa fa-fw fa-sort"></i>
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </th>
                    <th>
                        View
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${issues.size() > 0}">
                        <c:forEach items="${issues}" var="issue">
                            <tr>
                                <c:choose>
                                    <c:when test="${issue.isKBArticle()}">
                                        <td>${issue.getTitle()}</td>
                                        <td>${issue.getCategory().getTitle()}</td>
                                        <td><fmt:formatDate type="both" value="${issue.getResolved()}"/></td>
                                        <td>
                                            <a href="${baseURL}kb/${issue.getIssueId()}"
                                               class="button button-primary button-small button-block">
                                                <i class="fa fa-fw fa-pencil"></i>
                                                View
                                            </a>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td colspan="6" class="text-center">There are no Knowledge Base Articles found.</td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6" class="text-center">There are no Knowledge Base Articles found.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </form>
    </jsp:body>
</t:page>