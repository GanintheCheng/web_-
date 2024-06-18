package service.impl;

import dao.impl.AdminDImpl;
import model.Admin;
import service.AdminLoginService;

public class AdminLoginServiceImpl implements AdminLoginService {
    private final AdminDImpl AdminDao = new AdminDImpl();

    @Override
    public boolean checkAdminLogin(String account, String password) throws Exception {
        Admin admin = AdminDao.checkAccount(account, password);
        if (admin != null) {
            return true;
        }
        return false;
    }
}
