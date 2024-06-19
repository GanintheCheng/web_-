<%--
   Created by IntelliJ IDEA.
  User: gzc
  Date: 2024
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
</head>
<body>
<c:if test="${empty info}">
    <c:redirect url="http://${header.host}${pageContext.request.contextPath}/login.jsp"/>
</c:if>
<c:set var="student" value="${info}" />
<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="http://localhost:8080/${student.img}"/>
            <h1>${student.name}</h1>
        </div>
        <div id="menu">
            <ul>
                <li><a href="personal.jsp">个人信息</a></li>
                <li class="current_page_item"><a href="main.jsp">成绩信息</a></li>
                <li><a onclick="return confirm('确认退出?');" href="../exit">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>成绩信息</h2>
            <hr/>
        </div>
        <div class="table">
            <table width="800" frame="box" align="center">
                <tr>
                    <th height="35">学号</th>
                    <th>姓名</th>
                    <th>专业</th>
                    <th>班级</th>
                    <th>数据库</th>
                    <th>Android</th>
                    <th>JavaWeb</th>
                    <th>操作</th>
                </tr>
                <tr>
                    <td height="35">${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.major}</td>
                    <td>${student.studentClass.name}</td>
                    <td>${score.database}</td>
                    <td>${score.android}</td>
                    <td>${score.jsp}</td>
                    <td>
                        <a href="../export_excel?studentId=${student.id}">PDF</a>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<script src="../resources/js/jquery-3.2.1.min.js"></script>
<script src="../resources/js/popper.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
