package controller;

import dao.impl.ScoreDImpl;
import dao.impl.StudentDImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Teacher;
import service.StudentService;
import service.impl.StudentServiceIml;
import service.impl.TeacherServiceIml;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add_teacher_admin")
public class add_teacher_admin extends HttpServlet {
    private final TeacherServiceIml teacherService = new TeacherServiceIml();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();


        String id = request.getParameter("teacherId");
        String name = request.getParameter("teacherName");
        String password = request.getParameter("teacherPassword");
        String sex = request.getParameter("teacherSex");
        String email = request.getParameter("teacherEmail");

        try {
            teacherService.addTeacher(id, name,password, sex, email);
        } catch (Exception e) {
            out.print(e);
        }
        response.sendRedirect("one_page_teacher_admin");
    }
}
