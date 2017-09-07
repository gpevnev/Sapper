package score;

import UI.UIElements.LevelDifficulty;

import java.util.TreeSet;

public interface ScoreService {
    void addScore(long score, LevelDifficulty levelDifficulty);

    TreeSet<Long> getScore(LevelDifficulty levelDifficulty);

    long getBestScore(LevelDifficulty levelDifficulty);
}
