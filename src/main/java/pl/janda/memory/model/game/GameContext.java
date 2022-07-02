package pl.janda.memory.model.game;

import java.util.Optional;

public class GameContext {

    private MemoryGame ongoingGame;

    public Optional<MemoryGame> getContext() {
        return Optional.ofNullable(ongoingGame);
    }

    public void clearContext() {
        ongoingGame = null;
    }

    public void setContext(MemoryGame game) {
        ongoingGame = game;
    }

}
