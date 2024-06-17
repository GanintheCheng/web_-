<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Index Page</title>
</head>
<body>
<c:redirect url="http://${header.host}${pageContext.request.contextPath}/check_cookie"/>
<%--    <c:redirect url="check_cookie"/>--%>
</body>
</html>