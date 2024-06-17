<%--
   Created by IntelliJ IDEA.
  User: gzc
  Date: 2024
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重置密码</title>
    <link rel="stylesheet" href="../resources/css/bootstrap.css">
    <link href="../resources/css/forget.css" type="text/css" rel="stylesheet" />
</head>
<body>
<h1 style="margin: 50px 80px; color: darkgray; font-family: cursive;">欢迎来到教务系统</h1>
<c:set var="resetCode" value="${sessionScope.reset}" />
<c:choose>
    <c:when test="${param.reset != resetCode}">
        <script>alert("验证码错误!"); window.location.href='../forget.jsp';</script>
    </c:when>
    <c:otherwise>
        <div class="main">
            <form role="form" action="../update_teacher_password" method="post">
                <div class="form-group" align="center">
                    <input type="text" class="form-control" name="password" placeholder="新密码"><br>
                    <input type="hidden" name="id" value="${param.id}">
                    <input type="submit" class="btn btn-success" value="提交">
                    <input type="button" class="btn btn-info" value="取消" style="margin-left: 20px" onclick="window.location.href='../login.jsp'">
                </div>
            </form>
        </div>
    </c:otherwise>
</c:choose>
<script src="../resources/js/jquery-3.2.1.min.js"></script>
<script src="../resources/js/popper.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
