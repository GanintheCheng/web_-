package controller;

import dao.impl.AdminDImpl;
import model.Admin;
import service.impl.AdminLoginServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin_check_login")
public class AdminCheckLoginServlet extends HttpServlet {
    private final AdminLoginServiceImpl adminLoginServiceImpl = new AdminLoginServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String account = request.getParameter("account");
        String password = request.getParameter("password");

        try {
            boolean isAdminValid = adminLoginServiceImpl.checkAdminLogin(account, password);
            if (isAdminValid) {
                Admin admin = new AdminDImpl().findWithAccount(account);
                session.setAttribute("admin", admin); // 存储完整的Admin对象到session
                request.setAttribute("admin", admin); // 将Admin对象放入request
                response.sendRedirect(request.getContextPath() + "/admin/main.jsp");
            } else {
                out.print("<script>alert(\"管理员用户名或密码错误！\")</script>");
                response.sendRedirect("login.jsp"); // 重定向回登录页面
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("<script>alert(\"系统错误，请稍后再试！\")</script>");
            response.sendRedirect("login.jsp");
        }
    }
}
