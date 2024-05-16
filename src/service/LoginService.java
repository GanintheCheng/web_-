package service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {
    Object checkLogin(String user, String password) throws Exception;

    void exitLogin(HttpServletRequest request, HttpServletResponse response);
}
