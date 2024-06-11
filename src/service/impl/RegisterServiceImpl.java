package service.impl;

import dao.impl.TeacherDImpl;
import model.Teacher;
import service.RegisterService;
import util.factory;

public class RegisterServiceImpl implements RegisterService {
    private final TeacherDImpl teacherDao= factory.getTeacherDImpl();

    @Override
    public Teacher registerTeacher(String user, String password, String email) throws Exception {
        return teacherDao.insertTeacher(user, password, email);
    }
}
