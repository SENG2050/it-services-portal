<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

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
        <table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
            <thead>
            <tr>
                <th>ID</th>
                <th>User</th>
                <th>Status</th>
                <th>Title</th>
                <th>Time Created</th>
                <th>View</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${issues.size() > 0}">
                    <c:forEach items="${issues}" var="issue">
                        <tr>
                            <td>Issue ${issue.getIssueId()}</td>
                            <td>${issue.getUserId()}</td>
                            <td>${issue.getStatus()}</td>
                            <td>${issue.getTitle()}</td>
                            <td>${issue.getCreated()}</td>
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
    </jsp:body>
</t:page>