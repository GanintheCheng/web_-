package service;

import model.Score;

import java.util.ArrayList;

public interface ScoreService {
    ArrayList<Score> getOnePageScores(int currentIndex, int size) throws Exception;

    Score findScoreWithId(String id) throws Exception;

    int getScoreCount() throws Exception;

    void updateScores(String[] ids, String[] databases, String[] androids, String[] jsps) throws Exception;
}
