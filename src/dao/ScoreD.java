package dao;

import model.Score;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public interface ScoreD {
    public boolean insertScore(String id) throws Exception;

    public boolean deleteScore(String id) throws Exception;

    public void updateScoreInfo(String id, String database, String android, String jsp) throws Exception;

    public Score findWithId(String id) throws Exception;

    public ArrayList<Score> getOnePage(int page, int size) throws Exception;

    public int getScoreCount() throws Exception;

    public Score getScore(ResultSet rs) throws SQLException;

    public void getMoreScore(ArrayList<Score> al, ResultSet rs) throws SQLException;
}
