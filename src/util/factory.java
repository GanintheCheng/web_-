package util;

import dao.impl.*;
import service.impl.*;

public class factory {
    private static final AdminDImpl adminD = new AdminDImpl();
    private static final StudentDImpl studentD = new StudentDImpl();
    private static final TeacherDImpl teacherD = new TeacherDImpl();
    private static final ClassDImpl classD = new ClassDImpl();
    private static final ScoreDImpl scoreD = new ScoreDImpl();

    private static final StudentServiceIml studentServiceIml = new StudentServiceIml();
    private static final TeacherServiceIml teacherServiceIml = new TeacherServiceIml();
    private static final ClassServiceIml classServiceIml = new ClassServiceIml();
    private static final ScoreServiceImpl scoreServiceIml = new ScoreServiceImpl();
    private static final AdminServiceImpl adminServiceIml = new AdminServiceImpl();

    public static AdminDImpl getAdminDImpl() {
        return adminD;
    }

    public static StudentDImpl getStudentDImpl() {
        return studentD;
    }

    public static TeacherDImpl getTeacherDImpl() {
        return teacherD;
    }

    public static ClassDImpl getClassDImpl() {
        return classD;
    }

    public static ScoreDImpl getScoreDImpl() {
        return scoreD;
    }

    public static StudentServiceIml getStudentServiceIml() {
        return studentServiceIml;
    }

    public static TeacherServiceIml getTeacherServiceIml() {
        return teacherServiceIml;
    }

    public static ClassServiceIml getClassServiceIml() {
        return classServiceIml;
    }

    public static ScoreServiceImpl getScoreServiceImpl() {return scoreServiceIml;}

    public static AdminServiceImpl getAdminServiceImpl() {return adminServiceIml;}
}
