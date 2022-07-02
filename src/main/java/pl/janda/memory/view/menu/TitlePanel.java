package pl.janda.memory.view.menu;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {

    private static final String TITLE = "Memory The Game";

    private static final String STUDENT_CODE = "S24422";

    public TitlePanel() {
        setup();
    }

    private void setup() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addTitleLabel();
        addStudentCodeLabel();
    }

    private void addStudentCodeLabel() {
        add(center(new JLabel(STUDENT_CODE)));
    }

    private void addTitleLabel() {
        JLabel title = new JLabel(TITLE);
        title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        add(center(title));
    }

    private <T extends JComponent> T center(T jComponent) {
        jComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
        return jComponent;
    }

}
