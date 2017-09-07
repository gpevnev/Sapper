package score;

import UI.UIElements.DifficultyLevel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class SimpleScoreService implements ScoreService {
    protected Map<DifficultyLevel, TreeSet<Long>> results;
    private int maxScores;

    public SimpleScoreService() {
        this(20);
    }

    public SimpleScoreService(int maxScores) {
        results = new HashMap<>();
        for (DifficultyLevel difficultyLevel : DifficultyLevel.values()) {
            results.put(difficultyLevel, new TreeSet<>());
        }

        this.maxScores = maxScores;
    }

    @Override
    public void addScore(long score, DifficultyLevel difficultyLevel) {
        TreeSet<Long> a = results.get(difficultyLevel);
        a.add(score);
        if (a.size() > maxScores) {
            Iterator<Long> it = a.descendingIterator();
            it.next();
            it.remove();
        }
    }

    @Override
    public TreeSet<Long> getScore(DifficultyLevel difficultyLevel) {
        return results.get(difficultyLevel);
    }

    @Override
    public long getBestScore(DifficultyLevel difficultyLevel) {
        return results.get(difficultyLevel).isEmpty() ?
                -1 :
                results.get(difficultyLevel).descendingIterator().next();
    }

    protected void print() {
        for (DifficultyLevel difficultyLevel : DifficultyLevel.values()) {
            System.out.print(difficultyLevel.name() + ": ");
            if (results.get(difficultyLevel).isEmpty()) {
                System.out.print("empty");
            }
            for (Long i : results.get(difficultyLevel)) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SimpleScoreService a = new SimpleScoreService(3);
        a.addScore(1,DifficultyLevel.EASY);
        a.addScore(2, DifficultyLevel.EASY);
        a.addScore(3, DifficultyLevel.EASY);
        a.addScore(4, DifficultyLevel.EASY);
        a.addScore(-1, DifficultyLevel.EASY);
        a.print();
    }
}
