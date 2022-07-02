package pl.janda.memory.controller;

import pl.janda.memory.model.game.MemoryGame;
import pl.janda.memory.model.ranking.RankingService;
import pl.janda.memory.model.service.GameCreator;

public class NewGameControllerImpl implements NewGameController {

    private final RankingService rankingService;

    public NewGameControllerImpl(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @Override
    public GameController startGame(int gridSize) {
        MemoryGame memoryGame = GameCreator.create(gridSize);
        return new GameControllerImpl(memoryGame, rankingService);
    }

}
