<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Admin" %>
<%@ page import="dao.impl.AdminDImpl" %>
<%@ page import="model.Score" %>
<%@ page import="dao.impl.StudentDImpl" %>
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
    Admin admin = new Admin();
    try {
        admin = new AdminDImpl().findWithAccount(adminId);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    ArrayList<Score> stus = (ArrayList<Score>) session.getAttribute("onePageScore");
    int sumIndex = (int) session.getAttribute("sumScoreIndex");
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
            <input type="button" class="btn-add" onclick="location.href='score_excel.jsp';" value="导出EXCEL">
            <input type="submit" class="btn-add" style="float: right;margin-bottom: 30px" value="修改">
            <div class="table" style="margin-top: 20px; height: 525px">
                <table id="table" width="800" frame="box" align="center">
                    <tr>
                        <th height="35">学号</th>
                        <th>姓名</th>
                        <th>专业</th>
                        <th>数据库</th>
                        <th>Android</th>
                        <th>JavaWeb</th>
                    </tr>
                    <%
                        try {
                            StudentDImpl stuD = new StudentDImpl();
                            for (Score stu : stus) {
                                String name = stuD.findWithId(stu.getId()).getName();
                                String major = stuD.findWithId(stu.getId()).getMajor();
                    %>
                    <tr>
                        <td height="35"><%=stu.getId()%></td>
                        <td><%=name%></td>
                        <td><%=major%></td>
                        <td><input value="<%=stu.getDatabase()%>" name="database" class="table-input"></td>
                        <td><input value="<%=stu.getAndroid()%>" name="android" class="table-input"></td>
                        <td><input value="<%=stu.getJsp()%>" name="jsp" class="table-input"></td>
                        <input value="<%=stu.getId()%>" name="id" type="hidden">
                    </tr>
                    <%
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    %>
                </table>

            </div>
        </form>

        <%
            if (sumIndex > 1){
        %>
                <div id="index">
                    <a href="../one_page_score?index=1">首页</a>
                    <%
                        for (int i = 1; i <= sumIndex; i++) {
                    %>
                    <a href="../one_page_score_admin?index=<%=i%>">第<%=i%>页</a>
                    <%
                        }
                    %>
                    <a href="../one_page_score_admin?index=<%=sumIndex%>">尾页</a>
                </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>