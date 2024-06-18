package dao.impl;

import dao.BaseDao;
import dao.TeacherD;
import model.Class;
import model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TeacherDImpl extends BaseDao implements TeacherD {
    ClassDImpl classD = new ClassDImpl();

    @Override
    public Teacher checkAccount(String id, String password) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from teacher where account = '" + id + "' and password = '" + password + "'";
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
        String sql = "insert into teacher(id, account, password, email) values(?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, id);
        ps.setString(3, password);
        ps.setString(4, email);
        ps.executeUpdate();
        Teacher teacher = findWithId(id);
        closeConnection();
        String[] strings = new String[]{"0"};
        classD.changeClassWithTeacherId(strings,id);
        return teacher;
    }

    public Teacher insertTeacher(String id, String name, String password, String sex, String email) throws Exception {
        initConnection();
        String sql = "insert into teacher(id, account, name, password, sex, email) values(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, id);
        ps.setString(3, name);
        ps.setString(4, password);
        ps.setString(5, sex);
        ps.setString(6, email);
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
    public Teacher getTeacher(ResultSet rs) throws Exception {
        Teacher tea = null;
        if (rs.next()) {
            tea = new Teacher();
            tea.setId(rs.getString("id"));
            tea.setAccount(rs.getString("Account"));
            tea.setPassword(rs.getString("password"));
            tea.setName(rs.getString("name"));
            tea.setEmail(rs.getString("email"));
            tea.setSex(rs.getString("sex"));
            tea.setImg(rs.getString("img"));
            tea.setClassList(getClassesWithId(tea.getId()));
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

    public void getMoreTeacher(ArrayList<Teacher> al, ResultSet rs) throws Exception {
        while (rs.next()) {
            Teacher tea = new Teacher();
            tea.setId(rs.getString("id"));
            tea.setPassword(rs.getString("password"));
            tea.setName(rs.getString("name"));
            tea.setSex(rs.getString("sex"));
            tea.setImg(rs.getString("img"));
            tea.setEmail(rs.getString("email"));
            tea.setClassList(getClassesWithId(tea.getId()));
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
        classD.deleteTeacherClass(id);
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "delete from teacher where id='" + id + "'";
        int i = stat.executeUpdate(sql);
        closeConnection();
        return i == 1;
    }

    public String getMaxTeacherId() throws Exception {
        initConnection();
        String maxIdString = null;
        try {
            String sql = "SELECT MAX(CAST(id AS UNSIGNED)) AS max_id FROM teacher";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                long maxId = rs.getLong("max_id");
                // 将最大值加一并转换为字符串
                maxIdString = Long.toString(maxId + 1);
            }
        } finally {
            closeConnection();
        }
        return maxIdString;
    }

    public List<Class> getClassesWithId(String id) throws Exception {
        initConnection();
        List<Class> listClassesWithIds = new ArrayList<>();
        String query = "SELECT c.* FROM teacher_class tc " +
                "JOIN teacher t ON t.id = tc.teacher_id " +
                "JOIN class c ON c.id = tc.class_id " +
                "WHERE tc.teacher_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Class cls = new Class();
                    cls.setId(resultSet.getInt("id"));
                    cls.setName(resultSet.getString("name"));
                    listClassesWithIds.add(cls);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error while fetching classes with teacher ID: " + id, e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return listClassesWithIds;
    }
}
