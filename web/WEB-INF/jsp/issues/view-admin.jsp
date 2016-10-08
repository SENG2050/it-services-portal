<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page>
    <jsp:attribute name="title">
        Issues View Admin Page
    </jsp:attribute>
    <jsp:body>
        ${issue.getTitle()}
    </jsp:body>
</t:page>