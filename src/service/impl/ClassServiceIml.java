package service.impl;

import dao.impl.ClassDImpl;
import model.Class;
import util.factory;

import java.util.List;

public class ClassServiceIml {
    public ClassDImpl classD = factory.getClassDImpl();

    public Class getClassById(int id) throws Exception {
        return classD.getClassById(id);
    }

    public List<Class> getListClassesWithIds(List<Integer> ids) throws Exception {
        return classD.getListClassesWithIds(ids);
    }

    public List<Class> getAllClasses() throws Exception {
        return classD.getAllClasses();
    }
}
