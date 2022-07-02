package pl.janda.memory.model.game;

import pl.janda.memory.model.game.card.MemoryCardPair;

import java.util.Collection;

public class MemoryGameScore {

    private static final long POINTS_FIRST_TRY_RESOLVED_PAIR = 1;

    private final int gridSize;

    private final int time;

    private final long firstTryResolvedPairsPoints;

    public MemoryGameScore(Collection<MemoryCardPair> pairs, int time) {
        this.gridSize = pairs.size();
        this.time = time;
        this.firstTryResolvedPairsPoints = pairs.stream()
                .filter(MemoryCardPair::isFirstTryResolved)
                .count() * POINTS_FIRST_TRY_RESOLVED_PAIR;
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getTime() {
        return time;
    }

    public double getScore() {
        double scale = Math.pow(10, 2);
        double score = ((double) gridSize * gridSize / time) + firstTryResolvedPairsPoints;
        return Math.round(score * scale) / scale;
    }
}
