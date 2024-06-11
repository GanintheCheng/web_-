package service.impl;

import dao.impl.AdminDImpl;
import model.Admin;
import service.AdminService;
import util.factory;

public class AdminServiceImpl implements AdminService {
    private final AdminDImpl adminD = factory.getAdminDImpl();

    @Override
    public Admin updateAdmin(int uid, String account, String password, String name) throws Exception {
        return adminD.updateAdmin(uid, account, password, name);
    }
}
