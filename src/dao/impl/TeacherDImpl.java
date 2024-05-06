package dao.impl;

import dao.BaseDao;
import dao.TeacherD;
import model.Teacher;

import java.sql.*;


public class TeacherDImpl extends BaseDao implements TeacherD {
    @Override
    public Teacher checkAccount(String id, String password) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from teacher where id = '" + id + "' and password = '" + password + "'";
        ResultSet rs = stat.executeQuery(sql);
        Teacher tea = getTeacher(rs);
        closeConnection();
        return tea;
    }

    @Override
    public Teacher findWithId(String id) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from teacher where id = '" + id + "'";
        ResultSet rs = stat.executeQuery(sql);
        Teacher tea = getTeacher(rs);
        closeConnection();
        return tea;
    }

    @Override
    public Teacher insertTeacher(String id, String password, String email) throws Exception {
        initConnection();
        String sql = "insert into teacher(id, password, email) values(?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, password);
        ps.setString(3, email);
        ps.executeUpdate();
        Teacher teacher = findWithId(id);
        closeConnection();
        return teacher;
    }

    @Override
    public Teacher updateTeacher(String id, String name, String sex, String email, String password) throws Exception {

        initConnection();
        String sql = "update teacher set name=?, sex=?, email=?, password=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, sex);
        ps.setString(3, email);
        ps.setString(4, password);
        ps.setString(5, id);
        ps.executeUpdate();
        Teacher teacher = findWithId(id);
        closeConnection();
        return teacher;
    }

    @Override
    public void updateTeacherPassword(String id, String password) throws Exception {

        initConnection();
        String sql = "update teacher set password=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, password);
        ps.setString(2, id);
        ps.executeUpdate();
        closeConnection();
    }

    @Override
    public Teacher getTeacher(ResultSet rs) throws SQLException {
        Teacher tea = null;
        if (rs.next()) {
            tea = new Teacher();
            tea.setId(rs.getString("id"));
            tea.setPassword(rs.getString("password"));
            tea.setName(rs.getString("name"));
            tea.setEmail(rs.getString("email"));
            tea.setSex(rs.getString("sex"));
            tea.setImg(rs.getString("img"));
        }
        return tea;
    }

    @Override
    public void updateImg(String id, String img) throws Exception {
        initConnection();
        String sql = "update teacher set img=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, img);
        ps.setString(2, id);
        ps.executeUpdate();
        closeConnection();
    }
}
