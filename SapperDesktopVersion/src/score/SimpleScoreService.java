package score;

import UI.UIElements.LevelDifficulty;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class SimpleScoreService implements ScoreService {
    protected Map<LevelDifficulty, TreeSet<Long>> results;
    private int maxScores;

    public SimpleScoreService() {
        this(20);
    }

    public SimpleScoreService(int maxScores) {
        results = new HashMap<>();
        for (LevelDifficulty levelDifficulty : LevelDifficulty.values()) {
            results.put(levelDifficulty, new TreeSet<>());
        }

        this.maxScores = maxScores;
    }

    @Override
    public void addScore(long score, LevelDifficulty levelDifficulty) {
        TreeSet<Long> a = results.get(levelDifficulty);
        a.add(score);
        if (a.size() > maxScores) {
            Iterator<Long> it = a.descendingIterator();
            it.next();
            it.remove();
        }
    }

    @Override
    public TreeSet<Long> getScore(LevelDifficulty levelDifficulty) {
        return results.get(levelDifficulty);
    }

    @Override
    public long getBestScore(LevelDifficulty levelDifficulty) {
        return results.get(levelDifficulty).isEmpty() ?
                -1 :
                results.get(levelDifficulty).descendingIterator().next();
    }

    protected void print() {
        for (LevelDifficulty levelDifficulty : LevelDifficulty.values()) {
            System.out.print(levelDifficulty.name() + ": ");
            if (results.get(levelDifficulty).isEmpty()) {
                System.out.print("empty");
            }
            for (Long i : results.get(levelDifficulty)) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SimpleScoreService a = new SimpleScoreService(3);
        a.addScore(1,LevelDifficulty.EASY);
        a.addScore(2, LevelDifficulty.EASY);
        a.addScore(3, LevelDifficulty.EASY);
        a.addScore(4, LevelDifficulty.EASY);
        a.addScore(-1, LevelDifficulty.EASY);
        a.print();
    }
}
