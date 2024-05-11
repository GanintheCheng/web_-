package util;

import dao.impl.StudentDImpl;
import dao.impl.TeacherDImpl;

public class myuntils {
    public static StudentDImpl studentD = new StudentDImpl();
    public static TeacherDImpl teacherD = new TeacherDImpl();

    public static String getMaxStuId() throws Exception {
        return studentD.getMaxStudentId();
    }

    public static String getMaxTeaId() throws Exception {
        return teacherD.getMaxTeacherId();
    }
}
