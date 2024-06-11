package controller;

import dao.impl.ScoreDImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.impl.ScoreServiceImpl;
import util.factory;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update_score")
public class update_score extends HttpServlet {
    private final ScoreServiceImpl scoreUpdateService = new ScoreServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        String[] id = request.getParameterValues("id");
        String[] database = request.getParameterValues("database");
        String[] android = request.getParameterValues("android");
        String[] jsp = request.getParameterValues("jsp");
        String teacherId = request.getParameter("teacherId");

        try {
            scoreUpdateService.updateScores(id, database, android, jsp);
            response.sendRedirect("one_page_score?teacherId=" + teacherId);
        } catch (Exception e) {
            out.print(e);
        }
    }
}
