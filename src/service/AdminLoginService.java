package service;

public interface AdminLoginService {
    /**
     * 检查管理员登录凭证，验证账号和密码
     * @param account 管理员账号
     * @param password 管理员密码
     * @return 登录成功返回true，失败返回false
     * @throws Exception 异常信息
     */
    boolean checkAdminLogin(String account, String password) throws Exception;
}