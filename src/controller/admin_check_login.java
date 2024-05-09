package controller;

import service.impl.AdminLoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.impl.LoginServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin_check_login")
public class admin_check_login extends HttpServlet {
    private final AdminLoginServiceImpl adminLoginServiceImpl;

    public admin_check_login() {
        this.adminLoginServiceImpl = new AdminLoginServiceImpl();
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
            boolean isAdminValid = adminLoginServiceImpl.checkAdminLogin(user, password);
            if (isAdminValid) {
                session.setAttribute("admin", user);
                if (remember != null) {
                    Cookie adminCookie = new Cookie("admin", user);
                    adminCookie.setMaxAge(10);
                    response.addCookie(adminCookie);
                }
                response.sendRedirect("admin/main.jsp");
            } else {
                out.print("<script>alert(\"管理员用户名或密码错误！\")</script>");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
