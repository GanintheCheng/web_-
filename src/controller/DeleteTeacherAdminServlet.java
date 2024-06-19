package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.impl.TeacherServiceIml;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/delete_teacher_admin")
public class DeleteTeacherAdminServlet extends HttpServlet {
    public TeacherServiceIml teacher = new TeacherServiceIml();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        try {
            teacher.deleteTeacher(id);
            response.sendRedirect("one_page_teacher_admin");
        } catch (Exception e) {
            out.print(e);
        }
    }
}
