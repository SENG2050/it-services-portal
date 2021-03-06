<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                <a href="${baseURL}issues">Issues</a>
            </li>
            <li class="active">${issue.getTitle()}</li>
        </ol>
    </jsp:attribute>
    <jsp:body>
        <section class="css-section">
            <div class="panel panel-primary-dark">
                <div class="panel-body">
                    <p>${issue.getDescription()}</p>
                </div>
            </div>
        </section>

        <c:if test='${issue.getResolution() != null && issue.getResolution().equals("") == false && issue.getResolution().equals("null") == false}'>
            <h2 class="section-title">Resolution</h2>
            <div class="panel panel-primary-dark">
                <div class="panel-body">
                    <p>${issue.getResolution()}</p>
                </div>
            </div>
        </c:if>

        <form method="post" name="viewForm">
            <div class="row">
                <div class="col-md-9">
                    <section class="css-section">
                        <h2 class="section-title">Comments</h2>
                        <ul class="list-unstyled">
                            <c:if test='${issue.getStatus().getTitle() != "Resolved" && issue.getStatus().getTitle() != "Knowledge Base"}'>
                                <li class="comment">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <label class="control-label" for="reply">Reply</label>

                                            <textarea class="form-control" rows="5" id="reply" name="reply"
                                                      placeholder="Type your reply here..."></textarea>
                                        </div>
                                    </div>
                                </li>
                            </c:if>
                            <c:forEach items="${issue.getComments()}" var="comment">
                                <c:if test="${comment.isPublic()}">
                                    <li class="comment">
                                        <div class="panel panel-default">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-sm-1">
                                                        <img src="https://www.gravatar.com/avatar/${comment.getUser().getEmailHash()}?d=mm&s=50"
                                                             class="imageborder img-circle">
                                                    </div>
                                                    <div class="col-sm-11">
                                                        <p>${comment.getComment()}</p>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel-footer">
                                                <div class="row">
                                                    <div class="col-sm-9">
                                                        <i class="fa fa-user"></i>
                                                            ${comment.getUser().getName()}
                                                        <i class="fa fa-clock-o"></i>
                                                        <fmt:formatDate type="both" value="${comment.getCreated()}"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </section>
                </div>
                <div class="col-md-3">
                    <section class="css-section">
                        <h2 class="section-title">Properties</h2>

                        <div class="panel panel-primary-dark">
                            <div class="panel-body">
                                <dl>
                                    <dt>Time Created</dt>
                                    <dd><fmt:formatDate type="both" value="${issue.getCreated()}"/></dd>

                                    <c:if test='${issue.getStatus().getTitle() != "Resolved" && issue.getResolved() != null}'>
                                        <dt>Time Resolved</dt>
                                        <dd><fmt:formatDate type="both" value="${issue.getResolved()}"/></dd>
                                    </c:if>

                                    <dt>Status</dt>
                                    <dd>${issue.getStatus().getTitle()}</dd>

                                    <dt>Category</dt>
                                    <dd>${issue.getCategory().getTitle()}</dd>
                                </dl>
                            </div>
                        </div>

                        <input type="hidden" name="status" value="${issue.getStatus().getId()}" id="issueStatus"/>

                        <c:choose>
                            <c:when test='${issue.getStatus().getTitle() != "Completed" && issue.getStatus().getTitle() != "Resolved" && issue.getStatus().getTitle() != "Knowledge Base"}'>
                                <button type="submit" class="btn btn-primary btn-block">
                                    Submit Comment
                                </button>
                            </c:when>
                            <c:when test='${issue.getStatus().getTitle() == "Completed"}'>
                                <div class="btn-group btn-block">
                                    <button type="button" class="btn btn-primary btn-block dropdown-toggle"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Submit <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <c:forEach items="${statuses}" var="status">
                                            <c:if test='${status.getTitle() == "Resolved" || status.getTitle() == "Not Accepted"}'>
                                                <li>
                                                    <a href="javascript:void();"
                                                       onclick="IssuesViewUser.submitAs(${status.getId()});">
                                                        Submit as ${status.getTitle()}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </c:when>
                        </c:choose>
                    </section>
                </div>
            </div>
        </form>
    </jsp:body>
</t:page>