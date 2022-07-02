package pl.janda.memory.model.game;

import pl.janda.memory.model.game.card.MemoryCard;
import pl.janda.memory.model.game.card.MemoryCardPair;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemoryGame {

    private final GameTimer gameTimer;

    private final Map<Integer, MemoryCardPair> cardPairs;

    private MemoryCardPair clickedCardPair;

    public MemoryGame(List<MemoryCardPair> pairs) {
        this.gameTimer = new GameTimer();
        this.cardPairs = computeMap(pairs);
    }

    private static Map<Integer, MemoryCardPair> computeMap(List<MemoryCardPair> pairs) {
        Map<Integer, MemoryCardPair> pairMap = new HashMap<>();
        pairs.forEach(memoryCardPair -> {
            pairMap.put(memoryCardPair.getLeftSlot(), memoryCardPair);
            pairMap.put(memoryCardPair.getRightSlot(), memoryCardPair);
        });
        return pairMap;
    }

    public void setupTimer(Consumer<Integer> timerListener) {
        gameTimer.setListener(timerListener);
    }

    public ClickResult click(int slot) {
        if (isResolved()) {
            return ClickResult.ALL_RESOLVED;
        }
        checkTimerState();
        MemoryCardPair cardPair = getCardPair(slot);
        if (clickedCardPair != null && !clickedCardPair.equals(cardPair)) {
            clickedCardPair.markAsUnResolved();
            clickedCardPair = null;
            return ClickResult.WRONG_PAIR;
        }
        switch (cardPair.click(slot)) {
            case PAIR_RESOLVED -> {
                clickedCardPair = null;
                if (isResolved()) {
                    gameTimer.stop();
                }
                return pairResolvedResult();
            }
            case ONE_RESOLVED -> {
                clickedCardPair = cardPair;
                return ClickResult.FIRST_CLICK;
            }
            case ALREADY_RESOLVED -> {
                return ClickResult.SAME_CARD;
            }
            default -> {
                clickedCardPair = null;
                return ClickResult.WRONG_PAIR;
            }
        }
    }

    public Set<MemoryCard> getMemoryCards() {
        return cardPairs.values()
                .stream()
                .flatMap(memoryCardPair -> Stream.of(memoryCardPair.getLeft(), memoryCardPair.getRight()))
                .collect(Collectors.toSet());
    }

    public MemoryGameScore getScore() {
        if (gameTimer.isRunning() || !isResolved()) {
            throw new IllegalStateException("Game is still ongoing!");
        }
        return new MemoryGameScore(cardPairs.values(), gameTimer.getCounter());
    }

    public void stop() {
        gameTimer.stop();
    }

    private ClickResult pairResolvedResult() {
        if (isResolved()) {
            gameTimer.stop();
            return ClickResult.ALL_RESOLVED;
        }
        return ClickResult.PAIR_RESOLVED;
    }

    private boolean isResolved() {
        return cardPairs.values().stream()
                .allMatch(MemoryCardPair::isResolved);
    }

    private void checkTimerState() {
        if (!gameTimer.isRunning()) {
            gameTimer.start();
        }
    }

    private MemoryCardPair getCardPair(int slot) {
        return Optional.ofNullable(cardPairs.get(slot))
                .orElseThrow(() -> new IllegalStateException("Not found card at slot " + slot));
    }

}
