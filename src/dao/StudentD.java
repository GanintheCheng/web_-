package dao;

import model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentD {
    Student checkAccount(String user, String password) throws Exception;

    Student findWithId(String id) throws Exception;

    ArrayList<Student> findWithName(String name) throws Exception;

    boolean insertStudent(String id, String name, String sex, String school_date, String major) throws Exception;

    boolean deleteStudent(String id) throws Exception;

    ArrayList<Student> getOnePage(int page, int size) throws Exception;

    int getStudentCount() throws Exception;

    void getMoreStudent(ArrayList<Student> al, ResultSet rs) throws Exception;

    Student getStudent(ResultSet rs) throws Exception;

    void updateStudentInfo(String id, String name, String sex, String major) throws Exception;

    void updateStudentSecurity(String id, String email, String password) throws Exception;

    void updateImg(String id, String img) throws Exception;
}
