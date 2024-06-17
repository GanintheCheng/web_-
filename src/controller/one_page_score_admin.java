package controller;

import dao.impl.ScoreDImpl;
import dao.impl.StudentDImpl;
import model.Score;
import model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ScoreService;
import service.impl.ScoreServiceImpl;
import service.StudentService;
import service.impl.StudentServiceIml;


import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/one_page_score_admin")
public class one_page_score_admin extends HttpServlet {
    private final ScoreServiceImpl scoreService = new ScoreServiceImpl();
    private final StudentServiceIml studentService = new StudentServiceIml();

     @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        String key = request.getParameter("id");

        if (key == null) {
            int currentIndex, count, size = 10;
            String index = request.getParameter("index");
            if (index == null)
                index = "1";
            currentIndex = Integer.parseInt(index);

            try {
                count = scoreService.getScoreCount();
                ArrayList<Score> scores = scoreService.getOnePageScores(currentIndex, size);
                ArrayList<Student> students = new ArrayList<>();
                for (Score score : scores) {
                    Student student = studentService.findStudentWithId(score.getId());
                    if (student != null) {
                        students.add(student);
                    }
                }
                int sumIndex = count % size == 0 ? count / size : count / size + 1;
                session.setAttribute("onePageScore", scores);
                session.setAttribute("onePageStudent", students);
                session.setAttribute("sumScoreIndex", sumIndex);
                response.sendRedirect("admin/score_management.jsp");
            } catch (Exception e) {
                e.printStackTrace(response.getWriter());
            }
        } else {
            try {
                Score score = scoreService.findScoreWithId(key);
                ArrayList<Score> scores = new ArrayList<>();
                scores.add(score);
                Student student = studentService.findStudentWithId(score.getId());
                ArrayList<Student> students = new ArrayList<>();
                students.add(student);
                session.setAttribute("onePageScore", scores);
                session.setAttribute("onePageStudent", students);
                session.setAttribute("sumScoreIndex", 1);
                response.sendRedirect("admin/score_management.jsp");
            } catch (Exception e) {
                e.printStackTrace(response.getWriter());
            }
        }
    }
}
