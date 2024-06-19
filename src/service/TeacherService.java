package service;

import model.Teacher;

import java.util.ArrayList;

public interface TeacherService {
    /**
     * 分页获取教师信息
     * @param currentIndex 当前页码
     * @param size 每页大小
     * @return 教师对象列表
     * @throws Exception 异常信息
     */
    ArrayList<Teacher> getOnePageTeachers(int currentIndex, int size) throws Exception;

    /**
     * 获取教师总数
     * @return 教师总数
     * @throws Exception 异常信息
     */
    int getTeacherCount() throws Exception;

    /**
     * 判断字符串是否为数字
     * @param str 待判断的字符串
     * @return 如果是数字返回true，否则返回false
     */
    boolean isNumeric(String str);

    /**
     * 通过教师ID查找教师信息
     * @param id 教师ID
     * @return Teacher对象
     * @throws Exception 异常信息
     */
    Teacher findTeacherWithId(String id) throws Exception;

    /**
     * 通过教师姓名查找教师列表
     * @param name 教师姓名
     * @return 教师对象列表
     * @throws Exception 异常信息
     */
    ArrayList<Teacher> findTeachersWithName(String name) throws Exception;

    /**
     * 更新教师信息
     * @param id 教师ID
     * @param name 教师姓名
     * @param sex 教师性别
     * @param password 教师密码
     * @throws Exception 异常信息
     */
    void updateTeacherInfo(String id, String name, String sex, String password) throws Exception;

    /**
     * 删除教师信息
     * @param id 教师ID
     * @throws Exception 异常信息
     */
    void deleteTeacher(String id) throws Exception;

    /**
     * 添加教师信息
     * @param id 教师ID
     * @param name 教师姓名
     * @param password 教师密码
     * @param sex 教师性别
     * @param email 教师邮箱
     * @throws Exception 异常信息
     */
    void addTeacher(String id, String name, String password, String sex, String email) throws Exception;

    /**
     * 更新教师信息
     * @param uid 教师ID
     * @param name 教师姓名
     * @param sex 教师性别
     * @param email 教师邮箱
     * @param password 教师密码
     * @return 更新后的Teacher对象
     * @throws Exception 异常信息
     */
    Teacher updateTeacher(String uid, String name, String sex, String email, String password) throws Exception;

    /**
     * 更新教师密码
     * @param uid 教师ID
     * @param password 教师密码
     * @throws Exception 异常信息
     */
    void updateTeacherPassword(String uid, String password) throws Exception;

    /**
     * 更新教师头像
     * @param uid 教师ID
     * @param img 头像路径
     * @throws Exception 异常信息
     */
    void updateImg(String uid, String img) throws Exception;
}
