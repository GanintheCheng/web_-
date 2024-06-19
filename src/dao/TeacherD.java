package dao;

import model.Class;
import model.Teacher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface TeacherD {
    /**
     * @author GanintheCheng
     * 检查教师账号密码，用于教师登录
     * @param id 教师账号
     * @param password 教师密码
     * @return Teacher对象
     * @throws Exception 异常信息
     */
    public Teacher checkAccount(String id, String password) throws Exception;

    /**
     * @author GanintheCheng
     * 通过教师ID查找教师
     * @param id 教师ID
     * @return Teacher对象
     * @throws Exception 异常信息
     */
    public Teacher findWithId(String id) throws Exception;

    /**
     * @author GanintheCheng
     * 插入教师信息
     * @param id 教师ID
     * @param password 教师密码
     * @param email 教师邮箱
     * @return 插入的Teacher对象
     * @throws Exception 异常信息
     */
    public Teacher insertTeacher(String id, String password, String email) throws Exception;

    /**
     * @author GanintheCheng
     * 更新教师信息
     * @param id 教师ID
     * @param name 教师姓名
     * @param sex 教师性别
     * @param email 教师邮箱
     * @param password 教师密码
     * @return 更新后的Teacher对象
     * @throws Exception 异常信息
     */
    public Teacher updateTeacher(String id, String name, String sex, String email, String password) throws Exception;

    /**
     * @author GanintheCheng
     * 更新教师密码
     * @param id 教师ID
     * @param password 新密码
     * @throws Exception 异常信息
     */
    public void updateTeacherPassword(String id, String password) throws Exception;

    /**
     * @author GanintheCheng
     * 通过ResultSet获取Teacher对象
     * @param rs 结果集
     * @return Teacher对象
     * @throws Exception 异常信息
     */
    public Teacher getTeacher(ResultSet rs) throws Exception;

    /**
     * @author GanintheCheng
     * 更新教师头像
     * @param id 教师ID
     * @param img 头像路径
     * @throws Exception 异常信息
     */
    public void updateImg(String id, String img) throws Exception;

    /**
     * @author GanintheCheng
     * 插入教师信息
     * @param id 教师ID
     * @param name 教师姓名
     * @param password 教师密码
     * @param sex 教师性别
     * @param email 教师邮箱
     * @return 插入的Teacher对象
     * @throws Exception 异常信息
     */
    public Teacher insertTeacher(String id, String name, String password, String sex, String email) throws Exception;

    /**
     * @author GanintheCheng
     * 分页获取教师信息
     * @param page 页码
     * @param size 每页大小
     * @return 教师对象列表
     * @throws Exception 异常信息
     */
    public ArrayList<Teacher> getOnePage(int page, int size) throws Exception;

    /**
     * @author GanintheCheng
     * 获取更多教师信息 用rs进行封装 操作多个老师
     * @param al 教师对象列表
     * @param rs 结果集
     * @throws Exception 异常信息
     */
    public void getMoreTeacher(ArrayList<Teacher> al, ResultSet rs) throws Exception;

    /**
     * @author GanintheCheng
     * 获取教师总数
     * @return 教师总数
     * @throws Exception 异常信息
     */
    public int getTeacherCount() throws Exception;

    /**
     * @author GanintheCheng
     * 通过教师姓名查找教师列表
     * @param name 教师姓名
     * @return 教师对象列表
     * @throws Exception 异常信息
     */
    public ArrayList<Teacher> findWithName(String name) throws Exception;

    /**
     * @author GanintheCheng
     * 更新教师信息
     * @param id 教师ID
     * @param name 教师姓名
     * @param sex 教师性别
     * @param password 教师密码
     * @throws Exception 异常信息
     */
    public void updateTeacherInfo(String id, String name, String sex, String password) throws Exception;

    /**
     * @author GanintheCheng
     * 删除教师信息
     * @param id 教师ID
     * @return 是否删除成功
     * @throws Exception 异常信息
     */
    public boolean deleteTeacher(String id) throws Exception;

    /**
     * @author GanintheCheng
     * 获取教师ID的最大值
     * @return 最大教师ID
     * @throws Exception 异常信息
     */
    public String getMaxTeacherId() throws Exception;

    /**
     * @author GanintheCheng
     * 获取教师负责的班级列表
     * @param id 教师ID
     * @return 班级对象列表
     * @throws Exception 异常信息
     */
    public List<Class> getClassesWithId(String id) throws Exception;
}
