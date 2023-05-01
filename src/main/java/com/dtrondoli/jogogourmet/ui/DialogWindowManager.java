package com.dtrondoli.jogogourmet.ui;

import javax.swing.*;
import java.awt.*;

public class DialogWindowManager {

    public static int showConfirmDialog(Component parent, String title, String message) {
        return JOptionPane.showConfirmDialog(
                parent,
                message,
                title,
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static String showInputDialog(Component parent, String title, String message) {
        return JOptionPane.showInputDialog(
                parent,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE
        );
    }

    public static void showMessageDialog(Component parent, String title, String message) {
        JOptionPane.showMessageDialog(
                parent,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
