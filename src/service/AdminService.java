package service;

import model.Admin;

public interface AdminService {
    /**
     * 更新管理员信息
     * @param uid 管理员ID
     * @param account 管理员账号
     * @param password 管理员密码
     * @param name 管理员姓名
     * @return 更新后的Admin对象
     * @throws Exception 异常信息
     */
    Admin updateAdmin(int uid, String account, String password, String name) throws Exception;
}
