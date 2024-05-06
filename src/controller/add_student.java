package controller;

import dao.impl.ScoreDImpl;
import dao.impl.StudentDImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StudentService;
import service.impl.StudentServiceIml;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add_student")
public class add_student extends HttpServlet {
    private final StudentServiceIml studentService;

    public add_student() {
        this.studentService = new StudentServiceIml();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        StudentDImpl studentDImpl = new StudentDImpl();
        ScoreDImpl scoreDImpl = new ScoreDImpl();

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String major = request.getParameter("major");
        String school_date = request.getParameter("school_date");

        try {
            studentService.addStudent(id, name, sex, major, school_date);
        } catch (Exception e) {
            out.print(e);
        }
        response.sendRedirect("one_page_student");
    }
}
