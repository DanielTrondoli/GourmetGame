package com.dtrondoli.jogogourmet.model;

public class Node {

    private final String value;
    private Node parent;
    private Node yes;
    private Node no;

    public Node(String value, Node parent, Node yes, Node no) {
        this.value = value;
        this.parent = parent;
        this.yes = yes;
        this.no = no;
    }

    public String getValue() {
        return value;
    }

    public Node getYes() {
        return yes;
    }

    public void setYes(Node yes) {
        this.yes = yes;
    }

    public Node getNo() {
        return no;
    }

    public void setNo(Node no) {
        this.no = no;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isLeaf() {
        return this.yes == null && this.no == null && this.parent != null;
    }
}
