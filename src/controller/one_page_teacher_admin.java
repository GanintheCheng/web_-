package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Class;
import model.Teacher;
import service.impl.ClassServiceIml;
import service.impl.TeacherServiceIml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/one_page_teacher_admin")
public class one_page_teacher_admin extends HttpServlet {
    private final TeacherServiceIml teacherServiceIml = new TeacherServiceIml();
    private final ClassServiceIml classService = new ClassServiceIml();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

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
            if (index == null)
                index = "1";
            currentIndex = Integer.parseInt(index);

            try {
                ArrayList<Teacher> teachers = teacherServiceIml.getOnePageTeachers(currentIndex, size);
                count = teacherServiceIml.getTeacherCount();
                int sumIndex = count % size == 0 ? count / size : count / size + 1;
                session.setAttribute("teachers", teachers);
                session.setAttribute("sumIndex", sumIndex);
                response.sendRedirect("admin/teacher_management.jsp");
            } catch (Exception e) {
                // 处理异常
                e.printStackTrace();
                response.getWriter().println("Error: " + e.getMessage());
            }
        } else {
            if (teacherServiceIml.isNumeric(key)) {
                try {
                    Teacher teacher = teacherServiceIml.findTeacherWithId(key);
                    ArrayList<Teacher> teachers = new ArrayList<>();
                    teachers.add(teacher);
                    session.setAttribute("teachers", teachers);
                    session.setAttribute("sumIndex", 1);
                    response.sendRedirect("admin/teacher_management.jsp");
                } catch (Exception e) {
                    // 处理异常
                    e.printStackTrace();
                    response.getWriter().println("Error: " + e.getMessage());
                }
            } else {
                try {
                    ArrayList<Teacher> teachers = teacherServiceIml.findTeachersWithName(key);
                    if (teachers.size() == 0) {
                        teachers.add(null);
                    }
                    session.setAttribute("teachers", teachers);
                    session.setAttribute("sumIndex", 1);
                    response.sendRedirect("admin/teacher_management.jsp");
                } catch (Exception e) {
                    // 处理异常
                    e.printStackTrace();
                    response.getWriter().println("Error: " + e.getMessage());
                }
            }
        }
    }
}
