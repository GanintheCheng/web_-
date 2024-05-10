package controller;

import dao.impl.AdminDImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Admin;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update_admin")
public class update_admin extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        AdminDImpl adminDImpl = new AdminDImpl();

        int uid = Integer.parseInt(request.getParameter("uid"));
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        try {
            Admin admin = adminDImpl.updateAdmin(uid,account, password);
            session.setAttribute("admin", admin.getAccount());
            out.print("<script>alert(\"保存成功！\");location.href = \"admin/main.jsp\";</script>");
        } catch (Exception e) {
            out.print(e);
        }
    }
}
