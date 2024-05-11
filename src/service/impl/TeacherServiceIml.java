package service.impl;

import dao.impl.ScoreDImpl;
import dao.impl.StudentDImpl;
import dao.impl.TeacherDImpl;
import model.Student;
import model.Teacher;
import service.TeacherService;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TeacherServiceIml implements TeacherService {
    private final ScoreDImpl scoreDao = new ScoreDImpl();
    private final TeacherDImpl teacherDao = new TeacherDImpl();

    public ArrayList<Teacher> getOnePageTeachers(int currentIndex, int size) throws Exception {
        return teacherDao.getOnePage(currentIndex, size);
    }

    public int getTeacherCount() throws Exception {
        return teacherDao.getTeacherCount();
    }

    public boolean isNumeric(String str) {
        String pattern = "^\\d+";
        return Pattern.matches(pattern, str);
    }

    public Teacher findTeacherWithId(String id) throws Exception {
        return teacherDao.findWithId(id);
    }

    public ArrayList<Teacher> findTeachersWithName(String name) throws Exception {
        return teacherDao.findWithName(name);
    }

    public void updateTeacherInfo(String id, String name, String sex, String password) throws Exception {
        teacherDao.updateTeacherInfo(id, name, sex, password);
    }

    public void deleteTeacher(String id) throws Exception {
        teacherDao.deleteTeacher(id);
    }

    public void addTeacher(String id, String name, String password, String sex, String email) throws Exception {
        teacherDao.insertTeacher(id, name, password, sex, email);
    }

    public Teacher updateTeacher(String uid, String name, String sex, String email, String password) throws Exception {
        return teacherDao.updateTeacher(uid, name, sex, email, password);
    }

    public void updateTeacherPassword(String uid, String password) throws Exception {
        teacherDao.updateTeacherPassword(uid, password);
    }

    public void updateImg(String uid, String img) throws Exception {
        teacherDao.updateImg(uid, img);
    }
}
