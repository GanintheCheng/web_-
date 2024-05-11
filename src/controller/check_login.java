package controller;

import dao.impl.StudentDImpl;
import dao.impl.TeacherDImpl;
import model.Student;
import model.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.impl.LoginServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/check_login")
public class check_login extends HttpServlet {
    private final LoginServiceImpl LoginServiceImpl;

    public check_login() {
        this.LoginServiceImpl = new LoginServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String user = request.getParameter("user");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        try {
            Object userInfo = LoginServiceImpl.checkLogin(user, password);
            if (userInfo instanceof Teacher) {
                session.setAttribute("info", userInfo);
                if (remember != null) {
                    Cookie userCookie = new Cookie("name", user);
                    userCookie.setMaxAge(999999999);
                    response.addCookie(userCookie);
                }
                response.sendRedirect("one_page_student");
            } else if (userInfo instanceof Student) {
                session.setAttribute("info", userInfo);
                if (remember != null) {
                    Cookie userCookie = new Cookie("name", user);
                    userCookie.setMaxAge(999999999);
                    response.addCookie(userCookie);
                }
                response.sendRedirect("student/main.jsp");
            } else {
                out.print("<script>alert(\"用户名或密码错误！\")</script>");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

