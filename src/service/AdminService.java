package service;

import model.Admin;

public interface AdminService {
    Admin updateAdmin(int uid, String account, String password, String name) throws Exception;
}
