package service.impl;

import dao.impl.ClassDImpl;
import model.Class;

import java.util.List;

public class ClassServiceIml {
    public ClassDImpl classD = new ClassDImpl();

    public Class getClassById(int id) throws Exception {
        return classD.getClassById(id);
    }

    public List<Class> getListClassesWithIds(List<Integer> ids) throws Exception {
        return classD.getListClassesWithIds(ids);
    }
}
