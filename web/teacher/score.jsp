<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
    <script>
        // Function to validate form inputs before submission
        function validateForm() {
            var database = document.getElementsByName("database")[0].value;
            var android = document.getElementsByName("android")[0].value;
            var jsp = document.getElementsByName("jsp")[0].value;

            if (isNaN(database) || isNaN(android) || isNaN(jsp)) {
                alert("分数必须是数字！");
                return false;
            }

            database = parseFloat(database);
            android = parseFloat(android);
            jsp = parseFloat(jsp);

            if (database <= 0 || database > 100 || android <= 0 || android > 100 || jsp <= 0 || jsp > 100) {
                alert("分数必须在1到100之间！");
                return false;
            }
            alert("修改成功！");
            return true;
        }
    </script>
</head>
<body>
<c:set var="teacher" value="${info}"/>
<c:set var="scores" value="${onePageScore}"/>
<c:set var="sumIndex" value="${sumScoreIndex}"/>

<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="http://localhost:8080/${teacher.img}"/>
            <h1>${teacher.name}</h1>
        </div>
        <div id="menu">
            <ul>
                <li><a href="personal.jsp">个人信息</a></li>
                <li><a href="../one_page_student?index=1&amp;teacherId=${teacher.id}">学生管理</a></li>
                <li class="current_page_item"><a
                        href="../one_page_score?index=1&amp;teacherId=${teacher.id}">成绩管理</a></li>
                <li><a onclick="return confirm('确认退出?');" href="../exit">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>学生成绩管理</h2>
            <hr/>
        </div>
        <c:if test="${not empty scores}">
            <form method="post" action="../update_score?teacherId=${teacher.id}"
                  style="height: 525px; margin-top: 20px" onsubmit="return validateForm()">
                <input type="button" class="btn-add" onclick="location.href='../export_excel?teacherId=${teacher.id}';"
                       value="导出EXCEL">
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
                        <c:forEach var="score" items="${scores}">
                            <c:set var="student" value="${onePageStudent[scores.indexOf(score)]}"/>
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
        </c:if>

        <c:if test="${sumIndex > 1}">
            <div id="index">
                <a href="../one_page_score?index=1&amp;teacherId=${teacher.id}">首页</a>
                <c:forEach begin="1" end="${sumIndex}" var="i">
                    <a href="../one_page_score?index=${i}&amp;teacherId=${teacher.id}">第${i}页</a>
                </c:forEach>
                <a href="../one_page_score?index=${sumIndex}&amp;teacherId=${teacher.id}">尾页</a>
            </div>
        </c:if>

        <c:if test="${empty scores}">
            <div style="text-align: center; margin-top: 50px; font-size: 18px; color: gray;">暂无学生</div>
        </c:if>
    </div>
</div>

<c:if test="${not empty successMessage}">
    <script>
        showSuccessMessage();
    </script>
</c:if>

</body>
</html>
