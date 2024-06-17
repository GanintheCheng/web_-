<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>验证码</title>
    <link rel="stylesheet" href="resources/css/bootstrap.css">
    <link href="resources/css/forget.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<h1 style="margin: 50px 80px; color: darkgray; font-family: cursive;">欢迎来到教务系统</h1>

<c:choose>
    <c:when test="${userType == 'teacher'}">
        <script>alert("发送成功，请到邮箱'${user.email}'查收验证码");</script>
        <div class="main">
            <form role="form" action="teacher/resetPassword.jsp" method="post">
                <div class="form-group" align="center">
                    <input class="form-control" type="text" name="reset" placeholder="验证码"><br>
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" class="btn btn-success" value="下一步">
                    <input type="button" class="btn btn-info" value="取消" style="margin-left: 20px" onclick="window.location.href='login.jsp'">
                </div>
            </form>
        </div>
    </c:when>
    <c:when test="${userType == 'student'}">
        <script>alert("发送成功，请到邮箱'${user.email}'查收验证码");</script>
        <div class="main">
            <form role="form" action="student/resetPassword.jsp" method="post">
                <div class="form-group" align="center">
                    <input class="form-control" type="text" name="reset" placeholder="验证码"><br>
                    <input type="hidden" name="id" value="${user.id}">
                    <input name="email" value="${user.email}" type="hidden">
                    <input type="submit" class="btn btn-success" value="下一步">
                    <input type="button" class="btn btn-info" value="取消" style="margin-left: 20px" onclick="window.location.href='login.jsp'">
                </div>
            </form>
        </div>
    </c:when>
</c:choose>

<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/popper.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
