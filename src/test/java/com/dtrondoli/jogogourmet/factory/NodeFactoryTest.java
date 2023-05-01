package com.dtrondoli.jogogourmet.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodeFactoryTest {

    @Test
    void testCreateLeafNode(){
        var parent =NodeFactory.createMiddleNode("Parent", null, null, null);
        var leaf = NodeFactory.createLeafNode("Leaf", parent);

        assertEquals("Leaf", leaf.getValue());
        assertEquals("Parent", leaf.getParent().getValue());
        assertTrue(leaf.isLeaf());
        assertFalse(leaf.getParent().isLeaf());
        assertNull(leaf.getYes());
        assertNull(leaf.getNo());
    }

    @Test
    void testCreateMiddleNode(){
        var parent = NodeFactory.createMiddleNode("Parent", null, null, null);
        var leafYes = NodeFactory.createLeafNode("LeafYes", parent);
        var leafNo = NodeFactory.createLeafNode("LeafNo", parent);

        var middle =NodeFactory.createMiddleNode("Middle", parent, leafYes, leafNo);

        assertEquals("Middle", middle.getValue());
        assertEquals("Parent", middle.getParent().getValue());
        assertEquals("LeafYes", middle.getYes().getValue());
        assertEquals("LeafNo", middle.getNo().getValue());
        assertFalse(middle.isLeaf());
        assertFalse(middle.getParent().isLeaf());
        assertTrue(middle.getYes().isLeaf());
        assertTrue(middle.getNo().isLeaf());
    }
}
