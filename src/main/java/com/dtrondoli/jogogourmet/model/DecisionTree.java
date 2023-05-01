package com.dtrondoli.jogogourmet.model;

import static com.dtrondoli.jogogourmet.factory.NodeFactory.createLeafNode;
import static com.dtrondoli.jogogourmet.factory.NodeFactory.createMiddleNode;

public class DecisionTree {

    private final Node root;

    public DecisionTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void addNewDecision(Node current, String decisionValue, String value) {

        var parent = current.getParent();
        var resultNode = createLeafNode(value, null);
        var decisionNode = createMiddleNode(decisionValue, parent, resultNode, current);

        resultNode.setParent(decisionNode);
        current.setParent(decisionNode);

        if (parent.getYes().equals(current)) {
            parent.setYes(decisionNode);
        } else {
            parent.setNo(decisionNode);
        }
    }
}

