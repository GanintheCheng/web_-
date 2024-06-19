package controller;

import dao.impl.StudentDImpl;
import dao.impl.TeacherDImpl;
import model.Student;
import model.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/check_cookie")
public class CheckCookieServlet extends HttpServlet {

    private final TeacherDImpl teacherDImpl = new TeacherDImpl();
    private final StudentDImpl studentDImpl = new StudentDImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Teacher teacher = null;
        Student student = null;

        if (cookies != null) {
            for (Cookie c : cookies) {
                String cookieName = c.getName();
                if ("name".equals(cookieName)) {
                    String user = c.getValue();
                    try {
                        teacher = teacherDImpl.findWithId(user);
                        student = studentDImpl.findWithId(user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        if (teacher != null) {
            HttpSession session = request.getSession();
            session.setAttribute("info", teacher);
            response.sendRedirect("one_page_student");
        } else if (student != null) {
            HttpSession session = request.getSession();
            session.setAttribute("info", student);
            response.sendRedirect("student/main.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
