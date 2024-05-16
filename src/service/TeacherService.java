package service;

import model.Teacher;

import java.util.ArrayList;

public interface TeacherService {
    ArrayList<Teacher> getOnePageTeachers(int currentIndex, int size) throws Exception;

    int getTeacherCount() throws Exception;

    boolean isNumeric(String str);

    Teacher findTeacherWithId(String id) throws Exception;

    ArrayList<Teacher> findTeachersWithName(String name) throws Exception;

    void updateTeacherInfo(String id, String name, String sex, String password) throws Exception;

    void deleteTeacher(String id) throws Exception;

    void addTeacher(String id, String name, String password, String sex, String email) throws Exception;

    Teacher updateTeacher(String uid, String name, String sex, String email, String password) throws Exception;

    void updateTeacherPassword(String uid, String password) throws Exception;

    void updateImg(String uid, String img) throws Exception;
}
