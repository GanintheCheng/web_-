<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Student" %>
<%@ page import="model.Teacher" %>
<%@ page import="dao.impl.TeacherDImpl" %>
<%@ page import="dao.impl.TeacherDImpl" %>
<%--
   Created by IntelliJ IDEA.
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
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
</head>
<body>
<%
    Teacher teacher = (Teacher) session.getAttribute("info");
    try {
        teacher = new TeacherDImpl().findWithId(teacher.getId());
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    ArrayList<Student> stus = (ArrayList<Student>) session.getAttribute("onePageStudent");
    int sumIndex = (int) session.getAttribute("sumIndex");
%>
<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="<%="http://localhost:8080/"+teacher.getImg()%>"/>
            <h1><%=teacher.getName()%>
            </h1>
        </div>
        <div id="menu">
            <ul>
                <li><a href="personal.jsp">个人信息</a></li>
                <li class="current_page_item"><a href="../one_page_student?index=1&teacherId=<%= teacher.getId() %>">学生管理</a>
                </li>
                <li><a href="../one_page_score?index=1&teacherId=<%= teacher.getId() %>">成绩管理</a></li>
                <li><a onclick="return confirm('确认退出?');" href="../exit">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>学生信息管理</h2>
            <hr/>
            <button class="btn-add">添加学生</button>
            <div class="find">
                <form action="../one_page_student?teacherId=<%= teacher.getId() %>" method="post">
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
                <%
                    if (!stus.isEmpty() && stus.get(0) != null) {
                        for (Student stu : stus) {
//                            if (!teacher.getClassList().contains(stu.get_class())) {
//                                continue;
//                            } else counts++;
                %>
                <tr>
                    <form method="post" action="../update_student?teacherId=<%=teacher.getId()%>">
                        <td height="35"><%=stu.getId()%>
                        </td>
                        <td><input value="<%=stu.getName()%>" name="stuname" class="table-input" style="width: 50px">
                        </td>
                        <td><input value="<%=stu.getSex()%>" name="stusex" class="table-input" style="width: 50px"></td>
                        <td><%=stu.get_class().getName()%>
                        </td>
                        <td><%=stu.getSchool_date()%>
                        </td>
                        <td><%=stu.getMajor()%>
                        </td>
                        <input value="<%=stu.getId()%>" name="stuno" type="hidden">
                        <td><input type="submit" class="update-btn" value="修改">&nbsp;
                            <a class="btn-delete"
                               onclick="return confirm('确定要删除吗?');"
                               href=<%="'../delete_student?id=" + stu.getId() + "&teacherId=" + teacher.getId() + "'"%>>删除</a>&nbsp;&nbsp;<a
                                    href="../one_page_score?id=<%=stu.getId()%>">查看成绩</a>
                        </td>
                    </form>
                </tr>
                <%
                    }
//                    if(counts>0)sumIndex = counts / 10 + 1;
                } else {
                %>
                <tr>
                    <td colspan="6">未找到匹配的学生信息。</td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
        <%
            if (sumIndex > 1) {
        %>
        <div id="index">
            <a href="../one_page_student?index=1&teacherId=<%= teacher.getId() %>">首页</a>
            <%
                for (int i = 1; i <= sumIndex; i++) {
            %>
            <a href="../one_page_student?index=<%= i %>&teacherId=<%= teacher.getId() %>">第<%= i %>页</a>
            <%
                }
            %>
            <a href="../one_page_student?index=<%= sumIndex %>&teacherId=<%= teacher.getId() %>">尾页</a>
        </div>
        <%
            }
        %>
    </div>
</div>

<%--添加学生信息对话框--%>
<div id="add-dialog" title="添加学生信息">
    <form id="add-form" method="post">
        姓名:<input name="name" type="text"><br>
        性别:<input name="sex" type="text"><br>
        专业:<input name="major" type="text"><br>
        班级:
        <select name="class">
            <option value="1">软件工程1班</option>
            <option value="2">软件工程2班</option>
            <option value="3">软件工程3班</option>
        </select><br>
        入学日期:<input name="school_date" type="month" style="width: 190px">
        <hr>
        <input style="float: right" type="button" value="取消" onclick="$('#add-dialog').dialog('close');">
        <input style="float: right; margin-right: 25px" type="submit" value="确定"
               onclick="this.form.action='../add_student?teacherId=<%= teacher.getId() %>'">
    </form>
</div>

<style>
    .ui-dialog-titlebar-close {
        display: none
    }
</style>

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

