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

@WebServlet("/update_student_admin")
public class update_student_admin extends HttpServlet {
    public StudentServiceIml studentService = new StudentServiceIml();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        String stuno = request.getParameter("stuno");
        String stuname = request.getParameter("stuname");
        String stusex = request.getParameter("stusex");
        String stumajor = request.getParameter("stumajor");
        String stupassword = request.getParameter("stupassword");

        try {
            studentService.updateStudentInfo(stuno, stuname, stusex, stumajor,stupassword);
        } catch (Exception e) {
            out.print(e);
        }
        response.sendRedirect("one_page_student_admin");
    }
}

