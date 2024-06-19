<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Admin" %>
<%@ page import="service.impl.ClassServiceIml" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<c:if test="${empty admin}">
    <c:redirect url="http://${header.host}${pageContext.request.contextPath}/login.jsp" />
</c:if>

<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="http://localhost:8080/stu/userImg/默认.jpeg">
            <h1>${admin.name}</h1> <!-- 使用EL表达式访问属性 -->
        </div>
        <div id="menu">
            <ul>
                <li><a href="main.jsp">个人信息</a></li>
                <li><a href="../one_page_teacher_admin">老师管理</a></li>
                <li class="current_page_item"><a href="../one_page_student_admin">学生管理</a></li>
                <li><a href="../one_page_score_admin">成绩管理</a></li>
                <li><a onclick="return confirm('确认退出?');" href="../exit">退出</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>学生信息管理</h2>
            <hr/>
            <button class="btn-add">添加学生</button>
            <div class="find">
                <form action="../one_page_student_admin" method="post">
                    <input id="find-text" type="text" name="key" placeholder="输入学号或姓名搜索">
                    <input class="find-btn" type="submit" value="搜索">
                </form>
            </div>
        </div>
        <div class="table">
            <table id="table" width="800" frame="box" align="center">
                <tr>
                    <th height="35">学号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>入学日期</th>
                    <th>专业</th>
                    <th>班级</th>
                    <th>密码</th>
                    <th>操作</th>
                </tr>
                <c:choose>
                    <c:when test="${not empty stus }">
                        <c:forEach var="stu" items="${stus}">
                            <tr>
                                <form method="post" action="../update_student_admin">
                                    <td height="35">${stu.id}</td>
                                    <td><input value="${stu.name}" name="stuname" class="table-input" style="width: 80px"></td>
                                    <td><input value="${stu.sex}" name="stusex" class="table-input" style="width: 40px"></td>
                                    <td>${stu.school_date}</td>
                                    <td><input value="${stu.major}" name="stumajor" class="table-input" style="width: 100px"></td>
                                    <td>
                                        <select name="stuClass" class="table-select" style="min-width: 50px;">
                                            <c:forEach var="cls" items="${allClasses}">
                                                <option value="${cls.id}" ${stu.studentClass.id eq cls.id ? 'selected' : ''}>${cls.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td><input value="${stu.password}" name="stupassword" class="table-input" style="width: 100px"></td>
                                    <input value="${stu.id}" name="stuno" type="hidden">
                                    <td><input type="submit" class="update-btn" value="修改">&nbsp;
                                        <a class="btn-delete" onclick="return confirm('确定要删除吗?');" href="../delete_student_admin?id=${stu.id}">删除</a>&nbsp;&nbsp;<a href="../one_page_score_admin?id=${stu.id}">查看成绩</a>
                                    </td>
                                </form>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6">未找到匹配的学生信息。</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
        <c:if test="${sumIndex > 1}">
            <div id="index">
                <a href="../one_page_student_admin?index=1">首页</a>
                <c:forEach begin="1" end="${sumIndex}" varStatus="loop">
                    <a href="../one_page_student_admin?index=${loop.index}">第${loop.index}页</a>
                </c:forEach>
                <a href="../one_page_student_admin?index=${sumIndex}">尾页</a>
            </div>
        </c:if>
    </div>
</div>
<div id="add-dialog" title="添加学生信息">
    <form id="add-form" method="post" action="../add_student_admin">
        姓名:<input name="name" type="text"><br>
        性别:<input name="sex" type="text"><br>
        专业:<input name="major" type="text"><br>
        入学日期:<input name="school_date" type="month" style="width: 190px"><br>
        班级:
        <select name="stuClass">
            <c:forEach var="cls" items="${allClasses}">
                <option value="${cls.id}">${cls.name}</option>
            </c:forEach>
        </select><br>
        <hr>
        <input style="float: right" type="button" value="取消" onclick="$('#add-dialog').dialog('close');">
        <input style="float: right; margin-right: 25px" type="submit" value="确定">
    </form>
</div>
<script>
    $('#add-dialog').dialog({
        width: 310,
        autoOpen: false,
        draggable: false,
        modal: true,
        resizable: false
    });
    $('.btn-add').click(function () {
        $('#add-dialog').dialog('open');
    });
</script>
</body>
</html>
