package controller;

import model.Class;
import model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Teacher;
import service.impl.ClassServiceIml;
import service.impl.StudentServiceIml;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/one_page_student")
public class OnePageStudentServlet extends HttpServlet {
    private final StudentServiceIml studentService = new StudentServiceIml();
    private final ClassServiceIml classService = new ClassServiceIml();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try {
            List<Class> allClasses = classService.getAllClasses();
            session.setAttribute("allClasses", allClasses);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String key = request.getParameter("key");

        if (key == null || key.equals("")) {
            int currentIndex, count, size = 10;
            String index = request.getParameter("index");
            String teacherId = request.getParameter("teacherId");
            teacherId = (((Teacher)(session.getAttribute("info"))).getId());
            if (index == null)
                index = "1";
            currentIndex = Integer.parseInt(index);

            try {
                ArrayList<Student> stus = studentService.getOnePageStudents(currentIndex, size, teacherId);
                count = studentService.getStudentCount(teacherId);
                int sumIndex = count % size == 0 ? count / size : count / size + 1;
                session.setAttribute("stus", stus);
//                session.setAttribute("info", session.getAttribute("info"));
                session.setAttribute("sumIndex", sumIndex);
                response.sendRedirect("teacher/main.jsp");
            } catch (Exception e) {
                out.print(e);
            }
        } else {
            if (studentService.isNumeric(key)) {
                try {
                    Student student = studentService.findStudentWithId(key);
                    ArrayList<Student> students = new ArrayList<>();
                    students.add(student);
                    session.setAttribute("stus", students);
                    session.setAttribute("sumIndex", 1);
                    response.sendRedirect("teacher/main.jsp");
                } catch (Exception e) {
                    out.print(e);
                }
            } else {
                try {
                    ArrayList<Student> stus = studentService.findStudentsWithName(key);
                    session.setAttribute("stus", stus);
                    session.setAttribute("sumIndex", 1);
                    response.sendRedirect("teacher/main.jsp");
                } catch (Exception e) {
                    out.print(e);
                }
            }
        }
    }
}
