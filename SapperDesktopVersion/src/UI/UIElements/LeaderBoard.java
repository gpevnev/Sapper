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
        this.scoreService = new FileScoreService("resources/scores/scores.json");
    }

    public void addListener(ILeaderBoardListener listener) {
        listeners.add(listener);
    }

    @Override
    public void newScore(long score, DifficultyLevel difficultyLevel) {
        long previousHightScore = scoreService.getBestScore(difficultyLevel);
        scoreService.addScore(score, difficultyLevel);
        if (previousHightScore != score) {
            sayScoreChanged(score, difficultyLevel);
        }
    }


    public String getBeginnerLevelResult() {
        return TimeFormat.getTimeText(scoreService.getBestScore(DifficultyLevel.BEGINNER));
    }

    public String getEasyLevelResult() {
        return TimeFormat.getTimeText(scoreService.getBestScore(DifficultyLevel.EASY));
    }

    public String getNormalLevelResult() {
        return TimeFormat.getTimeText(scoreService.getBestScore(DifficultyLevel.NORMAL));
    }

    public String getHardLevelResult() {
        return TimeFormat.getTimeText(scoreService.getBestScore(DifficultyLevel.HARD));
    }
    public String getIntenseLevelResult() {
        return TimeFormat.getTimeText(scoreService.getBestScore(DifficultyLevel.INTENSE));
    }

    private void sayScoreChanged(long score, DifficultyLevel difficultyLevel) {
        for (ILeaderBoardListener listener: listeners) {
            listener.scoreChanged(TimeFormat.getTimeText(score), difficultyLevel);
        }
    }
}
