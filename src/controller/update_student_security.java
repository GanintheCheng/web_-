package controller;

import dao.impl.StudentDImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.impl.StudentServiceIml;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update_student_security")
public class update_student_security extends HttpServlet {
    private final StudentServiceIml serviceIml = new StudentServiceIml();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            serviceIml.updateStudentSecurity(id, email, password);
            out.print("<script>alert(\"修改成功,请重新登录\");window.location.href='login.jsp';</script>");
        } catch (Exception e) {
            out.print(e);
        }
    }
}
