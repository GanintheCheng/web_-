package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.impl.StudentServiceIml;
import util.myuntils;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add_student_admin")
public class add_student_admin extends HttpServlet {
    private final StudentServiceIml studentService = new StudentServiceIml();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

//        String id = request.getParameter("id");
        String id = null;
        try {
            id = myuntils.getMaxStuId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String major = request.getParameter("major");
        String school_date = request.getParameter("school_date");
        int classId = Integer.parseInt(request.getParameter("stuClass"));

        try {
            studentService.addStudent(id, name, sex, major, school_date,classId);
        } catch (Exception e) {
            out.print(e);
        }
        out.println("<script>");
        out.println("alert('添加的学生账号为" + id + ",可用其登录');");
        out.println("window.location.href='one_page_student_admin';");
        out.println("</script>");
        out.flush();
//        response.sendRedirect("one_page_student_admin");
    }
}
