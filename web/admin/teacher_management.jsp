<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Admin" %>
<%@ page import="dao.impl.AdminDImpl" %>
<%@ page import="model.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Class" %>
<%@ page import="service.impl.ClassServiceIml" %>
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
    String adminId = (String) session.getAttribute("admin");
    Admin admin;
    try {
        admin = new AdminDImpl().findWithAccount(adminId);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    ArrayList<Teacher> teachers = (ArrayList<Teacher>) session.getAttribute("onePageTeachers");
    int sumIndex = (int) session.getAttribute("sumIndex");
%>
<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src=http://localhost:8080/stu/userImg/默认.jpeg>
            <h1><%=admin.getName()%>
            </h1>
        </div>
        <div id="menu">
            <ul>
                <li><a href="main.jsp">个人信息</a></li>
                <li class="current_page_item"><a href="../one_page_teacher_admin">老师管理</a></li>
                <li><a href="../one_page_student_admin">学生管理</a></li>
                <li><a href="../one_page_score_admin">成绩管理</a></li>
                <li><a onclick="return confirm('确认退出?');" href="../exit">退出</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>教师信息管理</h2>
            <hr/>
            <button class="btn-add">添加教师</button>
            <div class="find">
                <form action="../one_page_teacher_admin" method="post">
                    <input id="find-text" type="text" name="key" placeholder="输入教师编号或姓名搜索">
                    <input class="find-btn" type="submit" value="搜索">
                </form>
            </div>
        </div>
        <div class="table">
            <table id="table" width="800" frame="box" align="center">
                <tr>
                    <th height="35">编号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>密码</th>
                    <th>班级</th>
                    <th>操作</th>
                </tr>
                <%
                    if (!teachers.isEmpty() && teachers.get(0) != null) {
                        for (Teacher teacher : teachers) {
                %>
                <tr>
                    <form method="post" action="../update_teacher_admin">
                        <td height="35"><%= teacher.getId() %>
                        </td>
                        <td>
                            <input value="<%= teacher.getName() %>" name="teacherName" class="table-input"
                                   style="min-width: 80px">
                        </td>
                        <td>
                            <input value="<%= teacher.getSex() %>" name="teacherSex" class="table-input">
                        </td>
                        <td>
                            <input value="<%= teacher.getPassword() %>" name="teacherPassword" class="table-input"
                                   style="min-width: 100px">
                        </td>
                        <td>
                            <select name="teacherClasses" class="table-select" multiple style="min-width: 150px;">
                                <%
                                    List<Class> allClasses = null; // 获取所有班级列表
                                    try {
                                        allClasses = new ClassServiceIml().getAllClasses();
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                    List<Class> teacherClasses = teacher.getClassList(); // 获取教师所管理的班级列表
                                    for (Class cls : allClasses) {
                                        boolean isSelected = teacherClasses.contains(cls);
                                %>
                                <option value="<%= cls.getId() %>" <%= isSelected ? "selected" : "" %>><%= cls.getName() %>
                                </option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                        <input value="<%= teacher.getId() %>" name="teacherId" type="hidden">
                        <td>
                            <input type="submit" class="update-btn" value="修改">&nbsp;
                            <a class="btn-delete" onclick="return confirm('确定要删除吗?');"
                               href="<%="../delete_teacher_admin?id=" + teacher.getId()%>">删除</a>
                        </td>
                    </form>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="6">未找到匹配的教师信息。</td>
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
            <a href="../one_page_teacher_admin?index=1">首页</a>
            <%
                for (int i = 1; i <= sumIndex; i++) {
            %>
            <a href="../one_page_teacher_admin?index=<%=i%>">第<%=i%>页</a>
            <%
                }
            %>
            <a href="../one_page_teacher_admin?index=<%=sumIndex%>">尾页</a>
        </div>
        <%
            }
        %>
    </div>
</div>
<div id="add-dialog" title="添加教师信息">
    <form id="add-form" method="post">
        <%--        编号:<input name="teacherId" type="text"><br>--%>
        姓名:<input name="teacherName" type="text"><br>
        密码:<input name="teacherPassword" type="text"><br>
        性别:<input name="teacherSex" type="text"><br>
        邮箱:<input name="teacherEmail" type="text"><br>
        <hr>
        <input style="float: right" type="submit" value="取消" onclick="function x() {
          $('#add-dialog').dialog('close');
        }">
        <input style="float: right; margin-right: 25px" type="submit" value="确定"
               onclick="this.form.action='../add_teacher_admin'">
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
