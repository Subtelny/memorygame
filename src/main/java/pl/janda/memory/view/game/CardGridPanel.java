package pl.janda.memory.view.game;

import pl.janda.memory.controller.GameController;
import pl.janda.memory.model.game.ClickResult;
import pl.janda.memory.view.MemoryCardInfo;
import pl.janda.memory.view.MemoryCardView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CardGridPanel extends JPanel {

    private final GameController gameController;

    private final GameFrame gameFrame;

    private MemoryCardView previousClicked;

    private boolean clickAble = true;

    public CardGridPanel(GameController gameController, GameFrame gameFrame) {
        this.gameController = gameController;
        this.gameFrame = gameFrame;
        setup();
    }

    private void setup() {
        List<MemoryCardInfo> views = gameController.getMemoryCardViews();
        setLayout(new GridLayout(2, 2));

        List<MemoryCardInfo> shuffledList = new ArrayList<>(views);
        Collections.shuffle(shuffledList);
        shuffledList.forEach(this::addCard);
    }

    private void addCard(MemoryCardInfo memoryCardView) {
        MemoryCardView view = new MemoryCardView(memoryCardView);
        view.addActionListener(e -> click(view));
        add(view);
    }

    private void click(MemoryCardView view) {
        if (!clickAble) {
            return;
        }
        ClickResult clickResult = gameController.click(view.getSlot());
        view.showCard();
        switch (clickResult) {
            case FIRST_CLICK -> previousClicked = view;
            case PAIR_RESOLVED -> previousClicked = null;
            case WRONG_PAIR -> {
                clickAble = false;
                hideAfterTime(view, previousClicked);
                previousClicked = null;
            }
            case ALL_RESOLVED -> endGame();
        }
    }

    private void endGame() {
        ScoreFrame scoreFrame = new ScoreFrame(gameController, gameFrame);
        scoreFrame.launch();
    }

    private void hideAfterTime(MemoryCardView first, MemoryCardView second) {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(() -> {
            first.hideCard();
            second.hideCard();
            clickAble = true;
        }, 2, TimeUnit.SECONDS);
    }

}
