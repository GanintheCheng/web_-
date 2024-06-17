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
import model.Teacher;
import service.impl.TeacherServiceIml;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/export_excel")
public class export_score extends HttpServlet {
    TeacherServiceIml teacherService = new TeacherServiceIml();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=excel.xls");
        String teacherId = request.getParameter("teacherId");
        String studentId = request.getParameter("studentId");
        Teacher teacher=new Teacher();
        if(teacherId != null) {
            try {
                teacher=teacherService.findTeacherWithId(teacherId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        try (PrintWriter out = response.getWriter()) {
            ScoreDImpl scoreD = new ScoreDImpl();
            StudentDImpl studentD = new StudentDImpl();
            ArrayList<Score> scores = scoreD.getOnePage(1, 10000);

            out.println("<table align='center' border='1'>");
            out.println("<tr>");
            out.println("<th height='35'>学号</th>");
            out.println("<th>姓名</th>");
            out.println("<th>专业</th>");
            out.println("<th>班级</th>");
            out.println("<th>数据库</th>");
            out.println("<th>Android</th>");
            out.println("<th>JavaWeb</th>");
            out.println("</tr>");

            for (Score score : scores) {
                Student student = studentD.findWithId(score.getId());
                if (studentId != null) {
                    if(!student.getId().equals(studentId)){
                        continue;
                    }
                }
                if (teacherId != null) {
                    if(!teacher.getClassList().contains(student.getStudentClass())){
                        continue;
                    }
                }
                if (student != null) {
                    String className = student.getStudentClass() != null ? student.getStudentClass().getName() : "未分配";
                    out.println("<tr>");
                    out.println("<td align='center'>" + score.getId() + "</td>");
                    out.println("<td align='center'>" + student.getName() + "</td>");
                    out.println("<td align='center'>" + student.getMajor() + "</td>");
                    out.println("<td align='center'>" + className + "</td>");
                    out.println("<td align='center'>" + score.getDatabase() + "</td>");
                    out.println("<td align='center'>" + score.getAndroid() + "</td>");
                    out.println("<td align='center'>" + score.getJsp() + "</td>");
                    out.println("</tr>");
                }
            }

            out.println("</table>");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
