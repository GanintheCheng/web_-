<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
    <script>
        function validateForm() {
            var password = document.forms["updateForm"]["password"].value;
            var email = document.forms["updateForm"]["email"].value;
            var sex = document.forms["updateForm"]["sex"].value.toLowerCase(); // Convert to lowercase for case-insensitive check

            if (password.length < 6) {
                alert("密码必须大于等于6位");
                return false;
            }

            var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailPattern.test(email)) {
                alert("请输入正确的邮箱格式");
                return false;
            }

            // Sex check
            if (sex !== "男" && sex !== "女") {
                alert("性别必须为男或女");
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
<c:set var="teacher" value="${info}" />

<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="http://localhost:8080/${teacher.img}"/>
            <h1>${teacher.name}</h1>
        </div>
        <div id="menu">
            <ul>
                <li class="current_page_item"><a href="personal.jsp">个人信息</a></li>
                <li><a href="../one_page_student?index=1&amp;teacherId=${teacher.id}">学生管理</a></li>
                <li><a href="../one_page_score?index=1&amp;teacherId=${teacher.id}">成绩管理</a></li>
                <li><a onclick="return confirm('确认退出?');" href="../exit">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>个人信息</h2>
            <hr/>
        </div>
        <div class="info">
            <img src="http://localhost:8080/${teacher.img}" class="personalImg"><br>
            <form action="../upload_teacherImg" method="post" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${teacher.id}">
                <input type="file" name="img">
                <input type="submit" value="上传头像">
            </form>
            <form method="post" action="../update_teacher" class="personalForm" name="updateForm" onsubmit="return validateForm()">
                <input name="uid" value="${teacher.id}" type="hidden">
                用户名:<input type="text" value="${teacher.account}" class="personalInput" readonly onmousedown ><br>
                姓名: <input type="text" name="name" value="${teacher.name}" class="personalInput" required><br>
                管理班级: <input type="text" name="name" value="${teacher.classListString}" class="personalInput" readonly onmousedown><br>
                性别: <input type="text" name="sex" value="${teacher.sex}" class="personalInput"><br>
                邮箱: <input type="email" name="email" value="${teacher.email}" class="personalInput" required><br>
                密码: <input type="password" name="password" value="${teacher.password}" class="personalInput" required><br>
                <input type="submit" value="保存" style="width: 100px; height: 30px; margin-top: 20px">
            </form>
        </div>
    </div>
</div>
</body>
</html>
