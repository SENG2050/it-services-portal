<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<t:page>
    <jsp:attribute name="title">
        New Issue
    </jsp:attribute>
    <jsp:attribute name="breadcrumbs">
        <ol class="breadcrumb pull-right">
            <li>
                <a href="${baseURL}">Home</a>
            </li>
            <li>
                <a href="${baseURL}issues">Issues</a>
            </li>
            <li class="active">New Issue</li>
        </ol>
    </jsp:attribute>
    <jsp:body>
        <form method="post" class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-sm-2" for="category">Category</label>

                <div class="col-sm-10">
                    <select class="form-control" name="category" id="category">
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
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="issueTitle">Issue Title</label>

                <div class="col-sm-10">
                    <input type="text" class="form-control" name="issueTitle" required="required"
                              id="issueTitle" placeholder="Type your issue title here..."
                              onkeyup="IssuesNew.relatedArticles('${baseURL}');" value="${title}"/>

                    <span class="help-block">Please be as descriptive as possible to get the best possible response.</span>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="description">Issue Description</label>

                <div class="col-sm-10">
                    <textarea class="form-control" rows="5" name="description" required="required"
                              id="description" placeholder="Type your issue here...">${description}</textarea>

                    <span class="help-block">Please be as descriptive as possible to get the best possible response.</span>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-plus"></i>
                        Submit Issue
                    </button>
                </div>
            </div>
        </form>

        <iframe class="full-width" frameborder="0" scrolling="no" id="related-articles" onload="resizeIframe(this);"></iframe>
    </jsp:body>
</t:page>