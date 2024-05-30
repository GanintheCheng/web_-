package dao.impl;

import dao.BaseDao;
import model.Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClassDImpl extends BaseDao {
    public Class getClassById(int id) throws Exception {
        initConnection();
        Class c = null;
        Statement stat = conn.createStatement();
        String sql = "SELECT * FROM class where id = '" + id + "'";
        ;
        ResultSet rs = stat.executeQuery(sql);
        c = getClass(rs);
        closeConnection();
        return c;
    }

    public List<Class> getListClassesWithIds(List<Integer> ids) throws Exception {
        initConnection();
        List<Class> classes = new ArrayList<Class>();
        Statement stat = conn.createStatement();
        StringBuilder temp = new StringBuilder();
        for (Integer id : ids) {
            temp.append(id).append(",");
        }
        String sql = "SELECT * FROM class where id in (" + temp + ") ";
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()) {
            classes.add(getClass(rs));
            classes.add(getClass(rs));
        }
        return classes;
    }

    public Class getClass(ResultSet rs) throws SQLException {
        Class c = null;
        if (rs.next()) {
            c = new Class();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
        }
        return c;
    }
}
