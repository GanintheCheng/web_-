package dao.impl;

import dao.BaseDao;
import dao.ScoreD;
import model.Score;
import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class ScoreDImpl extends BaseDao implements ScoreD {
//    StudentServiceIml studentService = factory.getStudentServiceIml();
    StudentDImpl studentD = new StudentDImpl();

    @Override
    public boolean insertScore(String id) throws Exception {
        initConnection();
        String sql = "insert into score(id) values(?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        int i = ps.executeUpdate();
        closeConnection();
        return i == 1;
    }

    @Override
    public void deleteScore(String id) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "delete from score where id='" + id + "'";
        stat.executeUpdate(sql);
        closeConnection();
    }

    @Override
    public void updateScoreInfo(String id, String database, String android, String jsp) throws Exception {

        initConnection();
        String sql = "update score set dat=?, android=?, jsp=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, database);
        ps.setString(2, android);
        ps.setString(3, jsp);
        ps.setString(4, id);
        ps.executeUpdate();
        closeConnection();
    }

    @Override
    public Score findWithId(String id) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from score where id = '" + id + "'";
        ResultSet rs = stat.executeQuery(sql);
        Score stu = getScore(rs);
        closeConnection();
        return stu;
    }

    @Override
    public ArrayList<Score> getOnePage(int page, int size) throws Exception {
        ArrayList<Score> al = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM score limit ?, ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, (page - 1) * size);
        ps.setInt(2, size);
        ResultSet rs = ps.executeQuery();
        getMoreScore(al, rs);
        closeConnection();
        return al;
    }

    public ArrayList<Score> getOnePage(int page, int size, String teacherId) throws Exception {
        ArrayList<Score> al = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        students = studentD.getStudentListWithTeacherId(teacherId);
        HashSet<String> Strings = new HashSet<>();
        for (Student student : students) {
            Strings.add(student.getId());
        }
        StringBuilder temp = new StringBuilder();
        for (String id : Strings) {
            temp.append(id+",");
        }
        String res=temp.substring(0,temp.length()-1);
        initConnection();
        String sql = "SELECT * FROM score  where id in ("+res+") limit ?, ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, (page - 1) * size);
        ps.setInt(2, size);
        ResultSet rs = ps.executeQuery();
        getMoreScore(al, rs);
        closeConnection();
        return al;
    }

    @Override
    public int getScoreCount() throws Exception {
        initConnection();
        String sql = "select count(*) from score";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        rs.next();
        int count = rs.getInt(1);
        closeConnection();
        return count;
    }

    @Override
    public Score getScore(ResultSet rs) throws SQLException {
        Score stu = null;
        if (rs.next()) {
            stu = new Score();
            stu.setId(rs.getString("id"));
            stu.setDatabase(rs.getString("dat"));
            stu.setAndroid(rs.getString("android"));
            stu.setJsp(rs.getString("jsp"));
        }
        return stu;
    }

    @Override
    public void getMoreScore(ArrayList<Score> al, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Score score = new Score();
            score.setId(rs.getString("id"));
            score.setDatabase(rs.getString("dat"));
            score.setAndroid(rs.getString("android"));
            score.setJsp(rs.getString("jsp"));
            al.add(score);
        }
    }
}
