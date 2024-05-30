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

    @Override
    public ArrayList<Student> findWithPartialId(String partialId) throws Exception {
        return studentDao.findWithPartialId(partialId);
    }

    @Override
    public void addStudent(String id, String name, String sex, String major, String school_date) throws Exception {
        studentDao.insertStudent(id, name, sex, school_date, major);
        scoreDao.insertScore(id);
    }

    @Override
    public void deleteStudent(String id) throws Exception {
        scoreDao.deleteScore(id);
        studentDao.deleteStudent(id);
    }

    @Override
    public ArrayList<Student> getOnePageStudents(int currentIndex, int size) throws Exception {
        return studentDao.getOnePage(currentIndex, size);
    }

    public ArrayList<Student> getOnePageStudents(int currentIndex, int size,String teacherId) throws Exception {
        return studentDao.getOnePage(currentIndex, size,teacherId);
    }

    @Override
    public Student findStudentWithId(String id) throws Exception {
        return studentDao.findWithId(id);
    }

    @Override
    public ArrayList<Student> findStudentsWithName(String name) throws Exception {
        return studentDao.findWithName(name);
    }

    @Override
    public int getStudentCount() throws Exception {
        return studentDao.getStudentCount();
    }

    public int getStudentCount(String teacherId) throws Exception {
        return studentDao.getStudentCount(teacherId);
    }

    @Override
    public boolean isNumeric(String str) {
        String pattern = "^\\d+";
        return Pattern.matches(pattern, str);
    }

    @Override
    public void updateStudentInfo(String stuno, String stuname, String stusex, String stumajor) throws Exception {
        studentDao.updateStudentInfo(stuno, stuname, stusex, stumajor);
    }

    @Override
    public void updateStudentInfo(String stuno, String stuname, String stusex, String stumajor, String password) throws Exception {
        studentDao.updateStudentInfo(stuno, stuname, stusex, stumajor, password);
    }

    @Override
    public void updateStudentSecurity(String stuno, String email, String password) throws Exception {
        studentDao.updateStudentSecurity(stuno, email, password);
    }

    @Override
    public void updateImg(String stuno, String img) throws Exception {
        studentDao.updateImg(stuno, img);
    }
}
