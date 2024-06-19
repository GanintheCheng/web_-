package controller;

import dao.impl.TeacherDImpl;
import dao.impl.StudentDImpl;
import model.Student;
import model.Teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@WebServlet("/send_code")
public class SendCodeServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("user");
        TeacherDImpl teacherDImpl = new TeacherDImpl();
        StudentDImpl studentDImpl = new StudentDImpl();
        Teacher teacher = null;
        Student student = null;

        try {
            teacher = teacherDImpl.findWithId(id);
            student = studentDImpl.findWithId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        if (teacher != null) {
            if (teacher.getEmail() == null) {
                request.setAttribute("message", "该教师未设置安全邮箱!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                sendVerificationCode(teacher.getEmail(), session);
                request.setAttribute("user", teacher);
                request.setAttribute("userType", "teacher");
                request.getRequestDispatcher("sendCode.jsp").forward(request, response);
            }
        } else if (student != null) {
            if (student.getEmail() == null) {
                request.setAttribute("message", "该学生未设置安全邮箱!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                sendVerificationCode(student.getEmail(), session);
                request.setAttribute("user", student);
                request.setAttribute("userType", "student");
                request.getRequestDispatcher("sendCode.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "该用户不存在!");
            request.getRequestDispatcher("forget.jsp").forward(request, response);
        }
    }

    private void sendVerificationCode(String toMail, HttpSession session) {
        int x = (int) (1000 + Math.random() * (9999 - 1000 + 1));
        String title = "验证码";
        String content = Integer.toString(x);

        try {
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", true);
            Session s = Session.getInstance(prop);

            MimeMessage message = new MimeMessage(s);
            message.setFrom(new InternetAddress("1837761918@qq.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
            message.setSubject(title);
            message.setText(content, "utf-8");
            message.setSentDate(new Date());
            message.saveChanges();

            Transport transport = s.getTransport("smtp");
            transport.connect("smtp.qq.com", "1837761918@qq.com", "jsianyzwfwajdabe");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            session.setAttribute("reset", content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
