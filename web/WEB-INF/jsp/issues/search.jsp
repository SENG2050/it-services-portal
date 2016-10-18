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
                <a href="${baseURL}issues">Issues</a>
            </li>
            <li class="active">Search Results For "${term}"</li>
        </ol>
    </jsp:attribute>
    <jsp:body>
        <div class="panel panel-border">
            <div class="panel-body">
                <form method="get" action="${baseURL}issues/search" class="m-b-none">
                    <div class="row">
                        <div class="col-sm-2">
                            <select class="form-control" name="status">
                                <c:choose>
                                    <c:when test='${status == null || status == "0"}'>
                                        <option value="0" selected="selected">Any Status</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="0">Any Status</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach items="${statuses}" var="s">
                                    <c:choose>
                                        <c:when test='${status != null && status == s.getId().toString()}'>
                                            <option value="${s.getId()}" selected="selected">${s.getTitle()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${s.getId()}">${s.getTitle()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
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
                        <div class="col-sm-8">
                            <div class="input-group">
                                <input type="text" class="form-control input-lg" value="${term}"
                                       placeholder="Enter a search term..." name="term">
                                <span class="input-group-btn">
                                    <button class="btn btn-ar btn-lg btn-primary" type="submit">Search</button>
                                </span>
                            </div>
                        </div>
                    </div>

                    <input type="hidden" name="column" value="${column}">
                </form>
            </div>
        </div>
        <form method="get" action="${baseURL}issues/search" class="m-b-none">
            <input type="hidden" name="status" value="${status}">
            <input type="hidden" name="category" value="${category}">
            <input type="hidden" name="term" value="${term}">

            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th class="table-middle">
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
                    <th class="table-middle">
                        User
                    </th>
                    <th class="table-middle">
                        Status
                    </th>
                    <th class="table-middle">
                        Category
                    </th>
                    <th class="table-middle">
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
                    <th class="table-middle">
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
                    <th class="table-middle">
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
                                <td>${issue.getCategory().getTitle()}</td>
                                <td>${issue.getTitle()}</td>
                                <td><fmt:formatDate type="both" value="${issue.getCreated()}"/></td>
                                <td>
                                    <a href="${baseURL}issues/${issue.getIssueId()}"
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
                            <td colspan="7" class="text-center">There are no matching issues.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </form>
    </jsp:body>
</t:page>