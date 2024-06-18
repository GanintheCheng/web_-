package service.impl;

import dao.impl.TeacherDImpl;
import model.Teacher;
import service.RegisterService;

public class RegisterServiceImpl implements RegisterService {
    private final TeacherDImpl teacherDao= new TeacherDImpl();

    @Override
    public Teacher registerTeacher(String user, String password, String email) throws Exception {
        return teacherDao.insertTeacher(user, password, email);
    }
}
