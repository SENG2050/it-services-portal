<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:page>
    <jsp:attribute name="title">
        Issues
    </jsp:attribute>
    <jsp:attribute name="breadcrumbs">
        <ol class="breadcrumb pull-right">
            <li>
                <a href="/">Home</a>
            </li>
            <li class="active">Issues</li>
        </ol>
    </jsp:attribute>
    <jsp:body>
        <div class="panel panel-border">
            <div class="panel-body">
                <form method="get" action="/issues/search" class="m-b-none">
                    <div class="input-group">
                        <input type="text" class="form-control input-lg"
                               placeholder="Enter a search term..." name="term" required="required">
                        <span class="input-group-btn">
                            <button class="btn btn-ar btn-lg btn-primary" type="submit">Search</button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
        <form method="get" class="m-b-none">
            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>
                        ID
                        <span class="pull-right">
                            <c:choose>
                                <c:when test='${column == "id|ASC"}'>
                                    <button type="submit" class="btn btn-link" name="column" value="id|DESC">
                                        <i class="fa fa-fw fa-sort"></i>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-link" name="column" value="id|ASC">
                                        <i class="fa fa-fw fa-sort"></i>
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </th>
                    <th>
                        User
                        <span class="pull-right">
                            <c:choose>
                                <c:when test='${column == "userId|ASC"}'>
                                    <button type="submit" class="btn btn-link" name="column" value="userId|DESC">
                                        <i class="fa fa-fw fa-sort"></i>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-link" name="column" value="userId|ASC">
                                        <i class="fa fa-fw fa-sort"></i>
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </th>
                    <th>
                        Status
                        <span class="pull-right">
                            <c:choose>
                                <c:when test='${column == "status|ASC"}'>
                                    <button type="submit" class="btn btn-link" name="column" value="status|DESC">
                                        <i class="fa fa-fw fa-sort"></i>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-link" name="column" value="status|ASC">
                                        <i class="fa fa-fw fa-sort"></i>
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </th>
                    <th>
                        Title
                        <span class="pull-right">
                            <c:choose>
                                <c:when test='${column == "title|ASC"}'>
                                    <button type="submit" class="btn btn-link" name="column" value="title|DESC">
                                        <i class="fa fa-fw fa-sort"></i>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-link" name="column" value="title|ASC">
                                        <i class="fa fa-fw fa-sort"></i>
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </th>
                    <th>
                        Time Created
                        <span class="pull-right">
                            <c:choose>
                                <c:when test='${column == "created|ASC"}'>
                                    <button type="submit" class="btn btn-link" name="column" value="created|DESC">
                                        <i class="fa fa-fw fa-sort"></i>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-link" name="column" value="created|ASC">
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
                                <td>Issue ${issue.getIssueId()}</td>
                                <td>${issue.getUser().getName()}</td>
                                <td>${issue.getStatus().getTitle()}</td>
                                <td>${issue.getTitle()}</td>
                                <td><fmt:formatDate type="both" value="${issue.getCreated()}"/></td>
                                <td>
                                    <a href="/issues/${issue.getIssueId()}"
                                       class="button button-primary button-small button-block">
                                        <i class="fa fa-fw fa-pencil"></i>
                                        View
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6" class="text-center">There are no outstanding issues.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </form>
    </jsp:body>
</t:page>