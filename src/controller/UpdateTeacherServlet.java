package controller;

import model.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.impl.TeacherServiceIml;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update_teacher")
public class UpdateTeacherServlet extends HttpServlet {
    public final TeacherServiceIml teacherService = new TeacherServiceIml();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String uid = request.getParameter("uid");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            Teacher teacher = teacherService.updateTeacher(uid, name, sex, email, password);
            session.setAttribute("info", teacher);
            out.print("<script>alert(\"保存成功！\");location.href = \"teacher/personal.jsp\";</script>");
        } catch (Exception e) {
            out.print(e);
        }
    }
}
