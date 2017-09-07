package UI.panels;

import UI.UIElements.DifficultyLevel;

/**
 * Created by @author Telnov Sergey on 05.09.2017.
 */

public interface IScorePanelListener {
    void newScore(long score, DifficultyLevel difficultyLevel);
}
