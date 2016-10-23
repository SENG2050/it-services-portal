<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<t:plain>
    <jsp:attribute name="title">
        Related Knowledge Base Articles
    </jsp:attribute>
    <jsp:body>
        <c:if test="${issues.size() > 0}">
            <section class="css-section">
                <h2 class="section-title">Related Knowledge Base Articles</h2>

                <div class="row">
                    <c:forEach items="${issues}" var="issue">
                        <div class="col-sm-6 m-b-md">
                            <div class="content-box box-primary">
                                <h3 class="content-box-title">${issue.getTitle()}</h3>

                                <p>${issue.getDescription()}</p>

                                <a href="${baseURL}kb/${issue.getIssueId()}" target="_blank"
                                   class="btn btn-ar btn-default">
                                    <i class="fa fa-fw fa-search"></i>
                                    Read More
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </c:if>
    </jsp:body>
</t:plain>