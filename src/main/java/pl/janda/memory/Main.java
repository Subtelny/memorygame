package pl.janda.memory;

import pl.janda.memory.controller.NewGameController;
import pl.janda.memory.controller.NewGameControllerImpl;
import pl.janda.memory.controller.RankingReaderController;
import pl.janda.memory.controller.RankingReaderControllerImpl;
import pl.janda.memory.model.ranking.RankingService;
import pl.janda.memory.view.menu.MenuFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        RankingService rankingService = new RankingService();
        NewGameController gameController = new NewGameControllerImpl(rankingService);
        RankingReaderController rankingReaderController = new RankingReaderControllerImpl(rankingService);

        MenuFrame menuFrame = new MenuFrame(gameController, rankingReaderController);
        SwingUtilities.invokeLater(menuFrame::launch);
    }

}
