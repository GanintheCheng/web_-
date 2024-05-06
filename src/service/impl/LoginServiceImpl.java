package service.impl;


import dao.impl.StudentDImpl;
import dao.impl.TeacherDImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Teacher;
import model.Student;

public class LoginServiceImpl {
    private final TeacherDImpl teacherDao;
    private final StudentDImpl studentDao;

    public LoginServiceImpl() {
        this.teacherDao = new TeacherDImpl();
        this.studentDao = new StudentDImpl();
    }

    public Object checkLogin(String user, String password) throws Exception {
        Teacher teacher = teacherDao.checkAccount(user, password);
        if (teacher != null) {
            return teacher;
        }
        Student student = studentDao.checkAccount(user, password);
        return student;
    }

    public void exitLogin(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                String cookieName = c.getName();
                if ("name".equals(cookieName)) {
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }
    }
}
