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
        <c:forEach items="${users}" var="user">
            <dl>
                <dt>ID</dt>
                <dd>${user.getUserId()}</dd>
                <dt>First Name</dt>
                <dd>${user.getFirstName()}</dd>
                <dt>Last Name</dt>
                <dd>${user.getLastName()}</dd>
                <dt>Email</dt>
                <dd>${user.getEmail()}</dd>
                <dt>Phone</dt>
                <dd>${user.getPhone()}</dd>
                <dt>Password</dt>
                <dd>${user.getPassword()}</dd>
                <dt>Role</dt>
                <dd>${user.getRole()}</dd>
            </dl>
        </c:forEach>

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
                                <c:when test='${column == "id|asc"}'>
                                    <button type="submit" class="btn btn-link" name="column" value="id|desc">
                                        <i class="fa fa-fw fa-sort"></i>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-link" name="column" value="id|asc">
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
                                <c:when test='${column == "user|asc"}'>
                                    <button type="submit" class="btn btn-link" name="column" value="user|desc">
                                        <i class="fa fa-fw fa-sort"></i>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-link" name="column" value="user|asc">
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
                                <c:when test='${column == "status|asc"}'>
                                    <button type="submit" class="btn btn-link" name="column" value="status|desc">
                                        <i class="fa fa-fw fa-sort"></i>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-link" name="column" value="status|asc">
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
                                <c:when test='${column == "title|asc"}'>
                                    <button type="submit" class="btn btn-link" name="column" value="title|desc">
                                        <i class="fa fa-fw fa-sort"></i>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-link" name="column" value="title|asc">
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
                                <c:when test='${column == "time-created|asc"}'>
                                    <button type="submit" class="btn btn-link" name="column" value="time-created|desc">
                                        <i class="fa fa-fw fa-sort"></i>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-link" name="column" value="time-created|asc">
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
        </form>
    </jsp:body>
</t:page>