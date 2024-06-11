package controller;

import dao.impl.StudentDImpl;
import model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Teacher;
import service.impl.StudentServiceIml;
import service.impl.TeacherServiceIml;
import util.factory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

@WebServlet("/one_page_teacher_admin")
public class one_page_teacher_admin extends HttpServlet {
    private final TeacherServiceIml teacherServiceIml = new TeacherServiceIml();

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
                ArrayList<Teacher> teachers = teacherServiceIml.getOnePageTeachers(currentIndex, size);
                count = teacherServiceIml.getTeacherCount();
                int sumIndex = count % size == 0 ? count / size : count / size + 1;
                session.setAttribute("onePageTeachers", teachers);
                session.setAttribute("sumIndex", sumIndex);
                response.sendRedirect("admin/teacher_management.jsp");
            } catch (Exception e) {
                out.print(e);
            }
        } else {
            if (teacherServiceIml.isNumeric(key)) {
                try {
                    Teacher teacher = teacherServiceIml.findTeacherWithId(key);
                    ArrayList<Teacher> teachers = new ArrayList<>();
                    teachers.add(teacher);
                    session.setAttribute("onePageTeachers", teachers);
                    session.setAttribute("sumIndex", 1);
                    response.sendRedirect("admin/teacher_management.jsp");
                } catch (Exception e) {
                    out.print(e);
                }
            } else {
                try {
                    ArrayList<Teacher> teachers = teacherServiceIml.findTeachersWithName(key);
                    if(teachers.size()==0){
                        teachers.add(null);
                    }
                    session.setAttribute("onePageTeachers", teachers);
                    session.setAttribute("sumIndex", 1);
                    response.sendRedirect("admin/teacher_management.jsp");
                } catch (Exception e) {
                    out.print(e);
                }
            }
        }
    }
}
