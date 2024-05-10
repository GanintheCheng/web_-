package controller;

import dao.impl.StudentDImpl;
import model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.impl.StudentServiceIml;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

@WebServlet("/one_page_student_admin")
public class one_page_student_admin extends HttpServlet {
    private final StudentServiceIml studentService = new StudentServiceIml();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String key = request.getParameter("key");

        if (key == null || key.equals("")) {
            int currentIndex, count, size = 10;
            String index = request.getParameter("index");
            if (index == null)
                index = "1";
            currentIndex = Integer.parseInt(index);

            try {
                ArrayList<Student> stus = studentService.getOnePageStudents(currentIndex, size);
                count = studentService.getStudentCount();
                int sumIndex = count % size == 0 ? count / size : count / size + 1;
                session.setAttribute("onePageStudent", stus);
                session.setAttribute("sumIndex", sumIndex);
                response.sendRedirect("admin/student_management.jsp");
            } catch (Exception e) {
                out.print(e);
            }
        } else {
            if (studentService.isNumeric(key)) {
                try {
                    Student student = studentService.findStudentWithId(key);
                    ArrayList<Student> students = new ArrayList<>();
                    students.add(student);
                    session.setAttribute("onePageStudent", students);
                    session.setAttribute("sumIndex", 1);
                    response.sendRedirect("admin/student_management.jsp");
                } catch (Exception e) {
                    out.print(e);
                }
            } else {
                try {
                    ArrayList<Student> stus = studentService.findStudentsWithName(key);
                    session.setAttribute("onePageStudent", stus);
                    session.setAttribute("sumIndex", 1);
                    response.sendRedirect("admin/student_management.jsp");
                } catch (Exception e) {
                    out.print(e);
                }
            }
        }
    }
}
