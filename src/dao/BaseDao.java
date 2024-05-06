package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {
    public Connection conn = null;
    public String driver ="com.mysql.cj.jdbc.Driver";
    public String account = "gzc";
    public String password = "gzc0816";

    public void initConnection() throws Exception {
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_manager?user="+account+"&password="+password);
        } catch (Exception e) {
            System.out.println("DAO连接数据库异常：" + e.getMessage());
        }
    }

    public void closeConnection() throws Exception {
        conn.close();
    }
}
