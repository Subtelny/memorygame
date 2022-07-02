package pl.janda.memory.model.game.card;

import java.util.Objects;

public class MemoryCardPair {

    private final MemoryCard left;

    private final MemoryCard right;

    private boolean firstTryResolved = true;

    public MemoryCardPair(MemoryCard left, MemoryCard right) {
        this.left = left;
        this.right = right;
    }

    public PairClickResult click(int slot) {
        if (left.getSlot() == slot) {
            return resolveCard(left, right);
        }
        if (right.getSlot() == slot) {
            return resolveCard(right, left);
        }
        firstTryResolved = false;
        markAsUnResolved();
        return PairClickResult.NOT_RESOLVED;
    }

    public MemoryCard getLeft() {
        return left;
    }

    public MemoryCard getRight() {
        return right;
    }

    public int getLeftSlot() {
        return left.getSlot();
    }

    public int getRightSlot() {
        return right.getSlot();
    }

    public void markAsUnResolved() {
        left.markAsUnResolved();
        right.markAsUnResolved();
    }

    public boolean isResolved() {
        return left.isResolved() && right.isResolved();
    }

    public boolean isFirstTryResolved() {
        return firstTryResolved;
    }

    private PairClickResult resolveCard(MemoryCard first, MemoryCard second) {
        if (first.isResolved()) {
            return PairClickResult.ALREADY_RESOLVED;
        }
        first.markAsResolved();
        return second.isResolved() ? PairClickResult.PAIR_RESOLVED : PairClickResult.ONE_RESOLVED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryCardPair)) return false;
        MemoryCardPair that = (MemoryCardPair) o;
        return Objects.equals(left, that.left) && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
