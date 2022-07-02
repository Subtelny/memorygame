package pl.janda.memory;

import javax.swing.*;
import java.awt.*;

public final class DialogUtil {

    public static void dialog(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message);
    }

}
