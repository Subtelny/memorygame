package pl.janda.memory.view.menu;

import pl.janda.memory.controller.NewGameController;
import pl.janda.memory.controller.RankingReaderController;
import pl.janda.memory.view.game.NewGameFrame;
import pl.janda.memory.view.ranking.RankingFrame;

import javax.swing.*;
import java.awt.*;

public class NavigationPanel extends JPanel {

    private static final String NEW_GAME = "New Game";

    private static final String HIGH_SCORES = "High Scores";

    private static final String EXIT = "Exit";

    private final MenuFrame menuFrame;

    private final NewGameController gameController;

    private final RankingReaderController rankingReaderController;

    public NavigationPanel(MenuFrame menuFrame, NewGameController gameController, RankingReaderController rankingReaderController) {
        this.menuFrame = menuFrame;
        this.gameController = gameController;
        this.rankingReaderController = rankingReaderController;
        setup();
    }

    private void setup() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addNewGameButton();
        addSpace();
        addHighScoresButton();
        addSpace();
        addExitButton();
    }

    private void addHighScoresButton() {
        JButton highScores = getButton(HIGH_SCORES);
        highScores.addActionListener(e -> {
            RankingFrame rankingFrame = new RankingFrame(menuFrame, rankingReaderController);
            rankingFrame.launch();
        });
        add(highScores);
    }

    private void addNewGameButton() {
        JButton newGame = getButton(NEW_GAME);
        newGame.addActionListener(e -> new NewGameFrame(menuFrame, gameController).launch());
        add(newGame);
    }

    private void addExitButton() {
        JButton exit = getButton(EXIT);
        exit.addActionListener(e -> {
            menuFrame.dispose();
            System.exit(1);
        });
        add(exit);
    }

    private void addSpace() {
        add(Box.createVerticalStrut(10));
    }

    private JButton getButton(String title) {
        BetterButton betterButton = new BetterButton(title);
        betterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        betterButton.setMinimumSize(new Dimension(150, 50));
        betterButton.setMaximumSize(new Dimension(150, 50));
        return betterButton;
    }
}
