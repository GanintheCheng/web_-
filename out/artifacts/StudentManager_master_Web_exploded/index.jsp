<%@ page import="dao.impl.TeacherDImpl" %>
<%@ page import="dao.impl.StudentDImpl" %>
<%@ page import="model.Teacher" %>
<%@ page import="model.Student" %>
<%@ page import="dao.impl.StudentDImpl" %>
<%@ page import="dao.impl.TeacherDImpl" %>
<%@ page import="jakarta.servlet.http.Cookie" %>
<%--
   Created by IntelliJ IDEA.
  User: gzc
  Date: 2024
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%
    TeacherDImpl teacherDImpl = new TeacherDImpl();
    StudentDImpl studentDImpl = new StudentDImpl();
    Teacher teacher = null;
    Student student = null;

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            String cookieName = c.getName();
            if ("name".equals(cookieName)) {
                String user = c.getValue();
                try {
                    teacher = teacherDImpl.findWithId(user);
                    student = studentDImpl.findWithId(user);
                } catch (Exception e) {
                    out.print(e);
                }
                if (teacher != null) {
                    session.setAttribute("info", teacher);
                    response.sendRedirect("one_page_student");
                    return;
                }
                else if(student != null){
                    session.setAttribute("info", student);
                    response.sendRedirect("student/main.jsp");
                    return;
                }
            }
        }
    }
    response.sendRedirect("login.jsp");
%>
</body>
</html>
