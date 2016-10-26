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
                <a href="${baseURL}kb">Knowledge Base</a>
            </li>
            <li class="active">${issue.getTitle()}</li>
        </ol>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-9">
                <section class="css-section">
                    <h2 class="section-title">Description</h2>
                    <div class="panel panel-primary-dark">
                        <div class="panel-body">
                            <p>${issue.getDescription()}</p>
                        </div>
                    </div>

                    <h2 class="section-title">Resolution</h2>
                    <div class="panel panel-primary-dark">
                        <div class="panel-body">
                            <p>${issue.getResolution()}</p>
                        </div>
                    </div>
                </section>
            </div>
            <div class="col-md-3">
                <section class="css-section">
                    <h2 class="section-title">Properties</h2>

                    <div class="panel panel-primary-dark">
                        <div class="panel-body">
                            <dl>
                                <c:if test="${issue.getResolved() != null}">
                                    <dt>Time Resolved</dt>
                                    <dd><fmt:formatDate type="both" value="${issue.getResolved()}"/></dd>
                                </c:if>

                                <dt>Category</dt>
                                <dd>${issue.getCategory().getTitle()}</dd>
                            </dl>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </jsp:body>
</t:page>