<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Admin" %>
<%@ page import="dao.impl.AdminDImpl" %>
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

<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="http://localhost:8080/stu/userImg/默认.jpeg">
            <h1>${not empty admin ? admin.name : ''}</h1>
        </div>
        <div id="menu">
            <ul>
                <li class="current_page_item"><a href="main.jsp">个人信息</a></li>
                <li><a href="../one_page_teacher_admin">老师管理</a></li>
                <li><a href="../one_page_student_admin">学生管理</a></li>
                <li><a href="../one_page_score_admin">成绩管理</a></li>
                <li><a onclick="return confirm('确认退出?');" href="../exit">退出</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <h2>欢迎管理员</h2>
        <hr/>
        <div class="info">
            <img src="http://localhost:8080/stu/userImg/默认.jpeg" class="personalImg"><br>
            <form action="../upload_teacherImg" method="post" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${not empty admin ? admin.account : ''}">
            </form>
            <form method="post" action="../update_admin" class="personalForm">
                <input name="uid" value="${not empty admin ? admin.id : ''}" type="hidden">
                账号: <input type="text" name="account" value="${not empty admin ? admin.account : ''}" class="personalInput"><br>
                姓名: <input type="text" name="name" value="${not empty admin ? admin.name : ''}" class="personalInput"><br>
                密码: <input type="text" name="password" value="${not empty admin ? admin.password : ''}" class="personalInput"><br>
                <input type="submit" value="保存" style="width: 100px; height: 30px; margin-top: 20px">
            </form>
        </div>
    </div>
</div>
</body>
</html>
