package com.dtrondoli.jogogourmet.ui;

import com.dtrondoli.jogogourmet.core.GameManager;

import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

public class MainWindow extends JFrame {

    private static final String ALIGN_CENTER = "push, align center";
    public static final String MAIN_LABEL_MESSAGE = "Pense em um prato que goste !!";
    public static final String OK_TEXT = "Ok";
    public static final int WIDTH_SIZE = 250;
    public static final int HEIGHT_SIZE = 130;

    public MainWindow(GameManager gameManager) {
        super();

        configFrame();

        this.add(createPanel(gameManager),ALIGN_CENTER);
    }

    private void configFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(
                new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(new Dimension(WIDTH_SIZE,HEIGHT_SIZE));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private JPanel createPanel(GameManager gameManager) {
        JLabel label = createLabel();
        JButton okButton = createOkButton(gameManager);

        JPanel panel = new JPanel();
        panel.setBorder(createEmptyBorder(20, 20, 20, 20));
        panel.add(label, ALIGN_CENTER);
        panel.add(okButton, ALIGN_CENTER);

        return panel;
    }

    private JButton createOkButton(GameManager gameManager) {
        JButton okButton = new JButton(OK_TEXT);
        okButton.addActionListener(e -> gameManager.start());
        return okButton;
    }

    private JLabel createLabel() {
        JLabel label = new JLabel(MAIN_LABEL_MESSAGE);
        label.setBorder(createEmptyBorder(2, 2, 5, 2));
        return label;
    }
}
