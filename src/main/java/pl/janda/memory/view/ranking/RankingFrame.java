package pl.janda.memory.view.ranking;

import pl.janda.memory.controller.RankingReaderController;
import pl.janda.memory.view.RankingRecordView;
import pl.janda.memory.view.menu.MenuFrame;

import javax.swing.*;
import java.awt.*;

public class RankingFrame extends JFrame {

    private static final String[] COLUMN = new String[]{"Name", "Time", "Grid", "Score"};

    private static final String TITLE = "High Scores";

    private final RankingReaderController rankingReaderController;

    private final MenuFrame menuFrame;

    public RankingFrame(MenuFrame menuFrame, RankingReaderController rankingReaderController) throws HeadlessException {
        super(TITLE);
        this.menuFrame = menuFrame;
        this.rankingReaderController = rankingReaderController;
        setup();
    }

    public void launch() {
        pack();
        setLocationRelativeTo(menuFrame);
        setVisible(true);
    }

    private void setup() {
        setPreferredSize(new Dimension(500, 500));

        String[][] data = rankingReaderController.getRecords().stream()
                .map(RankingRecordView::toArr)
                .toArray(String[][]::new);

        JTable jTable = new JTable(data, COLUMN);
        jTable.setBounds(30, 40, 200, 300);

        JScrollPane scrollPane = new JScrollPane(jTable);
        setPreferredSize(new Dimension(300, 400));
        add(scrollPane);
    }

}
