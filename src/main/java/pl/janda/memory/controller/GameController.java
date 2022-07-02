package pl.janda.memory.controller;

import pl.janda.memory.model.game.ClickResult;
import pl.janda.memory.view.MemoryCardInfo;

import java.util.List;
import java.util.function.Consumer;

public interface GameController {

    void setupTimeListener(Consumer<Integer> timeListener);

    void saveScore(String playerName);

    void stop();

    double getScore();

    ClickResult click(int slot);

    List<MemoryCardInfo> getMemoryCardViews();

}
