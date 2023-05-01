package com.dtrondoli.jogogourmet.model;

import com.dtrondoli.jogogourmet.factory.NodeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DecisionTreeTest {

    DecisionTree decisionTree;

    @BeforeEach
    void setup(){
        decisionTree = new DecisionTree(NodeFactory.createDefaultRootNode());
    }

    /*      Tree Structure
                Massa
               n / \ y
             Bolo  Lasanha
     */
    @Test
    @DisplayName("Verify the default tree structure")
    void testDefaultValues(){
        Node current = decisionTree.getRoot();
        assertEquals("Massa", current.getValue());
        assertEquals("Lasanha", current.getYes().getValue());
        assertEquals("Bolo de Chocolate", current.getNo().getValue());
        assertFalse(current.isLeaf());
        assertTrue(current.getYes().isLeaf());
        assertTrue(current.getNo().isLeaf());
    }

    /*      Tree Structure
                Massa
               n /  \ y
           da vaca   Lasanha
             /  \
          Bolo  Leite
     */
    @Test
    @DisplayName("Verify the insertion on tree first level in no path")
    void testAddNewDecisionInFirstLevelNoOption(){
        Node current = decisionTree.getRoot();
        current = current.getNo();
        decisionTree.addNewDecision(current,"da vaca", "Leite");

        current = decisionTree.getRoot();
        current = current.getNo();

        assertEquals("da vaca", current.getValue());
        assertEquals("Leite", current.getYes().getValue());
        assertEquals("Bolo de Chocolate", current.getNo().getValue());
        assertEquals("Massa", current.getParent().getValue());
        assertFalse(current.isLeaf());
        assertTrue(current.getYes().isLeaf());
        assertTrue(current.getNo().isLeaf());
    }

    /*      Tree Structure
                Massa
               n /  \ y
              Bolo  macarrao
                      /  \
                Lasanha  Espaguete
     */
    @Test
    @DisplayName("Verify the insertion on tree first level in yes path")
    void testAddNewDecisionInFirstLevelYesOption(){
        Node current = decisionTree.getRoot();
        current = current.getYes();
        decisionTree.addNewDecision(current,"macarrao", "Espaguete");

        current = decisionTree.getRoot();
        current = current.getYes();

        assertEquals("macarrao", current.getValue());
        assertEquals("Espaguete", current.getYes().getValue());
        assertEquals("Lasanha", current.getNo().getValue());
        assertEquals("Massa", current.getParent().getValue());
        assertFalse(current.isLeaf());
        assertTrue(current.getYes().isLeaf());
        assertTrue(current.getNo().isLeaf());
    }

    /*      Tree Structure
                Massa
               n /  \ y
           da vaca   Lasanha
             /  \
         bolo   marrom
                 /  \
             Leite  doce de leite
     */
    @Test
    @DisplayName("Verify the insertion on tree second level in yes path")
    void testAddNewDecisionInSecondLevelYesOption(){
        Node current = decisionTree.getRoot();
        current = current.getNo();
        decisionTree.addNewDecision(current,"da vaca", "Leite");
        current = decisionTree.getRoot();
        current = current.getNo();
        current = current.getYes();
        decisionTree.addNewDecision(current,"marrom", "Doce de Leite");

        current = decisionTree.getRoot();
        current = current.getNo();
        current = current.getYes();

        assertEquals("marrom", current.getValue());
        assertEquals("Doce de Leite", current.getYes().getValue());
        assertEquals("Leite", current.getNo().getValue());
        assertEquals("da vaca", current.getParent().getValue());
        assertFalse(current.isLeaf());
        assertTrue(current.getYes().isLeaf());
        assertTrue(current.getNo().isLeaf());
    }

    /*      Tree Structure
                Massa
               n /  \ y
              Bolo  macarrao
                      /  \
                 redondo Espaguete
                   / \
             Lasanha Pizza
     */
    @Test
    @DisplayName("Verify the insertion on tree second level in no path")
    void testAddNewDecisionInSecondLevelNoOption(){
        Node current = decisionTree.getRoot();
        current = current.getYes();
        decisionTree.addNewDecision(current,"macarrao", "Espaguete");

        current = decisionTree.getRoot();
        current = current.getYes();
        current = current.getNo();
        decisionTree.addNewDecision(current,"redondo", "Pizza");

        current = decisionTree.getRoot();
        current = current.getYes();
        current = current.getNo();

        assertEquals("redondo", current.getValue());
        assertEquals("Pizza", current.getYes().getValue());
        assertEquals("Lasanha", current.getNo().getValue());
        assertEquals("macarrao", current.getParent().getValue());
        assertFalse(current.isLeaf());
        assertTrue(current.getYes().isLeaf());
        assertTrue(current.getNo().isLeaf());
    }
}
