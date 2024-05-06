package controller;

import dao.impl.TeacherDImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update_teacher_password")
public class update_teacher_password extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        TeacherDImpl teacherDImpl = new TeacherDImpl();

        String id = request.getParameter("id");
        String password = request.getParameter("password");

        try {
            teacherDImpl.updateTeacherPassword(id, password);
            out.print("<script>alert(\"修改成功\");window.location.href='login.jsp';</script>");
        } catch (Exception e) {
            out.print(e);
        }
    }
}