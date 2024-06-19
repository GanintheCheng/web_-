package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.impl.TeacherServiceIml;
import util.myuntils;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add_teacher_admin")
public class AddTeacherAdminServlet extends HttpServlet {
    private final TeacherServiceIml teacherService = new TeacherServiceIml();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

//        String id = request.getParameter("teacherId");
        String id = null;
        try {
            id = myuntils.getMaxTeaId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String name = request.getParameter("teacherName");
        String password = request.getParameter("teacherPassword");
        String sex = request.getParameter("teacherSex");
        String email = request.getParameter("teacherEmail");

        try {
            teacherService.addTeacher(id, name, password, sex, email);
            out.println("<script>");
            out.println("alert('添加的教师账号为" + id + ",可用其登录');");
            out.println("window.location.href='one_page_teacher_admin';");
            out.println("</script>");
            out.flush();
        } catch (Exception e) {
            out.print(e);
        }
//        response.sendRedirect("one_page_teacher_admin");
    }
}
