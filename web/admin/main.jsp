<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Admin" %>
<%@ page import="dao.impl.AdminDImpl" %>
<%-- Created by IntelliJ IDEA.
  User: gzc
  Date: 2024
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/jquery-ui-1.10.4.custom.min.css">
    <script src="../resources/js/jquery-1.10.2.js"></script>
    <script src="../resources/js/jquery-ui-1.10.4.custom.min.js"></script>
    <title>管理员主页</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
</head>
<body>
<%
    String adminId =(String)session.getAttribute("admin");
    Admin admin = new Admin();
    try {
        admin = new AdminDImpl().findWithId(adminId);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
%>
<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src=http://localhost:8080/"+"默认.jpeg"/>
            <h1><%=admin.getId()%></h1>
        </div>
        <div id="menu">
            <ul>
                <li class="current_page_item"><a href="teacher_management.jsp">老师管理</a></li>
                <li><a href="student_management.jsp">学生管理</a></li>
                <li><a href="score_management.jsp">成绩管理</a></li>
                <li><a onclick="return confirm('确认退出?');" href="../exit">退出</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <h2>欢迎管理员</h2>
        <hr/>
        <!-- 这里可以根据需要添加管理员首页的其他内容 -->
    </div>
</div>
</body>
</html>
