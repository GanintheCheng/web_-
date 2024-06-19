<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/jquery-ui-1.10.4.custom.min.css">
    <script src="../resources/js/jquery-1.10.2.js"></script>
    <script src="../resources/js/jquery-ui-1.10.4.custom.min.js"></script>
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
    <script>
        $(document).ready(function () {
            var successMessage = '${sessionScope.successMessage}';
            if (successMessage) {
                alert(successMessage);
                <% session.removeAttribute("successMessage"); %>
            }
        });

        function validateForm(form) {
            var name = form.elements['stuname'].value.trim();
            var sex = form.elements['stusex'].value.trim();

            if (name === '' || (sex !== '男' && sex !== '女')) {
                alert('姓名不能为空且性别必须为"男"或"女"！');
                return false;
            } else {
                alert('修改成功！');
                return true;
            }
        }
    </script>
</head>
<body>
<c:if test="${empty info}">
    <c:redirect url="http://${header.host}${pageContext.request.contextPath}/login.jsp"/>
</c:if>
<c:set var="teacher" value="${info}"/>
<c:set var="classList" value="${teacher.classList}"/>

<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="http://localhost:8080/${teacher.img}"/>
            <h1>${teacher.name}</h1>
        </div>
        <div id="menu">
            <ul>
                <li><a href="personal.jsp">个人信息</a></li>
                <li class="current_page_item"><a
                        href="../one_page_student?index=1&amp;teacherId=${teacher.id}">学生管理</a></li>
                <li><a href="../one_page_score?index=1&amp;teacherId=${teacher.id}">成绩管理</a></li>
                <li><a onclick="return confirm('确认退出?');" href="../exit">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>学生信息管理</h2>
            <hr/>
            <c:if test="${not empty classList and classList[0].id != 0}">
                <button class="btn-add">添加学生</button>
            </c:if>
            <div class="find">
                <form action="../one_page_student?teacherId=${teacher.id}" method="post">
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
                    <th>班级</th>
                    <th>入学日期</th>
                    <th>专业</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${stus}" var="stu">
                    <c:set var="isInTeacherClass" value="false"/>
                    <c:forEach items="${classList}" var="clazz">
                        <c:if test="${stu.studentClass.id == clazz.id}">
                            <c:set var="isInTeacherClass" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:if test="${isInTeacherClass}">
                        <tr>
                            <form method="post" action="../update_student?teacherId=${teacher.id}" onsubmit="return validateForm(this);">
                                <td height="35">${stu.id}</td>
                                <td><input value="${stu.name}" name="stuname" class="table-input" style="width: 50px">
                                </td>
                                <td><input value="${stu.sex}" name="stusex" class="table-input" style="width: 50px">
                                </td>
                                <td>${stu.studentClass.name}</td>
                                <td>${stu.school_date}</td>
                                <td>${stu.major}</td>
                                <input value="${stu.id}" name="stuno" type="hidden">
                                <td>
                                    <input type="submit" class="update-btn" value="修改">&nbsp;
                                    <a class="btn-delete" onclick="return confirm('确定要删除吗?');"
                                       href="../delete_student?id=${stu.id}&amp;teacherId=${teacher.id}">删除</a>&nbsp;&nbsp;
                                    <a href="../one_page_score?id=${stu.id}">查看成绩</a>
                                </td>
                            </form>
                        </tr>
                    </c:if>
                </c:forEach>
                <c:if test="${empty stus or stus[0] == null}">
                    <tr>
                        <td colspan="7">未找到匹配的学生信息。</td>
                    </tr>
                </c:if>
            </table>
        </div>
        <c:if test="${sumIndex > 1}">
            <div id="index">
                <a href="../one_page_student?index=1&amp;teacherId=${teacher.id}">首页</a>
                <c:forEach begin="1" end="${sumIndex}" varStatus="loop">
                    <a href="../one_page_student?index=${loop.index}&amp;teacherId=${teacher.id}">第${loop.index}页</a>
                </c:forEach>
                <a href="../one_page_student?index=${sumIndex}&amp;teacherId=${teacher.id}">尾页</a>
            </div>
        </c:if>
    </div>
</div>

<div id="add-dialog" title="添加学生信息">
    <form id="add-form" method="post" action="../add_student?teacherId=${teacher.id}">
        姓名:<input name="name" type="text"><br>
        性别:<input name="sex" type="text"><br>
        专业:<input name="major" type="text"><br>
        班级:
        <select name="class">
            <c:forEach items="${classList}" var="clazz">
                <option value="${clazz.id}">${clazz.name}</option>
            </c:forEach>
        </select><br>
        入学日期:<input name="school_date" type="month" style="width: 190px">
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
