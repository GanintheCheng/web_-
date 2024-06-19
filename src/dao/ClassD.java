package dao;

import model.Class;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ClassD {
    /**
     * @author GanintheCheng
     * 通过ID获取班级信息
     * @param id 班级ID
     * @return Class对象
     * @throws Exception 异常信息
     */
    public Class getClassById(int id) throws Exception;

    /**
     * @author GanintheCheng
     * 通过一组班级ID获取对应的班级信息列表
     * @param ids 班级ID列表
     * @return Class对象列表
     * @throws Exception 异常信息
     */
    public List<Class> getListClassesWithIds(List<Integer> ids) throws Exception;

    /**
     * @author GanintheCheng
     * 通过ResultSet获取Class对象
     * @param rs 结果集
     * @return Class对象
     * @throws SQLException SQL异常
     */
    public Class getClass(ResultSet rs) throws SQLException;

    /**
     * @author GanintheCheng
     * 获取所有班级信息
     * @return Class对象列表
     * @throws Exception 异常信息
     */
    public List<Class> getAllClasses() throws Exception;

    /**
     * @author GanintheCheng
     * 更新班级的教师信息
     * @param classStrings 班级字符串数组
     * @param teacherId 教师ID
     * @throws Exception 异常信息
     */
    public void changeClassWithTeacherId(String[] classStrings, String teacherId) throws Exception;

    /**
     * @author GanintheCheng
     * 删除教师的班级信息
     * @param teacherId 教师ID
     * @throws Exception 异常信息
     */
    public void deleteTeacherClass(String teacherId) throws Exception;
}
