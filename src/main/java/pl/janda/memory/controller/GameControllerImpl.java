package pl.janda.memory.controller;

import pl.janda.memory.model.game.ClickResult;
import pl.janda.memory.model.game.MemoryGame;
import pl.janda.memory.model.game.MemoryGameScore;
import pl.janda.memory.model.ranking.RankingRecord;
import pl.janda.memory.model.ranking.RankingService;
import pl.janda.memory.view.MemoryCardInfo;

import java.util.List;
import java.util.function.Consumer;

public class GameControllerImpl implements GameController {

    private final MemoryGame memoryGame;

    private final RankingService rankingService;

    public GameControllerImpl(MemoryGame memoryGame, RankingService rankingService) {
        this.memoryGame = memoryGame;
        this.rankingService = rankingService;
    }

    @Override
    public void setupTimeListener(Consumer<Integer> timeListener) {
        memoryGame.setupTimer(timeListener);
    }

    @Override
    public void saveScore(String playerName) {
        if (playerName == null || playerName.isBlank() || playerName.isBlank()) {
            throw new IllegalStateException("Player name cannot be null");
        }
        MemoryGameScore score = memoryGame.getScore();
        RankingRecord record = new RankingRecord(playerName, score.getGridSize(), score.getTime(), score.getScore());
        rankingService.saveRankingRecord(record);
    }

    @Override
    public void stop() {
        memoryGame.stop();
    }

    @Override
    public double getScore() {
        return memoryGame.getScore().getScore();
    }

    @Override
    public ClickResult click(int slot) {
        return memoryGame.click(slot);
    }

    @Override
    public List<MemoryCardInfo> getMemoryCardViews() {
        return memoryGame.getMemoryCards().stream()
                .map(memoryCard -> new MemoryCardInfo(memoryCard.getSlot(), memoryCard.getImageId()))
                .toList();
    }
}
