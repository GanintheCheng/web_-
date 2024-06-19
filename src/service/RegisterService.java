package service;

import model.Teacher;

public interface RegisterService {
    /**
     * 注册教师信息
     * @param user 教师账号
     * @param password 教师密码
     * @param email 教师邮箱
     * @return 注册后的Teacher对象
     * @throws Exception 异常信息
     */
    Teacher registerTeacher(String user, String password, String email) throws Exception;
}
