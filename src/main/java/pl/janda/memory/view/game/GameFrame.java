package pl.janda.memory.view.game;

import pl.janda.memory.controller.GameController;
import pl.janda.memory.view.menu.MenuFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {

    private static final String TITLE = "Game";

    private final GameController gameController;

    private final MenuFrame menuFrame;

    public GameFrame(GameController gameController, MenuFrame menuFrame) throws HeadlessException {
        super(TITLE);
        this.gameController = gameController;
        this.menuFrame = menuFrame;
        setup();
    }

    public void launch() {
        pack();
        setLocationRelativeTo(menuFrame);
        setVisible(true);
    }

    private void setup() {
        setLayout(new GridLayout(2, 1));
        add(getCardGridPanel());
        add(getTimer());
        addQuitKeyboardShortcut();
    }

    private void addQuitKeyboardShortcut() {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK);
        JRootPane rootPane1 = getRootPane();
        rootPane1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "quitToMenu");
        rootPane1.getActionMap().put("quitToMenu", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.stop();
                dispose();
            }
        });
    }

    private JPanel getCardGridPanel() {
        return new CardGridPanel(gameController, this);
    }

    private JPanel getTimer() {
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(100, 100));
        jPanel.add(Box.createVerticalStrut(250));
        jPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label = new JLabel(toReadableTime(0));
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        gameController.setupTimeListener(time -> label.setText(toReadableTime(time)));
        jPanel.add(label);
        return jPanel;
    }

    private String toReadableTime(int seconds) {
        int minutes = seconds / 60;
        int sec = seconds % 60;
        return String.format("%d:%02d", minutes, sec);
    }
}
