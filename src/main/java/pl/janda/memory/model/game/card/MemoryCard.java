package pl.janda.memory.model.game.card;

import java.util.Objects;

public class MemoryCard {

    private final int slot;

    private final int imageId;

    private boolean resolved;

    public MemoryCard(int slot, int imageId) {
        this.slot = slot;
        this.imageId = imageId;
    }

    public int getSlot() {
        return slot;
    }

    public int getImageId() {
        return imageId;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void markAsUnResolved() {
        resolved = false;
    }

    public void markAsResolved() {
        resolved = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryCard)) return false;
        MemoryCard that = (MemoryCard) o;
        return slot == that.slot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(slot);
    }
}
