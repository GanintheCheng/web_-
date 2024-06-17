package controller;

import dao.impl.ClassDImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Class;
import model.Student;
import service.impl.ClassServiceIml;
import service.impl.StudentServiceIml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/one_page_student_admin")
public class one_page_student_admin extends HttpServlet {
    private final StudentServiceIml studentService = new StudentServiceIml();
    private final ClassServiceIml classService = new ClassServiceIml();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        try {
            List<Class> allClasses = classService.getAllClasses();
            session.setAttribute("allClasses", allClasses);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        String key = request.getParameter("key");

        if (key == null || key.equals("")) {
            handlePagination(request, response, session);
        } else {
            handleSearch(key, request, response, session);
        }
    }

    private void handlePagination(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        int currentIndex = 1;
        int size = 10;
        String indexParam = request.getParameter("index");
        if (indexParam != null) {
            currentIndex = Integer.parseInt(indexParam);
        }

        try {
            ArrayList<Student> stus = studentService.getOnePageStudents(currentIndex, size);
            int count = studentService.getStudentCount();
            int sumIndex = count % size == 0 ? count / size : count / size + 1;
            session.setAttribute("stus", stus);
            session.setAttribute("sumIndex", sumIndex);
            response.sendRedirect(request.getContextPath() + "/admin/student_management.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void handleSearch(String key, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        try {
            if (studentService.isNumeric(key)) {
                Student student = studentService.findStudentWithId(key);
                ArrayList<Student> students = new ArrayList<>();
                students.add(student);
                session.setAttribute("onePageStudent", students);
                session.setAttribute("sumIndex", 1);
                response.sendRedirect(request.getContextPath() + "/admin/student_management.jsp");
            } else {
                ArrayList<Student> stus = studentService.findStudentsWithName(key);
                session.setAttribute("stus", stus);
                session.setAttribute("sumIndex", 1);
                response.sendRedirect(request.getContextPath() + "/admin/student_management.jsp");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
