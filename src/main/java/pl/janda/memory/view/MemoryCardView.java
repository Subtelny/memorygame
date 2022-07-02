package pl.janda.memory.view;

import javax.swing.*;
import java.awt.*;

public class MemoryCardView extends JButton {

    private final MemoryCardInfo cardInfo;

    public MemoryCardView(MemoryCardInfo cardInfo) {
        this.cardInfo = cardInfo;
        setup();
    }

    public int getSlot() {
        return cardInfo.slot();
    }

    private void setup() {
        hideCard();
        setBackground(Color.WHITE);
    }

    public void showCard() {
        ImageIcon img = new ImageIcon(getClass().getResource("/" + cardInfo.imageId() + ".png"));
        setIcon(img);
    }

    public void hideCard() {
        ImageIcon img = new ImageIcon(getClass().getResource("/rewers.png"));
        setIcon(img);
    }
}
