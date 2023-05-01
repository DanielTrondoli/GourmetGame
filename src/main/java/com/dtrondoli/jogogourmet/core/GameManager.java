package com.dtrondoli.jogogourmet.core;

import com.dtrondoli.jogogourmet.factory.NodeFactory;
import com.dtrondoli.jogogourmet.model.DecisionTree;
import com.dtrondoli.jogogourmet.model.Node;

import javax.swing.*;

import static com.dtrondoli.jogogourmet.ui.DialogWindowManager.*;
import static java.lang.String.format;

public class GameManager {

    private static final String GIVE_UP_TITLE = "Desisto";
    private static final String COMPLETE_TITLE = "Complete";
    private static final String CONFIRM_TITLE = "Confirm";
    private static final String GAME_NAME_TITLE = "Jogo Gourmet";
    private static final String SUCCESS_GUESS = "Acertei %s!";
    private static final String DECISION_MSG = "O prato que você pensou é %s?";
    private static final String WHICH_FOOD_CHOSEN_MSG = "Qual prato você pensou ?";
    private static final String AGAIN_TEXT = "de novo";
    private static final String DIFFERENCE_MSG = "%s é _______ mas %s não é ?";

    private JFrame mainWindow;
    private DecisionTree decisionTree;
    private Node current;
    private int countSuccess;

    public void setup(JFrame mainWindow) {
        this.decisionTree = new DecisionTree(NodeFactory.createDefaultRootNode());
        this.mainWindow = mainWindow;
        this.countSuccess = 0;
    }

    public void start() {
        this.mainWindow.setVisible(false);
        current = decisionTree.getRoot();
        askQuestion();
    }

    public void showMainWindow() {
        this.mainWindow.setVisible(true);
    }

    public void askQuestion() {
        var msg = format(DECISION_MSG, current.getValue());
        int opt = showConfirmDialog(mainWindow, CONFIRM_TITLE, msg);

        if (opt == JOptionPane.CLOSED_OPTION) {
            showMainWindow();
            return;
        }

        if (current.isLeaf()) {
            guessedFood(opt);
        } else {
            makeDecision(opt);
            askQuestion();
        }
    }

    private void makeDecision(int opt) {
        if (opt == JOptionPane.YES_OPTION) {
            current = current.getYes();
        } else {
            current = current.getNo();
        }
    }

    private void guessedFood(int opt) {
        if (opt == JOptionPane.YES_OPTION) {
            var again = countSuccess > 0 ? AGAIN_TEXT : "";
            countSuccess++;

            var msg = format(SUCCESS_GUESS, again);
            showMessageDialog(mainWindow, GAME_NAME_TITLE, msg);
        } else {
            incrementDecisionTree();
        }
        showMainWindow();
    }

    private void incrementDecisionTree() {
        String chosenFood = askChosenFood();
        if (chosenFood == null) {
            return;
        }

        String difference = askDifference(chosenFood);
        if (difference == null) {
            return;
        }

        decisionTree.addNewDecision(current, difference, chosenFood);
    }

    private String askChosenFood() {
        return inputDialog(GIVE_UP_TITLE, WHICH_FOOD_CHOSEN_MSG);
    }

    private String askDifference(String foodName) {
        var msg = format(DIFFERENCE_MSG, foodName, current.getValue());
        return inputDialog(COMPLETE_TITLE, msg);
    }

    private String inputDialog(String title, String msg) {
        String input = "";
        while (input.isBlank()) {
            input = showInputDialog(mainWindow, title, msg);
            if (input == null) {
                return null;
            }
        }
        return input;
    }
}
