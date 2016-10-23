<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<t:page>
    <jsp:attribute name="title">
        Knowledge Base
    </jsp:attribute>
    <jsp:attribute name="breadcrumbs">
        <ol class="breadcrumb pull-right">
            <li>
                <a href="${baseURL}">Home</a>
            </li>
            <li class="active">Knowledge Base</li>
        </ol>
    </jsp:attribute>
    <jsp:body>
        <h1 class="section-title text-center no-margin-top">Knowledge Base</h1>
        <div class="panel panel-border">
            <div class="panel-body">
                <form method="get" action="${baseURL}kb/search" class="m-b-none">
                    <div class="row">
                        <div class="col-sm-2">
                            <select class="form-control" name="category">
                                <option value="0" selected="selected">Any Category</option>
                                <c:forEach items="${categories}" var="c">
                                    <option value="${c.getId()}">${c.getTitle()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-10">
                            <div class="input-group">
                                <input type="text" class="form-control input-lg"
                                       placeholder="Enter a search term..." name="term" required="required">
                                <span class="input-group-btn">
                                    <button class="btn btn-ar btn-lg btn-primary" type="submit">Search</button>
                                </span>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <h2 class="right-line">Browse by Topic</h2>
            </div>
            <c:choose>
                <c:when test="${categories.size() > 0}">
                    <c:forEach items="${categories}" var="category">
                        <div class="col-md-6">
                            <h3 class="no-margin-top">${category.getTitle()}</h3>
                            <ul class="list-line">
                                    <%--<c:choose>
                                        <c:forEach items="${issues}" var="issue">
                                                <c:when test="${issue.isKBArticle() && issue.getCategory().getId() == category.getId()}">
                                                    <li><a href="${baseURL}kb/${issue.getId()}">${issue.getTitle()}</a></li>
                                                </c:when>
                                        </c:forEach>
                                    </c:choose>--%>
                            </ul>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>
        </div>
    </jsp:body>
</t:page>