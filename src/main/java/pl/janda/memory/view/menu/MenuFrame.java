package pl.janda.memory.view.menu;

import pl.janda.memory.controller.NewGameController;
import pl.janda.memory.controller.RankingReaderController;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {

    private static final String TITLE = "Memory The Game";

    private final NewGameController gameController;

    private final RankingReaderController rankingReaderController;

    public MenuFrame(NewGameController gameController, RankingReaderController rankingReaderController) throws HeadlessException {
        super(TITLE);
        this.gameController = gameController;
        this.rankingReaderController = rankingReaderController;
        setup();
    }

    public void launch() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setup() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        addSpace();
        addTitlePanel();
        addSpace();
        addNavigationPanel();

    }

    private void addNavigationPanel() {
        add(new NavigationPanel(this, gameController, rankingReaderController));
    }

    private void addTitlePanel() {
        add(new TitlePanel());
    }

    private void addSpace() {
        add(Box.createVerticalStrut(20));
    }

}
