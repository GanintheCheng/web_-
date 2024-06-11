package dao.impl;

import dao.BaseDao;
import dao.ScoreD;
import dao.StudentD;
import model.Class;
import model.Student;
import model.Teacher;
import service.impl.ClassServiceIml;
import service.impl.TeacherServiceIml;
import util.factory;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDImpl extends BaseDao implements StudentD {
    //    ClassServiceIml classService = factory.getClassServiceIml();
//    TeacherServiceIml teacherService = factory.getTeacherServiceIml();
    TeacherDImpl teacherD = new TeacherDImpl();
    ClassDImpl classD = new ClassDImpl();

    @Override
    public Student checkAccount(String user, String password) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from student where account = '" + user + "' and password = '" + password + "'";
        ResultSet rs = stat.executeQuery(sql);
        Student stu = getStudent(rs);
        closeConnection();
        return stu;
    }

    @Override
    public Student findWithId(String id) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from student where id = '" + id + "'";
        ResultSet rs = stat.executeQuery(sql);
        Student stu = getStudent(rs);
        closeConnection();
        return stu;
    }

    public ArrayList<Student> findWithPartialId(String partialId) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "SELECT * FROM student WHERE id LIKE '%" + partialId + "%'";
        ResultSet rs = stat.executeQuery(sql);
        ArrayList<Student> students = new ArrayList<>();
        while (rs.next()) {
            Student student = getStudent(rs);
            students.add(student);
        }
        closeConnection();
        return students;
    }

    @Override
    public ArrayList<Student> findWithName(String name) throws Exception {
        ArrayList<Student> al = new ArrayList<>();
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from student where name = '" + name + "'";
        ResultSet rs = stat.executeQuery(sql);
        getMoreStudent(al, rs);
        closeConnection();
        return al;
    }

    @Override
    public boolean insertStudent(String id, String name, String sex, String school_date, String major) throws Exception {
        initConnection();
        String sql = "insert into student(id, account, name, sex, school_date, major) values(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, id);
        ps.setString(3, name);
        ps.setString(4, sex);
        ps.setString(5, school_date);
        ps.setString(6, major);
        int i = ps.executeUpdate();
        closeConnection();
        return i == 1;
    }

    public boolean insertStudent(String id, String name, String sex, String school_date, String major, int stuClass) throws Exception {
        initConnection();
        String sql = "insert into student(id, account, name, sex, school_date, major,class_id) values(?, ?, ?, ?, ?, ?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, id);
        ps.setString(3, name);
        ps.setString(4, sex);
        ps.setString(5, school_date);
        ps.setString(6, major);
        ps.setInt(7, stuClass);
        int i = ps.executeUpdate();
        closeConnection();
        return i == 1;
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "delete from student where id='" + id + "'";
        int i = stat.executeUpdate(sql);
        closeConnection();
        return i == 1;
    }

    @Override
    public ArrayList<Student> getOnePage(int page, int size) throws Exception {
        ArrayList<Student> al = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM student limit ?, ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, (page - 1) * size);
        ps.setInt(2, size);
        ResultSet rs = ps.executeQuery();
        getMoreStudent(al, rs);
        closeConnection();
        return al;
    }

    public ArrayList<Student> getOnePage(int page, int size, String teacherId) throws Exception {
        ArrayList<Student> al = new ArrayList<>();
        initConnection();
        List<Class> classList = teacherD.findWithId(teacherId).getClassList();
        StringBuilder temp = new StringBuilder();
        for (Class c : classList) {
            temp.append(c.getId()).append(",");
        }
        // 去除最后一个逗号
        if (!temp.isEmpty()) {
            temp.setLength(temp.length() - 1);
        }
        String sql = "SELECT * FROM student WHERE class_id IN (" + temp.toString() + ") LIMIT ?, ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, (page - 1) * size);
        ps.setInt(2, size);
        ResultSet rs = ps.executeQuery();
        getMoreStudent(al, rs);
        closeConnection();
        return al;
    }

    @Override
    public int getStudentCount() throws Exception {
        initConnection();
        String sql = "select count(*) from student";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        rs.next();
        int count = rs.getInt(1);
        closeConnection();
        return count;
    }

    public int getStudentCount(String teacherId) throws Exception {
        initConnection();
        List<Class> classList = teacherD.findWithId(teacherId).getClassList();
        StringBuilder temp = new StringBuilder();
        for (Class c : classList) {
            temp.append(c.getId()).append(",");
        }
        // 去除最后一个逗号
        if (!temp.isEmpty()) {
            temp.setLength(temp.length() - 1);
        }
        String sql = "select count(*) from student WHERE class_id IN (" + temp.toString() + ")";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        rs.next();
        int count = rs.getInt(1);
        closeConnection();
        return count;
    }

    @Override
    public void updateStudentInfo(String id, String name, String sex, String major) throws Exception {

        initConnection();
        String sql = "update student set name=?, sex=?, major=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, sex);
        ps.setString(3, major);
        ps.setString(4, id);
        ps.executeUpdate();
        closeConnection();
    }

    public void updateStudentInfo(String id, String name, String sex, String major, String password,int classId) throws Exception {
        initConnection();
        String sql = "update student set name=?, sex=?, major=?, password=?, class_id=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, sex);
        ps.setString(3, major);
        ps.setString(4, password);
        ps.setInt(5,classId);
        ps.setString(6, id);
        ps.executeUpdate();
        closeConnection();
    }


    @Override
    public void updateStudentSecurity(String id, String email, String password) throws Exception {

        initConnection();
        String sql = "update student set password=?, email=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, password);
        ps.setString(2, email);
        ps.setString(3, id);
        ps.executeUpdate();
        closeConnection();
    }

    @Override
    public void updateImg(String id, String img) throws Exception {
        initConnection();
        String sql = "update student set img=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, img);
        ps.setString(2, id);
        ps.executeUpdate();
        closeConnection();
    }

    @Override
    public Student getStudent(ResultSet rs) throws Exception {
        Student stu = null;
        int classId = -1;
        if (rs.next()) {
            stu = new Student();
            stu.setId(rs.getString("id"));
            stu.setAccount(rs.getString("account"));
            stu.setPassword(rs.getString("password"));
            stu.setName(rs.getString("name"));
            stu.setSex(rs.getString("sex"));
            stu.setSchool_date(rs.getString("school_date"));
            stu.setMajor(rs.getString("major"));
            stu.setEmail(rs.getString("email"));
            stu.setImg(rs.getString("img"));
            classId = rs.getInt("class_id");
        }
        if (classId != -1) {
            stu.setClass(classD.getClassById(classId));
        }
        return stu;
    }

    @Override
    public void getMoreStudent(ArrayList<Student> al, ResultSet rs) throws Exception {
        while (rs.next()) {
            Student stu = new Student();
            stu.setId(rs.getString("id"));
            stu.setPassword(rs.getString("password"));
            stu.setName(rs.getString("name"));
            stu.setSex(rs.getString("sex"));
            stu.setSchool_date(rs.getString("school_date"));
            stu.setMajor(rs.getString("major"));
            stu.setEmail(rs.getString("email"));
            stu.setClass(classD.getClassById(rs.getInt("class_id")));
            al.add(stu);
        }
    }

    public String getMaxStudentId() throws Exception {
        initConnection();
        String maxIdString = null;
        try {
            String sql = "SELECT MAX(CAST(id AS UNSIGNED)) AS max_id FROM student";
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


    public ArrayList<Student> getStudentListWithTeacherId(String teacherId) throws Exception {
        initConnection();
        ArrayList<Student> students = new ArrayList<>();

        String sql = "SELECT s.* FROM Student s " +
                "JOIN teacher_class tc ON s.class_id = tc.class_id " +
                "JOIN teacher t ON tc.teacher_id = t.id " +
                "WHERE t.id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, teacherId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Student student = new Student();
            student.setId(rs.getString("id"));
            student.setName(rs.getString("name"));
            student.setClass(classD.getClassById(rs.getInt("class_id")));
            students.add(student);
        }
        closeConnection();
        return students;
    }

}
