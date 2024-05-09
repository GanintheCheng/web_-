package dao.impl;

import dao.BaseDao;
import model.Admin;
import model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDImpl extends BaseDao {
    public Admin checkAccount(String user, String password) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from admin where id = '" + user + "' and password = '" + password + "'";
        ResultSet rs = stat.executeQuery(sql);
        Admin admin = getAdmin(rs);
        closeConnection();
        return admin;
    }

    public Admin getAdmin(ResultSet rs) throws SQLException {
        Admin admin = null;
        if (rs.next()) {
            admin = new Admin();
            admin.setId(rs.getString("id"));
            admin.setPassword(rs.getString("password"));
        }
        return admin;
    }

    public Admin findWithId(String id) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from admin where id = '" + id + "'";
        ResultSet rs = stat.executeQuery(sql);
        Admin admin = getAdmin(rs);
        closeConnection();
        return admin;
    }
}
