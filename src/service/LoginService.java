package service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {
    /**
     * 检查用户登录凭证，验证用户名和密码
     * @param user 用户名
     * @param password 用户密码
     * @return 登录成功返回用户对象，否则返回null
     * @throws Exception 异常信息
     */
    Object checkLogin(String user, String password) throws Exception;

    /**
     * 退出登录，清除会话信息
     * @param request HttpServletRequest对象
     * @param response HttpServletResponse对象
     */
    void exitLogin(HttpServletRequest request, HttpServletResponse response);
}
