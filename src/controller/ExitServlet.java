package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.impl.LoginServiceImpl;

import java.io.IOException;

@WebServlet("/exit")
public class ExitServlet extends HttpServlet {
    public LoginServiceImpl loginService = new LoginServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //清除cookie,session, 跳到起始页
        loginService.exitLogin(request, response);
        request.getSession().invalidate();
        response.sendRedirect("login.jsp");
    }
}