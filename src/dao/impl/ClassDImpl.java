package dao.impl;

import dao.BaseDao;
import model.Class;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClassDImpl extends BaseDao {
    public Class getClassById(int id) throws Exception {
        initConnection();
        Class c = null;
        Statement stat = conn.createStatement();
        String sql = "SELECT * FROM class where id = '" + id + "'";
        ResultSet rs = stat.executeQuery(sql);
        c = getClass(rs);
        closeConnection();
        return c;
    }

    public List<Class> getListClassesWithIds(List<Integer> ids) throws Exception {
        initConnection();
        List<Class> classes = new ArrayList<Class>();
        Statement stat = conn.createStatement();
        StringBuilder temp = new StringBuilder();
        for (Integer id : ids) {
            temp.append(id).append(",");
        }
        String sql = "SELECT * FROM class where id in (" + temp + ") ";
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()) {
            classes.add(getClass(rs));
            classes.add(getClass(rs));
        }
        return classes;
    }

    public Class getClass(ResultSet rs) throws SQLException {
        Class c = null;
        if (rs.next()) {
            c = new Class();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
        }
        return c;
    }

    public List<Class> getAllClasses() throws Exception {
        initConnection();
        List<Class> classes = new ArrayList<>();
        Statement stat = conn.createStatement();
        String sql = "SELECT * FROM class where id!=0";
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()) {
            Class c = new Class();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            classes.add(c);
        }
        closeConnection();
        return classes;
    }

     public void changeClassWithTeacherId(String[] classStrings, String teacherId) throws Exception {
        initConnection();
        PreparedStatement ps = null;
        try {
            // 删除老师对应的所有班级
            String deleteSql = "DELETE FROM teacher_class WHERE teacher_id = ?";
            ps = conn.prepareStatement(deleteSql);
            ps.setString(1, teacherId);
            ps.executeUpdate();

            // 插入新的班级
            String insertSql = "INSERT INTO teacher_class (teacher_id, class_id) VALUES (?, ?)";
            ps = conn.prepareStatement(insertSql);
            for (String classString : classStrings) {
                ps.setString(1, teacherId);
                ps.setString(2, classString);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("SQL错误");
        } finally {
            if (ps != null) {
                ps.close();
            }
            closeConnection();
        }
    }

    public void deleteTeacherClass(String teacherId) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "DELETE FROM teacher_class WHERE teacher_id = '" + teacherId + "'";
        stat.executeUpdate(sql);
        closeConnection();
    }
}
