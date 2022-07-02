package pl.janda.memory.view.menu;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BetterButton extends JButton {

    private static final int RADIUS = 13;

    public BetterButton(String text) {
        super(text);
        setupStyle();
    }

    private void setupStyle() {
        setBorder(new Border() {

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.drawRoundRect(x, y, width - 1, height - 1, RADIUS, RADIUS);
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(RADIUS + 1, RADIUS + 1, RADIUS + 2, RADIUS);
            }

            @Override
            public boolean isBorderOpaque() {
                return true;
            }
        });
    }

}
