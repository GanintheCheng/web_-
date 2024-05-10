package dao.impl;

import dao.BaseDao;
import dao.TeacherD;
import model.Student;
import model.Teacher;

import java.sql.*;
import java.util.ArrayList;


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

    public Teacher insertTeacher(String id, String name, String password, String sex, String email) throws Exception {
        initConnection();
        String sql = "insert into teacher(id, name, password, sex, email) values(?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, name);
        ps.setString(3, password);
        ps.setString(4, sex);
        ps.setString(5, email);
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

    public ArrayList<Teacher> getOnePage(int page, int size) throws Exception {
        ArrayList<Teacher> al = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM teacher limit ?, ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, (page - 1) * size);
        ps.setInt(2, size);
        ResultSet rs = ps.executeQuery();
        getMoreTeacher(al, rs);
        closeConnection();
        return al;
    }

    public void getMoreTeacher(ArrayList<Teacher> al, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Teacher tea = new Teacher();
            tea.setId(rs.getString("id"));
            tea.setPassword(rs.getString("password"));
            tea.setName(rs.getString("name"));
            tea.setSex(rs.getString("sex"));
            tea.setImg(rs.getString("img"));
            tea.setEmail(rs.getString("email"));
            al.add(tea);
        }
    }

    public int getTeacherCount() throws Exception {
        initConnection();
        String sql = "select count(*) from teacher";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        rs.next();
        int count = rs.getInt(1);
        closeConnection();
        return count;
    }

    public ArrayList<Teacher> findWithName(String name) throws Exception {
        ArrayList<Teacher> al = new ArrayList<>();
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from teacher where name = '" + name + "'";
        ResultSet rs = stat.executeQuery(sql);
        getMoreTeacher(al, rs);
        closeConnection();
        return al;
    }

    public void updateTeacherInfo(String id, String name, String sex, String password) throws Exception {
        initConnection();
        String sql = "update teacher set name=?, sex=?, password=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, sex);
        ps.setString(3, password);
        ps.setString(4, id);
        ps.executeUpdate();
        closeConnection();
    }

    public boolean deleteTeacher(String id) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "delete from teacher where id='" + id + "'";
        int i = stat.executeUpdate(sql);
        closeConnection();
        return i == 1;
    }
}
