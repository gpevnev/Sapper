package UI.UIElements;

import UI.panels.IScorePanelListener;
import score.FileScoreService;
import score.ScoreService;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by @author Telnov Sergey on 26.07.2017.
 */

public class LeaderBoard implements IScorePanelListener {
    private ArrayList<ILeaderBoardListener> listeners;
    private ScoreService scoreService;

    public LeaderBoard() {
        this.listeners = new ArrayList<>();
        this.scoreService = new FileScoreService("resources/scores/scores.txt");
    }

    public void addListener(ILeaderBoardListener listener) {
        listeners.add(listener);
    }

    @Override
    public void newScore(long score, LevelDifficulty levelDifficulty) {
        long previousHightScore = scoreService.getBestScore(levelDifficulty);
        scoreService.addScore(score, levelDifficulty);
        if (previousHightScore != score) {
            sayScoreChanged(score, levelDifficulty);
        }
    }


    public String getBeginnerLevelResult() {
        return TimeFormat.getTimeText(scoreService.getBestScore(LevelDifficulty.BEGINNER));
    }

    public String getEasyLevelResult() {
        return TimeFormat.getTimeText(scoreService.getBestScore(LevelDifficulty.EASY));
    }

    public String getNormalLevelResult() {
        return TimeFormat.getTimeText(scoreService.getBestScore(LevelDifficulty.NORMAL));
    }

    public String getHardLevelResult() {
        return TimeFormat.getTimeText(scoreService.getBestScore(LevelDifficulty.HARD));
    }
    public String getIntenseLevelResult() {
        return TimeFormat.getTimeText(scoreService.getBestScore(LevelDifficulty.INTENSE));
    }

    private void sayScoreChanged(long score, LevelDifficulty levelDifficulty) {
        for (ILeaderBoardListener listener: listeners) {
            listener.scoreChanged(TimeFormat.getTimeText(score), levelDifficulty);
        }
    }
}
