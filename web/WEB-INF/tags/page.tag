<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="title" fragment="true" %>

<html>
<title><jsp:invoke fragment="title"/></title>
<body>
<div id="body">
    <jsp:doBody/>
</div>
</body>
</html>