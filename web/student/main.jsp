<%@ page import="dao.impl.StudentDImpl" %>
<%@ page import="model.Student" %>
<%@ page import="dao.impl.ScoreDImpl" %>
<%@ page import="model.Score" %>
<%@ page import="dao.impl.StudentDImpl" %>
<%@ page import="model.Class" %>
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
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
</head>
<body>
<%
    Student student = (Student) session.getAttribute("info");
    try {
        student = new StudentDImpl().findWithId(student.getId());
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
%>
<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="<%="http://localhost:8080/"+student.getImg()%>"/>
            <h1><%=student.getName()%>
            </h1>
        </div>
        <div id="menu">
            <ul>
                <li><a href="personal.jsp">个人信息</a></li>
                <li class="current_page_item"><a href="main.jsp">成绩信息</a></li>
                <li><a onclick="return confirm('确认退出?');" href="../exit">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>成绩信息</h2>
            <hr/>
        </div>
        <div class="table">
            <table width="800" frame="box" align="center">
                <tr>
                    <th height="35">学号</th>
                    <th>姓名</th>
                    <th>专业</th>
                    <th>班级</th>
                    <th>数据库</th>
                    <th>Android</th>
                    <th>JavaWeb</th>
                    <th>操作</th>
                </tr>
                <%
                    try {
                        ScoreDImpl scoD = new ScoreDImpl();
                        StudentDImpl stuD = new StudentDImpl();
                        Score stu = scoD.findWithId(student.getId());
                        String name = stuD.findWithId(student.getId()).getName();
                        String major = stuD.findWithId(student.getId()).getMajor();
                        Class stuClass = stuD.findWithId(student.getId()).get_class();
                %>
                <tr>
                    <td height="35"><%=stu.getId()%>
                    </td>
                    <td><%=name%>
                    </td>
                    <td><%=major%>
                    </td>
                    <td><%=stuClass.getName()%>
                    </td>
                    <td><%=stu.getDatabase()%>
                    </td>
                    <td><%=stu.getAndroid()%>
                    </td>
                    <td><%=stu.getJsp()%>
                    </td>
                    <td>
                        <a href="pdf.jsp?id=<%=stu.getId()%>&name=<%=name%>&major=<%=major%>&stuClass=<%=stuClass.getName()%>&database=<%=stu.getDatabase()%>&android=<%=stu.getAndroid()%>&jsp=<%=stu.getJsp()%>">PDF</a>
                    </td>
                </tr>
                <%
                    } catch (Exception e) {
                        out.print(e);
                    }
                %>
            </table>
        </div>
    </div>
</div>
</body>
</html>

