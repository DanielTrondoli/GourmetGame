package com.dtrondoli.jogogourmet.factory;

import com.dtrondoli.jogogourmet.model.Node;

public class NodeFactory {
    public static Node createLeafNode(String value, Node parent) {
        return new Node(value, parent, null, null);
    }

    public static Node createMiddleNode(String value, Node parent, Node yes, Node no) {
        return new Node(value, parent, yes, no);
    }

    public static Node createDefaultRootNode() {
        var root = createMiddleNode("Massa", null, null, null);
        var yes = createLeafNode("Lasanha", root);
        var no = createLeafNode("Bolo de Chocolate", root);

        root.setYes(yes);
        root.setNo(no);
        return root;
    }
}
