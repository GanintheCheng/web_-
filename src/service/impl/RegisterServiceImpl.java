package service.impl;

import dao.impl.TeacherDImpl;
import model.Teacher;

public class RegisterServiceImpl {
    private final TeacherDImpl teacherDao;

    public RegisterServiceImpl() {
        this.teacherDao = new TeacherDImpl();
    }

    public Teacher registerTeacher(String user, String password, String email) throws Exception {
        return teacherDao.insertTeacher(user, password, email);
    }
}
