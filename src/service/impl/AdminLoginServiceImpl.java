package service.impl;

import dao.impl.AdminDImpl;
import dao.impl.TeacherDImpl;
import model.Admin;
import model.Student;
import model.Teacher;

public class AdminLoginServiceImpl{
    private final AdminDImpl AdminDao=new AdminDImpl();

    public boolean checkAdminLogin(String account, String password) throws Exception {
        Admin admin = AdminDao.checkAccount(account, password);
        if(admin != null) {
            return true;
        }
        return false;
    }
}
