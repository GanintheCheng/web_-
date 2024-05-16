package service;

public interface AdminLoginService {
    boolean checkAdminLogin(String account, String password) throws Exception;
}
