package service.impl;

import dao.impl.ScoreDImpl;
import model.Score;
import service.ScoreService;

import java.util.ArrayList;

public class ScoreServiceImpl implements ScoreService {
    private final ScoreDImpl scoreDImpl = new ScoreDImpl();

    @Override
    public ArrayList<Score> getOnePageScores(int currentIndex, int size) throws Exception {
        return scoreDImpl.getOnePage(currentIndex, size);
    }

    @Override
    public ArrayList<Score> getOnePageScores(int currentIndex, int size,String teacherId) throws Exception {
        return scoreDImpl.getOnePage(currentIndex, size,teacherId);
    }

    @Override
    public Score findScoreWithId(String id) throws Exception {
        return scoreDImpl.findWithId(id);
    }

    @Override
    public int getScoreCount() throws Exception {
        return scoreDImpl.getScoreCount();
    }

    @Override
    public void updateScores(String[] ids, String[] databases, String[] androids, String[] jsps) throws Exception {
        for (int i = 0; i < ids.length; i++) {
            scoreDImpl.updateScoreInfo(ids[i], databases[i], androids[i], jsps[i]);
        }
    }

    @Override
    public void insertScore(String id) throws Exception {
        scoreDImpl.insertScore(id);
    }

    @Override
    public void deleteScore(String id) throws Exception {
        scoreDImpl.deleteScore(id);
    }
}
