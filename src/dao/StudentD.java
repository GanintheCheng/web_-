package dao;

import model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentD {
    /**
     * @author GanintheCheng
     * 检查学生账号密码，用于学生登录
     * @param user 学生账号
     * @param password 学生密码
     * @return Student对象
     * @throws Exception 异常信息
     */
    Student checkAccount(String user, String password) throws Exception;

    /**
     * @author GanintheCheng
     * 通过学生ID查找学生
     * @param id 学生ID
     * @return Student对象
     * @throws Exception 异常信息
     */
    Student findWithId(String id) throws Exception;

    /**
     * @author GanintheCheng
     * 通过学生姓名查找学生列表 因为name可能不唯一 所以是list集合
     * @param name 学生姓名
     * @return 学生对象列表
     * @throws Exception 异常信息
     */
    ArrayList<Student> findWithName(String name) throws Exception;

    /**
     * @author GanintheCheng
     * 插入学生信息
     * @param id 学生ID
     * @param name 学生姓名
     * @param sex 学生性别
     * @param school_date 入学日期
     * @param major 学生专业
     * @throws Exception 异常信息
     */
    void insertStudent(String id, String name, String sex, String school_date, String major) throws Exception;

    /**
     * @author GanintheCheng
     * 根据id删除学生信息
     * @param id 学生ID
     * @throws Exception 异常信息
     */
    void deleteStudent(String id) throws Exception;

    /**
     * @author GanintheCheng
     * 分页获取学生信息
     * @param page 页码
     * @param size 每页大小
     * @return 学生对象列表
     * @throws Exception 异常信息
     */
    ArrayList<Student> getOnePage(int page, int size) throws Exception;

    /**
     * @author GanintheCheng
     * 获取学生总数
     * @return 学生总数
     * @throws Exception 异常信息
     */
    int getStudentCount() throws Exception;

    /**
     * @author GanintheCheng
     * 用rs封装学生信息 用于操作多个学生的封装
     * @param al 学生对象列表
     * @param rs 结果集
     * @throws Exception 异常信息
     */
    void getMoreStudent(ArrayList<Student> al, ResultSet rs) throws Exception;

    /**
     * @author GanintheCheng
     * 通过ResultSet获取Student对象 操作单个学生的封装
     * @param rs 结果集
     * @return Student对象
     * @throws Exception 异常信息
     */
    Student getStudent(ResultSet rs) throws Exception;

    /**
     * @author GanintheCheng
     * 更新学生信息
     * @param id 学生ID
     * @param name 学生姓名
     * @param sex 学生性别
     * @throws Exception 异常信息
     */
    void updateStudentInfo(String id, String name, String sex) throws Exception;

    /**
     * @author GanintheCheng
     * 更新学生安全信息（邮箱和密码）
     * @param id 学生ID
     * @param email 学生邮箱
     * @param password 学生密码
     * @throws Exception 异常信息
     */
    void updateStudentSecurity(String id, String email, String password) throws Exception;

    /**
     * @author GanintheCheng
     * 更新学生头像
     * @param id 学生ID
     * @param img 头像路径
     * @throws Exception 异常信息
     */
    void updateImg(String id, String img) throws Exception;

    /**
     * @author GanintheCheng
     * 获取特定教师ID的学生列表
     * @param teacherId 教师ID
     * @return 学生对象列表
     * @throws Exception 异常信息
     */
    ArrayList<Student> getStudentListWithTeacherId(String teacherId) throws Exception;

    /**
     * @author GanintheCheng
     * 获取学生ID的最大值
     * @return 最大学生ID
     * @throws Exception 异常信息
     */
    String getMaxStudentId() throws Exception;

    /**
     * @author GanintheCheng
     * 更新学生信息
     * @param id 学生ID
     * @param name 学生姓名
     * @param sex 学生性别
     * @param major 学生专业
     * @param password 学生密码
     * @param classId 班级ID
     * @throws Exception 异常信息
     */
    void updateStudentInfo(String id, String name, String sex, String major, String password, int classId) throws Exception;

    /**
     * @author GanintheCheng
     * 获取特定教师ID的学生总数
     * @param teacherId 教师ID
     * @return 学生总数
     * @throws Exception 异常信息
     */
    int getStudentCount(String teacherId) throws Exception;

    /**
     * @author GanintheCheng
     * 分页获取特定教师ID的学生信息 用于教师的学生展示
     * @param page 页码
     * @param size 每页大小
     * @param teacherId 教师ID
     * @return 学生对象列表
     * @throws Exception 异常信息
     */
    ArrayList<Student> getOnePage(int page, int size, String teacherId) throws Exception;

    /**
     * @author GanintheCheng
     * 插入学生信息
     * @param id 学生ID
     * @param name 学生姓名
     * @param sex 学生性别
     * @param school_date 入学日期
     * @param major 学生专业
     * @param stuClass 学生班级
     * @throws Exception 异常信息
     */
    void insertStudent(String id, String name, String sex, String school_date, String major, int stuClass) throws Exception;

    /**
     * @author GanintheCheng
     * 通过部分ID查找学生列表 模糊查询 未使用 弃用
     * @param partialId 部分ID
     * @return 学生对象列表
     * @throws Exception 异常信息
     */
    ArrayList<Student> findWithPartialId(String partialId) throws Exception;
}
