package service;

import model.Student;

import java.util.ArrayList;

public interface StudentService {
    ArrayList<Student> findWithPartialId(String partialId) throws Exception;

    void addStudent(String id, String name, String sex, String major, String school_date) throws Exception;

    void deleteStudent(String id) throws Exception;

    ArrayList<Student> getOnePageStudents(int currentIndex, int size) throws Exception;

    Student findStudentWithId(String id) throws Exception;

    ArrayList<Student> findStudentsWithName(String name) throws Exception;

    int getStudentCount() throws Exception;

    boolean isNumeric(String str);

    void updateStudentInfo(String stuno, String stuname, String stusex, String stumajor) throws Exception;

    void updateStudentInfo(String stuno, String stuname, String stusex, String stumajor, String password,int classId) throws Exception;

    void updateStudentSecurity(String stuno, String email, String password) throws Exception;

    void updateImg(String stuno, String img) throws Exception;
}
