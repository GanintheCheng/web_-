package service.impl;

import dao.impl.ScoreDImpl;
import dao.impl.StudentDImpl;
import model.Student;
import service.StudentService;

import java.util.regex.Pattern;
import java.util.ArrayList;

public class StudentServiceIml implements StudentService {
    private final StudentDImpl studentDao;
    private final ScoreDImpl scoreDao;

    public StudentServiceIml() {
        this.studentDao = new StudentDImpl();
        this.scoreDao = new ScoreDImpl();
    }

    public ArrayList<Student> findWithPartialId(String partialId) throws Exception {
        return studentDao.findWithPartialId(partialId);
    }

    public void addStudent(String id, String name, String sex, String major, String school_date) throws Exception {
        studentDao.insertStudent(id, name, sex, school_date, major);
        scoreDao.insertScore(id);
    }

    public void deleteStudent(String id) throws Exception {
        studentDao.deleteStudent(id);
        scoreDao.deleteScore(id);
    }

    public ArrayList<Student> getOnePageStudents(int currentIndex, int size) throws Exception {
        return studentDao.getOnePage(currentIndex, size);
    }

    public Student findStudentWithId(String id) throws Exception {
        StudentDImpl studentDImpl = new StudentDImpl();
        return studentDImpl.findWithId(id);
    }

    public ArrayList<Student> findStudentsWithName(String name) throws Exception {
        return studentDao.findWithName(name);
    }

    public int getStudentCount() throws Exception {
        return studentDao.getStudentCount();
    }

    public boolean isNumeric(String str) {
        String pattern = "^\\d+";
        return Pattern.matches(pattern, str);
    }

    public void updateStudentInfo(String stuno, String stuname, String stusex, String stumajor) throws Exception {
        studentDao.updateStudentInfo(stuno, stuname, stusex, stumajor);
    }

    public void updateStudentInfo(String stuno, String stuname, String stusex, String stumajor, String password) throws Exception {
        studentDao.updateStudentInfo(stuno, stuname, stusex, stumajor, password);
    }

    public void updateStudentSecurity(String stuno, String email, String password) throws Exception {
        studentDao.updateStudentSecurity(stuno, email, password);
    }

    public void updateImg(String stuno, String img) throws Exception {
        studentDao.updateImg(stuno, img);
    }
}
