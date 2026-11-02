package edu.kirkwood.model.leesonj;

import edu.kirkwood.model.Fraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiePoolTest {
    DiePool pool1;
    DiePool pool2;

    @BeforeEach
    void setUp() {
        pool1 = new DiePool(); // 1d6
        pool2 = new DiePool(3, 10);
    }

    @Test
    void getDice() {
        // Act and Assert
        assertEquals(1, pool1.getDice());
        assertEquals(3, pool2.getDice());
    }

    @Test
    void setDice() {
        // Act
        pool1.setDice(2);
        // Assert
        assertEquals(2, pool1.getDice());
        // Act
        pool2.setDice(100);
        // Assert
        assertEquals(100, pool2.getDice());
    }

    @Test
    void setDiceZeroOrNegative() {
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> pool1.setDice(-1));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> pool2.setDice(0));
    }

    @Test
    void getSides() {
        // Act and Assert
        assertEquals(6, pool1.getSides());
        assertEquals(10, pool2.getSides());
    }

    @Test
    void setSides() {
        // Act
        pool1.setSides(8);
        // Assert
        assertEquals(8, pool1.getSides());
        // Act
        pool2.setSides(12);
        // Assert
        assertEquals(12, pool2.getSides());
    }

    @Test
    void setSidesZeroOrNegative() {
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> pool1.setSides(-1));
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> pool1.setSides(0));
    }

    @Test
    void nextCombinationOneDie() {
        // Assemble
        int[] start = {1};
        int[] next = {2};
        // Act and Assert
        assertArrayEquals(next, pool1.nextCombination(start));
    }

    @Test
    void nextCombinationMultipleDice() {
        // Assemble
        int[] start = {1, 1, 1};
        int[] next = {1, 1, 2};
        // Act and Assert
        assertArrayEquals(next, pool2.nextCombination(start));

        // Assemble
        start = new int[] {1, 10, 10};
        next = new int[] {2, 1, 1};
        // Act and Assert
        assertArrayEquals(next, pool2.nextCombination(start));
    }

    @Test
    void nextCombinationDiePoolOverflow() {
        // Act and Assert
        assertNull (pool1.nextCombination(new int[] {6}));

        // Act and Assert
        assertNull(pool2.nextCombination(new int[] {10, 10, 10}));
    }

    @Test
    void testTarget() {
        // Assemble
        int[] combination = {4, 5, 6};
        // Act and Assert
        assertTrue(pool1.testTarget(combination, 15));
        assertFalse(pool1.testTarget(combination, 11));
    }

    @Test
    void calculateTargetOddsSingleDie() {
        // Act and Assert
        assertEquals(0.16667, pool1.calculateTargetOdds(1), 0.00001);

        // Assemble
        pool1.setSides(8);
        // Act and Assert
        assertEquals(0.125, pool1.calculateTargetOdds(7), 0.00001);
    }

    @Test
    void calculateTargetOddsMultipleDice() {
        // Assemble
        pool1.setDice(2);
        // Act and Assert
        assertEquals(0.02778, pool1.calculateTargetOdds(2), 0.00001);
        assertEquals(0.16667, pool1.calculateTargetOdds(7), 0.00001);
    }

    @Test
    void calculateTargetOddsOutOfRange() {
        // Assemble
        pool1.setDice(3);
        // Act and Assert
        assertEquals(0.0, pool1.calculateTargetOdds(2), 0.00001);
        assertEquals(0.0, pool1.calculateTargetOdds(19), 0.00001);
    }

    @Test
    void evaluateTargetOddsSingleDie() {
        // Act and Assert
        assertEquals(new Fraction(1,6), pool1.evaluateTargetOdds(1));

        // Assemble
        pool1.setSides(8);
        // Act and Assert
        assertEquals(new Fraction(1, 8), pool1.evaluateTargetOdds(7));
    }

    @Test
    void evaluateTargetOddsMultipleDice() {
        // Assemble
        pool1.setDice(2);
        // Act and Assert
        assertEquals(new Fraction(1,36), pool1.evaluateTargetOdds(2));
        assertEquals(new Fraction(6, 36), pool1.evaluateTargetOdds(7));
    }

    @Test
    void evaluateTargetOddsOutOfRange() {
        // Act and Assert
        assertEquals(new Fraction(0, 1), pool1.evaluateTargetOdds(-1));
        assertEquals(new Fraction(0, 1), pool1.evaluateTargetOdds(0));
        assertEquals(new Fraction(0, 1), pool1.evaluateTargetOdds(7));

        // Act and Assert
        assertEquals(new Fraction(0, 1), pool2.evaluateTargetOdds(2));
        assertEquals(new Fraction(0, 1), pool2.evaluateTargetOdds(31));
    }

    @Test
    void simulateTargetOdds() {
        // Assemble
        pool1.setSides(1);
        // Act and Assert
        assertEquals(new Fraction(100,100), pool1.simulateTargetOdds(1, 100));

        //Assemble
        pool1.setDice(4);
        pool1.setSides(12);
        long seed = 12345;

        // Act and Assert
        assertEquals(new Fraction(16, 10000), pool1.simulateTargetOdds(7, 10000, seed));
    }

    // Note:  Does dice ^ sides exceed Integer.MAX_VALUE  ( Math.pow(Integer.MAX_VALUE, 1/sides) < dice )
    // Note:  Also need to take into account practical limitations for looping since calculation and evaluation both loop over an amount.
    //          A large number of dice can result in too long of a wait for processing.  Need to review this for veracity.
    @Test
    @DisplayName("Evaluate target odds that exceed the capability of the program")
    void evaluateDicePoolTooLarge() {
        // Assemble
        DiePool pool1 = new DiePool(15, 20); // 20 ^ 15 > Long.MAX_VALUE
        DiePool pool2 = new DiePool(4, 100); // 100 million combinations is more than I want to evaluate

        // Act and Assert
        assertThrows(ArithmeticException.class, () -> pool1.evaluateTargetOdds(20));
        assertThrows(ArithmeticException.class, () -> pool2.evaluateTargetOdds((21)));

    }

    @Test
    @DisplayName(("Simulate too many results ( > 100,000,000)"))
    void simulateTooManyResults() {
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> pool1.simulateTargetOdds(1, 100000001));
    }

}