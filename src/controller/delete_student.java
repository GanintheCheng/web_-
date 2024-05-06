package controller;

import dao.impl.ScoreDImpl;
import dao.impl.StudentDImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.impl.StudentServiceIml;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/delete_student")
public class delete_student extends HttpServlet {
    public StudentServiceIml student=new StudentServiceIml();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        try {
            student.deleteStudent(id);
            response.sendRedirect("one_page_student");
        } catch (Exception e) {
            out.print(e);
        }
    }
}
