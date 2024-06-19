package service;

import model.Score;

import java.util.ArrayList;

public interface ScoreService {
    /**
     * 分页获取成绩信息
     * @param currentIndex 当前页码
     * @param size 每页大小
     * @return 成绩对象列表
     * @throws Exception 异常信息
     */
    ArrayList<Score> getOnePageScores(int currentIndex, int size) throws Exception;

    /**
     * 通过学生ID查找成绩信息
     * @param id 学生ID
     * @return Score对象
     * @throws Exception 异常信息
     */
    Score findScoreWithId(String id) throws Exception;

    /**
     * 获取成绩总数
     * @return 成绩总数
     * @throws Exception 异常信息
     */
    int getScoreCount() throws Exception;

    /**
     * 批量更新成绩信息
     * @param ids 学生ID数组
     * @param databases 数据库成绩数组
     * @param androids 安卓成绩数组
     * @param jsps JSP成绩数组
     * @throws Exception 异常信息
     */
    void updateScores(String[] ids, String[] databases, String[] androids, String[] jsps) throws Exception;

    /**
     * 分页获取特定教师ID的成绩信息
     * @param currentIndex 当前页码
     * @param size 每页大小
     * @param teacherId 教师ID
     * @return 成绩对象列表
     * @throws Exception 异常信息
     */
    public ArrayList<Score> getOnePageScores(int currentIndex, int size,String teacherId) throws Exception;

    /**
     * 插入成绩信息
     * @param id 学生ID
     * @throws Exception 异常信息
     */
    public void insertScore(String id) throws Exception;

    /**
     * 删除成绩信息
     * @param id 学生ID
     * @throws Exception 异常信息
     */
    public void deleteScore(String id) throws Exception;
}
