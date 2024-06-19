package service;

import model.Class;

import java.util.List;

public interface ClassService {
    /**
     * 通过班级ID获取班级信息
     * @param id 班级ID
     * @return Class对象
     * @throws Exception 异常信息
     */
    public Class getClassById(int id) throws Exception;

    /**
     * 通过班级ID列表获取对应班级信息列表
     * @param ids 班级ID列表
     * @return 班级对象列表
     * @throws Exception 异常信息
     */
    public List<Class> getListClassesWithIds(List<Integer> ids) throws Exception;

    /**
     * 获取所有班级信息
     * @return 班级对象列表
     * @throws Exception 异常信息
     */
    public List<Class> getAllClasses() throws Exception;
}
