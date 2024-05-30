package dao.impl;

import dao.BaseDao;
import model.Admin;
import model.Student;
import model.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDImpl extends BaseDao {
    public Admin checkAccount(String account, String password) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from admin where account = '" + account + "' and password = '" + password + "'";
        ResultSet rs = stat.executeQuery(sql);
        Admin admin = getAdmin(rs);
        closeConnection();
        return admin;
    }

    public Admin getAdmin(ResultSet rs) throws Exception {
        initConnection();
        Admin admin = null;
        if (rs.next()) {
            admin = new Admin();
            admin.setId(Integer.parseInt(rs.getString("id")));
            admin.setAccount(rs.getString("account"));
            admin.setName(rs.getString("name"));
            admin.setPassword(rs.getString("password"));
        }
        return admin;
    }

    public Admin findWithId(int id) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from admin where id = '" + id + "'";
        ResultSet rs = stat.executeQuery(sql);
        Admin admin = getAdmin(rs);
        closeConnection();
        return admin;
    }

    public Admin findWithAccount(String account) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from admin where account = '" + account + "'";
        ResultSet rs = stat.executeQuery(sql);
        Admin admin = getAdmin(rs);
        closeConnection();
        return admin;
    }

    public Admin updateAdmin(int id, String account, String password, String name) throws Exception {

        initConnection();
        String sql = "update admin set account=?, password=?, name=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, account);
        ps.setString(2, password);
        ps.setString(3, name);
        ps.setString(4, String.valueOf(id));
        ps.executeUpdate();
        Admin admin = findWithId(id);
        closeConnection();
        return admin;
    }
}
