package controller;

import dao.impl.AdminDImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Admin;
import service.impl.AdminServiceImpl;
import util.factory;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update_admin")
public class update_admin extends HttpServlet {
    private final AdminServiceImpl adminService = new AdminServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        int uid = Integer.parseInt(request.getParameter("uid"));
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        try {
            Admin admin = adminService.updateAdmin(uid, account, password, name);
            session.setAttribute("admin", admin);
            out.print("<script>alert(\"保存成功！\");location.href = \"admin/main.jsp\";</script>");
        } catch (Exception e) {
            out.print(e);
        }
    }
}
