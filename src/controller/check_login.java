package controller;

import dao.impl.StudentDImpl;
import dao.impl.TeacherDImpl;
import model.Student;
import model.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.impl.LoginServiceImpl;
import service.impl.ScoreServiceImpl;

import java.io.IOException;

@WebServlet("/check_login")
public class check_login extends HttpServlet {
    private final LoginServiceImpl loginService = new LoginServiceImpl();
    private final ScoreServiceImpl scoreService = new ScoreServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();

        String user = request.getParameter("user");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        try {
            Object userInfo = loginService.checkLogin(user, password);
            if (userInfo instanceof Teacher) {
                if (remember != null) {
                    Cookie userCookie = new Cookie("name", ((Teacher) userInfo).getId());
                    userCookie.setMaxAge(999999999);
                    response.addCookie(userCookie);
                }
                handleSuccessfulLogin(request, response, session, userInfo, remember, "one_page_student?index=1&teacherId=" + ((Teacher) userInfo).getId());
            } else if (userInfo instanceof Student) {
                session.setAttribute("score", scoreService.findScoreWithId(((Student) userInfo).getId()));
                if (remember != null) {
                    Cookie userCookie = new Cookie("name", ((Student) userInfo).getId());
                    userCookie.setMaxAge(999999999);
                    response.addCookie(userCookie);
                }
                handleSuccessfulLogin(request, response, session, userInfo, remember, "student/main.jsp");
            } else {
                handleFailedLogin(request, response, "用户名或密码错误！");
            }
        } catch (Exception e) {
            handleFailedLogin(request, response, "系统错误，请稍后再试。");
        }
    }

    private void handleSuccessfulLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, Object userInfo, String remember, String redirectUrl) throws IOException {
        session.setAttribute("info", userInfo);
        response.sendRedirect(redirectUrl);
    }

    private void handleFailedLogin(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
