package controller;

import dao.impl.TeacherDImpl;
import model.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.impl.RegisterServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/check_register")
public class check_register extends HttpServlet {
    private final RegisterServiceImpl registerService;

    public check_register() {
        this.registerService = new RegisterServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        String email = request.getParameter("email");
        String user = request.getParameter("user");
        String password = request.getParameter("password1");
        String code = request.getParameter("code");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String randStr = (String) session.getAttribute("randStr");

        if (!code.equals(randStr)) {
            out.print("<script>alert(\"验证码错误！\");location.href = \"register.jsp\";</script>");
        } else {
            try {
                Teacher teacher = registerService.registerTeacher(user, password, email);
                if (teacher != null) {
                    session.setAttribute("info", teacher);
                    response.sendRedirect("one_page_student");
                } else {
                    out.print("<script>alert(\"此用户已经注册！\");location.href = \"register.jsp\";</script>");
                }
            } catch (Exception e) {
                out.print("<script>alert(\"此用户已经注册！\");location.href = \"register.jsp\";</script>");
            }
        }
    }

}