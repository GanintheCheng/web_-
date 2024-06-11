package service.impl;

import dao.impl.ScoreDImpl;
import model.Score;
import service.ScoreService;
import util.factory;

import java.util.ArrayList;

public class ScoreServiceImpl implements ScoreService {
    private final ScoreDImpl scoreDImpl = factory.getScoreDImpl();

    @Override
    public ArrayList<Score> getOnePageScores(int currentIndex, int size) throws Exception {
        ScoreDImpl scoD = new ScoreDImpl();
        return scoD.getOnePage(currentIndex, size);
    }

    public ArrayList<Score> getOnePageScores(int currentIndex, int size,String teacherId) throws Exception {
        ScoreDImpl scoD = new ScoreDImpl();
        return scoD.getOnePage(currentIndex, size,teacherId);
    }

    @Override
    public Score findScoreWithId(String id) throws Exception {
        ScoreDImpl scoreDImpl = new ScoreDImpl();
        return scoreDImpl.findWithId(id);
    }

    @Override
    public int getScoreCount() throws Exception {
        ScoreDImpl scoD = new ScoreDImpl();
        return scoD.getScoreCount();
    }

    @Override
    public void updateScores(String[] ids, String[] databases, String[] androids, String[] jsps) throws Exception {
        for (int i = 0; i < ids.length; i++) {
            scoreDImpl.updateScoreInfo(ids[i], databases[i], androids[i], jsps[i]);
        }
    }

    public void insertScore(String id) throws Exception {
        scoreDImpl.insertScore(id);
    }

    public void deleteScore(String id) throws Exception {
        scoreDImpl.deleteScore(id);
    }
}
