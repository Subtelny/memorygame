package pl.janda.memory.view.game;

import pl.janda.memory.DialogUtil;
import pl.janda.memory.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class ScoreFrame extends JFrame {

    private static final String TITLE = "Save Score";

    private final GameController gameController;

    private final GameFrame gameFrame;

    public ScoreFrame(GameController gameController, GameFrame gameFrame) throws HeadlessException {
        super(TITLE);
        this.gameController = gameController;
        this.gameFrame = gameFrame;
        setup();
    }

    public void launch() {
        pack();
        setLocationRelativeTo(gameFrame);
        setVisible(true);
    }

    private void setup() {
        setLayout(new GridLayout(2, 1));
        setPreferredSize(new Dimension(400, 150));
        add(getScore());
        add(getSave());
    }

    private Component getScore() {
        JPanel jPanel = new JPanel();
        jPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel score = new JLabel();
        score.setText("Wynik: " + gameController.getScore());
        score.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        jPanel.add(score);
        return jPanel;
    }

    private JPanel getSave() {
        JPanel jPanel = new JPanel();
        JTextField field = new JTextField("Player 1");
        field.setPreferredSize(new Dimension(50, 20));
        JButton button = new JButton("Save");
        button.addActionListener(e -> {
            try {
                gameController.saveScore(field.getText());
                gameFrame.dispose();
                dispose();
            } catch (IllegalStateException ex) {
                DialogUtil.dialog(gameFrame, ex.getMessage());
            }
        });
        jPanel.add(field);
        jPanel.add(button);
        return jPanel;
    }


}
