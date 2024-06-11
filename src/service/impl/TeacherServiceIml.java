package service.impl;

import dao.impl.ClassDImpl;
import dao.impl.ScoreDImpl;
import dao.impl.TeacherDImpl;
import model.Teacher;
import service.TeacherService;
import util.factory;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TeacherServiceIml implements TeacherService {
    private final ScoreDImpl scoreDao = factory.getScoreDImpl();
    private final TeacherDImpl teacherDao = factory.getTeacherDImpl();
    private final ClassDImpl classDao = factory.getClassDImpl();

    @Override
    public ArrayList<Teacher> getOnePageTeachers(int currentIndex, int size) throws Exception {
        return teacherDao.getOnePage(currentIndex, size);
    }

    @Override
    public int getTeacherCount() throws Exception {
        return teacherDao.getTeacherCount();
    }

    @Override
    public boolean isNumeric(String str) {
        String pattern = "^\\d+";
        return Pattern.matches(pattern, str);
    }

    @Override
    public Teacher findTeacherWithId(String id) throws Exception {
        return teacherDao.findWithId(id);
    }

    @Override
    public ArrayList<Teacher> findTeachersWithName(String name) throws Exception {
        return teacherDao.findWithName(name);
    }

    @Override
    public void updateTeacherInfo(String id, String name, String sex, String password) throws Exception {
        teacherDao.updateTeacherInfo(id, name, sex, password);
    }

    public void updateTeacherInfo(String[] strings,String id, String name, String sex, String password) throws Exception {
        classDao.changeClassWithTeacherId(strings,id);
        teacherDao.updateTeacherInfo(id, name, sex, password);
    }

    @Override
    public void deleteTeacher(String id) throws Exception {
        teacherDao.deleteTeacher(id);
    }

    @Override
    public void addTeacher(String id, String name, String password, String sex, String email) throws Exception {
        teacherDao.insertTeacher(id, name, password, sex, email);
    }

    @Override
    public Teacher updateTeacher(String uid, String name, String sex, String email, String password) throws Exception {
        return teacherDao.updateTeacher(uid, name, sex, email, password);
    }

    @Override
    public void updateTeacherPassword(String uid, String password) throws Exception {
        teacherDao.updateTeacherPassword(uid, password);
    }

    @Override
    public void updateImg(String uid, String img) throws Exception {
        teacherDao.updateImg(uid, img);
    }
}
