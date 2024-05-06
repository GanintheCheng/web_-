package dao;

import model.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface TeacherD {
    public Teacher checkAccount(String id, String password) throws Exception;

    public Teacher findWithId(String id) throws Exception;

    public Teacher insertTeacher(String id, String password, String email) throws Exception;

    public Teacher updateTeacher(String id, String name, String sex, String email, String password) throws Exception;

    public void updateTeacherPassword(String id, String password) throws Exception;

    public Teacher getTeacher(ResultSet rs) throws SQLException ;

    public void updateImg(String id, String img) throws Exception;
}
