package pl.janda.memory.model.service;

import pl.janda.memory.model.game.MemoryGame;
import pl.janda.memory.model.game.card.MemoryCard;
import pl.janda.memory.model.game.card.MemoryCardPair;

import java.util.ArrayList;
import java.util.List;

public class GameCreator {

    public static MemoryGame create(int gridSize) {
        if (gridSize < 2) {
            throw GameCreationException.of("Grid size cannot be less than 2");
        }
        if (gridSize > 24) {
            throw GameCreationException.of("Grid size cannot be greater than 24");
        }
        List<MemoryCardPair> cardPairs = new ArrayList<>();
        int lastSlot = 0;
        for (int i = 0; i < gridSize; i++) {
            MemoryCard left = new MemoryCard(lastSlot++, i);
            MemoryCard right = new MemoryCard(lastSlot++, i);
            cardPairs.add(new MemoryCardPair(left, right));
        }
        return new MemoryGame(cardPairs);
    }

}
