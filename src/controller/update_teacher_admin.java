package controller;

import dao.impl.StudentDImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.impl.TeacherServiceIml;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update_teacher_admin")
public class update_teacher_admin extends HttpServlet {
    public TeacherServiceIml teacherService = new TeacherServiceIml();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        String teacherId = request.getParameter("teacherId");
        String teacherName = request.getParameter("teacherName");
        String teacherSex = request.getParameter("teacherSex");
        String teacherPassword = request.getParameter("teacherPassword");

        try {
            teacherService.updateTeacherInfo(teacherId, teacherName, teacherSex, teacherPassword);
        } catch (Exception e) {
            out.print(e);
        }
        response.sendRedirect("one_page_teacher_admin");
    }
}
