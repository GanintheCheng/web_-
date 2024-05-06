package service.impl;

import dao.impl.ScoreDImpl;
import model.Score;
import service.ScoreService;

import java.util.ArrayList;

public class ScoreServiceImpl implements ScoreService {
    private final ScoreDImpl scoreDImpl = new ScoreDImpl();

    public ArrayList<Score> getOnePageScores(int currentIndex, int size) throws Exception {
        ScoreDImpl scoD = new ScoreDImpl();
        return scoD.getOnePage(currentIndex, size);
    }

    public Score findScoreWithId(String id) throws Exception {
        ScoreDImpl scoreDImpl = new ScoreDImpl();
        return scoreDImpl.findWithId(id);
    }

    public int getScoreCount() throws Exception {
        ScoreDImpl scoD = new ScoreDImpl();
        return scoD.getScoreCount();
    }

    public void updateScores(String[] ids, String[] databases, String[] androids, String[] jsps) throws Exception {
        for (int i = 0; i < ids.length; i++) {
            scoreDImpl.updateScoreInfo(ids[i], databases[i], androids[i], jsps[i]);
        }
    }
}
