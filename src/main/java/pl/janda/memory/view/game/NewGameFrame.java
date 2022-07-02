package pl.janda.memory.view.game;

import pl.janda.memory.DialogUtil;
import pl.janda.memory.controller.GameController;
import pl.janda.memory.controller.NewGameController;
import pl.janda.memory.model.service.GameCreationException;
import pl.janda.memory.view.menu.MenuFrame;

import javax.swing.*;
import java.awt.*;

public class NewGameFrame extends JFrame {

    private static final String TITLE = "New Game";

    private static final String PLAY = "Play";

    private static final String SELECT_GRID_SIZE = "Select grid size";

    private final MenuFrame menuFrame;

    private final NewGameController newGameController;

    public NewGameFrame(MenuFrame menuFrame, NewGameController newGameController) throws HeadlessException {
        super(TITLE);
        this.menuFrame = menuFrame;
        this.newGameController = newGameController;
        setup();
    }

    public void launch() {
        pack();
        setLocationRelativeTo(menuFrame);
        setVisible(true);
    }

    private void setup() {
        setPreferredSize(new Dimension(250, 200));
        setLayout(new GridLayout(2, 1));

        JPanel select = new JPanel();
        JSpinner spinner = getSpinner();
        select.add(Box.createVerticalStrut(70));
        select.add(getSelectLabel());
        select.add(spinner);

        add(select);
        addPlayButton(spinner);
    }

    private void addPlayButton(JSpinner spinner) {
        JButton button = new JButton(PLAY);
        button.addActionListener(e -> {
            if (validateValue(spinner)) {
                startNewGame((int) spinner.getValue());
            }
        });
        add(button);
    }

    private void startNewGame(int gridSize) {
        try {
            GameController gameController = newGameController.startGame(gridSize);
            new GameFrame(gameController, menuFrame).launch();
            dispose();
        } catch (GameCreationException e) {
            DialogUtil.dialog(menuFrame, e.getMessage());
        }
    }

    private JLabel getSelectLabel() {
        JLabel label = new JLabel(SELECT_GRID_SIZE);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        return label;
    }

    private JSpinner getSpinner() {
        JSpinner jSpinner = new JSpinner();
        jSpinner.setValue(3);
        jSpinner.setPreferredSize(new Dimension(70, 30));
        return jSpinner;
    }

    private boolean validateValue(JSpinner spinner) {
        Object value = spinner.getValue();
        if (!(value instanceof Integer)) {
            DialogUtil.dialog(this, "Value " + value + " is not numeric!");
            return false;
        }
        return true;
    }

}
