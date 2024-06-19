package service.impl;

import dao.impl.ClassDImpl;
import model.Class;
import service.ClassService;

import java.util.List;

public class ClassServiceIml implements ClassService {
    public ClassDImpl classD = new ClassDImpl();

    @Override
    public Class getClassById(int id) throws Exception {
        return classD.getClassById(id);
    }

    @Override
    public List<Class> getListClassesWithIds(List<Integer> ids) throws Exception {
        return classD.getListClassesWithIds(ids);
    }

    @Override
    public List<Class> getAllClasses() throws Exception {
        return classD.getAllClasses();
    }
}
