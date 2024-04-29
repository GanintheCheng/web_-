package servlet;

import dao.ScoreD;
import dao.StudentD;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update_student")
public class update_student extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        StudentD studentD = new StudentD();

        String stuno = request.getParameter("stuno");
        String stuname = request.getParameter("stuname");
        String stusex = request.getParameter("stusex");
        String stumajor = request.getParameter("stumajor");

        try {
            studentD.updateStudentInfo(stuno, stuname, stusex, stumajor);
        }
        catch (Exception e){
            out.print(e);
        }
        response.sendRedirect("one_page_student");
    }
}
