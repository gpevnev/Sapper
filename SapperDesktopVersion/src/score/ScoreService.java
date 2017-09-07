package score;

import UI.UIElements.DifficultyLevel;

import java.util.TreeSet;

public interface ScoreService {
    void addScore(long score, DifficultyLevel difficultyLevel);

    TreeSet<Long> getScore(DifficultyLevel difficultyLevel);

    long getBestScore(DifficultyLevel difficultyLevel);
}
