<%@ page import="dao.impl.ScoreDImpl" %>
<%@ page import="model.Score" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.impl.StudentDImpl" %>
<%@ page import="dao.impl.StudentDImpl" %>
<%--
  Created by IntelliJ IDEA.
  User: gzc
  Date: 2024
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="application/msexcel" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>main</title>
</head>
<body>
<%
    out.clearBuffer();
    response.setHeader("Content-Disposition", "attachment;filename=excel.xls");
%>
<table align="center" border="1">
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
            ScoreDImpl scoD = new ScoreDImpl();
            StudentDImpl stuD = new StudentDImpl();
            ArrayList<Score> stus = scoD.getOnePage(1, 10000);
            for (Score stu : stus) {
                String name = stuD.findWithId(stu.getId()).getName();
                String major = stuD.findWithId(stu.getId()).getMajor();
    %>
    <tr>
            <td align="center"><%=stu.getId()%></td>
            <td align="center"><%=name%></td>
            <td align="center"><%=major%></td>
            <td align="center"><%=stu.getDatabase()%></td>
            <td align="center"><%=stu.getAndroid()%></td>
            <td align="center"><%=stu.getJsp()%></td>
    </tr>
    <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</table>
</body>
</html>

