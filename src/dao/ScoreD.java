package dao;

import model.Score;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public interface ScoreD {
    /**
     * @author GanintheCheng
     * 插入成绩信息
     * @param id 学生ID
     * @return 是否成功
     * @throws Exception 异常信息
     */
    public boolean insertScore(String id) throws Exception;

    /**
     * @author GanintheCheng
     * 删除成绩信息
     * @param id 学生ID
     * @throws Exception 异常信息
     */
    public void deleteScore(String id) throws Exception;

    /**
     * @author GanintheCheng
     * 更新成绩信息
     * @param id 学生ID
     * @param database 数据库成绩
     * @param android 安卓成绩
     * @param jsp JSP成绩
     * @throws Exception 异常信息
     */
    public void updateScoreInfo(String id, String database, String android, String jsp) throws Exception;

    /**
     * @author GanintheCheng
     * 通过学生ID查找成绩信息
     * @param id 学生ID
     * @return Score对象
     * @throws Exception 异常信息
     */
    public Score findWithId(String id) throws Exception;

    /**
     * @author GanintheCheng
     * 分页获取成绩信息
     * @param page 页码
     * @param size 每页大小
     * @return Score对象列表
     * @throws Exception 异常信息
     */
    public ArrayList<Score> getOnePage(int page, int size) throws Exception;

    /**
     * @author GanintheCheng
     * 获取成绩总数
     * @return 成绩总数
     * @throws Exception 异常信息
     */
    public int getScoreCount() throws Exception;

    /**
     * @author GanintheCheng
     * 通过ResultSet获取Score对象 用于插入，查询后封装单个score
     * @param rs 结果集
     * @return Score对象
     * @throws SQLException SQL异常
     */
    public Score getScore(ResultSet rs) throws SQLException;

    /**
     * @author GanintheCheng
     * 分页获取特定教师ID的成绩信息
     * @param page 页码
     * @param size 每页大小
     * @param teacherId 教师ID
     * @return Score对象列表
     * @throws Exception 异常信息
     */
    public ArrayList<Score> getOnePage(int page, int size, String teacherId) throws Exception;

    /**
     * @author GanintheCheng
     * 用rs封装score集合 用于插入，查询后封装多个score
     * @param al 成绩对象列表
     * @param rs 结果集
     * @throws SQLException SQL异常
     */
    public void getMoreScore(ArrayList<Score> al, ResultSet rs) throws SQLException;
}
