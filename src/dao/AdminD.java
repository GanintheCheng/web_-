package dao;

import model.Admin;
import java.sql.ResultSet;

public interface AdminD {
    /**
     * 检查管理员账号密码，用于管理员登录
     * @param account 管理员账号
     * @param password 管理员密码
     * @return Admin对象
     * @throws Exception 异常信息
     */
    public Admin checkAccount(String account, String password) throws Exception;

    /**
     * 通过ResultSet获取Admin对象
     * @param rs 结果集
     * @return Admin对象
     * @throws Exception 异常信息
     */
    public Admin getAdmin(ResultSet rs) throws Exception;

    /**
     * 通过管理员ID查找管理员
     * @param id 管理员ID
     * @return Admin对象
     * @throws Exception 异常信息
     */
    public Admin findWithId(int id) throws Exception;

    /**
     * 通过管理员账号查找管理员
     * @param account 管理员账号
     * @return Admin对象
     * @throws Exception 异常信息
     */
    public Admin findWithAccount(String account) throws Exception;

    /**
     * 更新管理员信息
     * @param id 管理员ID
     * @param account 管理员账号
     * @param password 管理员密码
     * @param name 管理员姓名
     * @return 更新后的Admin对象
     * @throws Exception 异常信息
     */
    public Admin updateAdmin(int id, String account, String password, String name) throws Exception;
}
