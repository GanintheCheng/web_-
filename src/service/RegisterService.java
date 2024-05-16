package service;

import model.Teacher;

public interface RegisterService {
    Teacher registerTeacher(String user, String password, String email) throws Exception;
}
