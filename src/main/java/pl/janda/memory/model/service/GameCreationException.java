package pl.janda.memory.model.service;

public class GameCreationException extends RuntimeException {

    private GameCreationException(String message) {
        super(message);
    }

    public static GameCreationException of(String message) {
        return new GameCreationException(message);
    }
}
