package service;

import model.Student;

import java.util.ArrayList;

public interface StudentService {
    /**
     * 通过部分ID查找学生 模糊查询 已弃用
     * @param partialId 部分ID
     * @return 学生对象列表
     * @throws Exception 异常信息
     */
    ArrayList<Student> findWithPartialId(String partialId) throws Exception;

    /**
     * 添加学生信息
     * @param id 学生ID
     * @param name 学生姓名
     * @param sex 学生性别
     * @param major 学生专业
     * @param school_date 入学日期
     * @throws Exception 异常信息
     */
    void addStudent(String id, String name, String sex, String major, String school_date) throws Exception;

    /**
     * 删除学生信息
     * @param id 学生ID
     * @throws Exception 异常信息
     */
    void deleteStudent(String id) throws Exception;

    /**
     * 分页获取学生信息
     * @param currentIndex 当前页码
     * @param size 每页大小
     * @return 学生对象列表
     * @throws Exception 异常信息
     */
    ArrayList<Student> getOnePageStudents(int currentIndex, int size) throws Exception;

    /**
     * 通过学生ID查找学生信息
     * @param id 学生ID
     * @return Student对象
     * @throws Exception 异常信息
     */
    Student findStudentWithId(String id) throws Exception;

    /**
     * 通过学生姓名查找学生列表
     * @param name 学生姓名
     * @return 学生对象列表
     * @throws Exception 异常信息
     */
    ArrayList<Student> findStudentsWithName(String name) throws Exception;

    /**
     * 获取学生总数
     * @return 学生总数
     * @throws Exception 异常信息
     */
    int getStudentCount() throws Exception;

    /**
     * 判断字符串是否为数字
     * @param str 待判断的字符串
     * @return 如果是数字返回true，否则返回false
     */
    boolean isNumeric(String str);

    /**
     * 更新学生信息
     * @param stuno 学生ID
     * @param stuname 学生姓名
     * @param stusex 学生性别
     * @throws Exception 异常信息
     */
    void updateStudentInfo(String stuno, String stuname, String stusex) throws Exception;

    /**
     * 更新学生信息
     * @param stuno 学生ID
     * @param stuname 学生姓名
     * @param stusex 学生性别
     * @param stumajor 学生专业
     * @param password 学生密码
     * @param classId 班级ID
     * @throws Exception 异常信息
     */
    void updateStudentInfo(String stuno, String stuname, String stusex, String stumajor, String password, int classId) throws Exception;

    /**
     * 更新学生安全信息（邮箱和密码）
     * @param stuno 学生ID
     * @param email 学生邮箱
     * @param password 学生密码
     * @throws Exception 异常信息
     */
    void updateStudentSecurity(String stuno, String email, String password) throws Exception;

    /**
     * 更新学生头像
     * @param stuno 学生ID
     * @param img 头像路径
     * @throws Exception 异常信息
     */
    void updateImg(String stuno, String img) throws Exception;

    /**
     * 添加学生信息
     * @param id 学生ID
     * @param name 学生姓名
     * @param sex 学生性别
     * @param major 学生专业
     * @param school_date 入学日期
     * @param stuClass 班级ID
     * @throws Exception 异常信息
     */

 public void addStudent(String id, String name, String sex, String major, String school_date, int stuClass) throws Exception;

    /**
     * 分页获取特定教师ID的学生信息
     * @param currentIndex 当前页码
     * @param size 每页大小
     * @param teacherId 教师ID
     * @return 学生对象列表
     * @throws Exception 异常信息
     */
    public ArrayList<Student> getOnePageStudents(int currentIndex, int size, String teacherId) throws Exception;

    /**
     * 根据教师ID查找学生信息
     * @param teacherId 教师ID
     * @return 学生对象列表
     * @throws Exception 异常信息
     */
    public ArrayList<Student> findStudentsWithTeacherId(String teacherId) throws Exception;

    /**
     * 获取特定教师ID的学生总数
     * @param teacherId 教师ID
     * @return 学生总数
     * @throws Exception 异常信息
     */
    public int getStudentCount(String teacherId) throws Exception;
}