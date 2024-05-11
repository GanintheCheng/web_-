package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.impl.LoginServiceImpl;

import java.io.IOException;

@WebServlet("/exit")
public class exit extends HttpServlet {
    public LoginServiceImpl loginService = new LoginServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //清除cookie, 跳到起始页
        loginService.exitLogin(request, response);
        response.sendRedirect("login.jsp");
    }
}