<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<t:page>
    <jsp:attribute name="title">
        Issues
    </jsp:attribute>
    <jsp:attribute name="breadcrumbs">
        <ol class="breadcrumb pull-right">
            <li class="active">Issues</li>
        </ol>
    </jsp:attribute>
    <jsp:body>
        <c:forEach items="${issues}" var="item">
            <p>Issue</p>
        </c:forEach>
    </jsp:body>
</t:page>