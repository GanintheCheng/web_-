package controller;

import dao.impl.StudentDImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.impl.StudentServiceIml;
import util.factory;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update_student")
public class update_student extends HttpServlet {
    public StudentServiceIml studentService = new StudentServiceIml();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        String stuno = request.getParameter("stuno");
        String stuname = request.getParameter("stuname");
        String stusex = request.getParameter("stusex");
        String teacherId = request.getParameter("teacherId");

        try {
            studentService.updateStudentInfo(stuno, stuname, stusex);
        } catch (Exception e) {
            out.print(e);
        }
        out.println("<script>alert(\"修改成功,请重新登录\");</script>");
        response.sendRedirect("one_page_student?teacherId="+teacherId);
    }
}
