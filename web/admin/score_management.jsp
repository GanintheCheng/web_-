<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<c:if test="${admin == null}">
    <c:redirect url="login.jsp"/>
</c:if>
<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="http://localhost:8080/stu/userImg/默认.jpeg">
            <h1>${admin.name}</h1>
        </div>
        <div id="menu">
            <ul>
                <li><a href="main.jsp">个人信息</a></li>
                <li><a href="../one_page_teacher_admin">老师管理</a></li>
                <li><a href="../one_page_student_admin">学生管理</a></li>
                <li class="current_page_item"><a href="../one_page_score_admin">成绩管理</a></li>
                <li><a onclick="return confirm('确认退出?');" href="../exit">退出</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>学生成绩管理</h2>
            <hr/>
        </div>
        <form method="post" action="../update_score_admin" style="height: 525px; margin-top: 20px">
            <input type="button" class="btn-add" onclick="location.href='../export_excel';" value="导出EXCEL">
            <input type="submit" class="btn-add" style="float: right;margin-bottom: 30px" value="修改">
            <div class="table" style="margin-top: 20px; height: 525px">
                <table id="table" width="800" frame="box" align="center">
                    <tr>
                        <th height="35">学号</th>
                        <th>姓名</th>
                        <th>专业</th>
                        <th>班级</th>
                        <th>数据库</th>
                        <th>Android</th>
                        <th>JavaWeb</th>
                    </tr>
                    <c:forEach var="score" items="${onePageScore}">
                        <c:set var="student" value="${onePageStudent[onePageScore.indexOf(score)]}"/>
                        <tr>
                            <td height="35">${score.id}</td>
                            <td>${student.name}</td>
                            <td>${student.major}</td>
                            <td>${student.studentClass.name}</td>
                            <td><input value="${score.database}" name="database" class="table-input"></td>
                            <td><input value="${score.android}" name="android" class="table-input"></td>
                            <td><input value="${score.jsp}" name="jsp" class="table-input"></td>
                            <input value="${score.id}" name="id" type="hidden">
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
        <c:if test="${sumIndex > 1}">
            <div id="index">
                <a href="../one_page_score?index=1">首页</a>
                <c:forEach var="i" begin="1" end="${sumIndex}">
                    <a href="../one_page_score_admin?index=${i}">第${i}页</a>
                </c:forEach>
                <a href="../one_page_score_admin?index=${sumIndex}">尾页</a>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
