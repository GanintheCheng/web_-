package controller;

import dao.impl.ScoreDImpl;
import model.Score;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ScoreService;
import service.impl.ScoreServiceImpl;
import service.impl.StudentServiceIml;
import util.factory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/one_page_score")
public class one_page_score extends HttpServlet {
    private final ScoreServiceImpl scoreService = new ScoreServiceImpl();
    private final StudentServiceIml studentService = new StudentServiceIml();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String key = request.getParameter("id");

        if (key == null) {

            int currentIndex, count, size = 10;
            String index = request.getParameter("index");
            String teacherId = request.getParameter("teacherId");
            if (index == null)
                index = "1";
            currentIndex = Integer.parseInt(index);

            try {
                count = studentService.getStudentCount(teacherId);
                ArrayList<Score> stus = scoreService.getOnePageScores(currentIndex, size,teacherId);
                int sumIndex = count % size == 0 ? count / size : count / size + 1;
                session.setAttribute("onePageScore", stus);
                session.setAttribute("sumScoreIndex", sumIndex);
                response.sendRedirect("teacher/score.jsp");
            } catch (Exception e) {
                out.print(e);
            }
        } else {
            try {
                Score score = scoreService.findScoreWithId(key);
                ArrayList<Score> scores = new ArrayList<>();
                scores.add(score);
                session.setAttribute("onePageScore", scores);
                session.setAttribute("sumScoreIndex", 1);
                response.sendRedirect("teacher/score.jsp");
            } catch (Exception e) {
                out.print(e);
            }
        }
    }
}
