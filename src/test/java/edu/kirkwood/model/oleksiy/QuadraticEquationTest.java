package edu.kirkwood.model.oleksiy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QuadraticEquationTest {

    private QuadraticEquation quadEqua1;
    private QuadraticEquation quadEqua2;

    @BeforeEach
    void setUp() {
        quadEqua1 = new QuadraticEquation(); // x^2+x+1
        quadEqua2 = new QuadraticEquation(3, 2, 4); // 3x^2+2x+4
    }

    @Test
    void getSectionA() {
        // Assert
        assertEquals(1, quadEqua1.getSectionA());
        assertEquals(3, quadEqua2.getSectionA());
    }

    @Test
    void setPositiveSectionA() {
        // Act
        quadEqua1.setSectionA(8);
        quadEqua2.setSectionA(2198710);

        // Assert
        assertEquals(8, quadEqua1.getSectionA());
        assertEquals(2198710, quadEqua2.getSectionA());
    }

    @Test
    void setNegativeSectionA() {
        // Act
        quadEqua1.setSectionA(-8);
        quadEqua2.setSectionA(-2198710);

        // Assert
        assertEquals(-8, quadEqua1.getSectionA());
        assertEquals(-2198710, quadEqua2.getSectionA());
    }

    @Test
    void setSectionAToDecimal() {
        // Act
        quadEqua1.setSectionA(0.8);
        quadEqua2.setSectionA(0.2198710);

        // Assert
        assertEquals(0.8, quadEqua1.getSectionA());
        assertEquals(0.2198710, quadEqua2.getSectionA());
    }

   @Test
   void setSectionAEqualZero() { // Making section A, 0, would turn the equation into a linear equation instead of a quadratic one.
        // Arrange
       String expectedMessage = "You cannot have section A as a zero. This will make the equation linear, instead of quadratic.";

       // Act & Assert
       IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> quadEqua1.setSectionA(0));

       // Act
       String actualMessage = exception.getMessage();

       // Assert
       assertEquals(expectedMessage, actualMessage);
   }

    @Test
    void getSectionB(){
        // Assert
        assertEquals(1, quadEqua1.getSectionB());
        assertEquals(2, quadEqua2.getSectionB());
    }

    @Test
    void setPositiveSectionB() {
        // Act
        quadEqua1.setSectionB(7);
        quadEqua2.setSectionB(4872910);

        // Assert
        assertEquals(7, quadEqua1.getSectionB());
        assertEquals(4872910, quadEqua2.getSectionB());
    }

    @Test
    void setNegativeSectionB() {
        // Act
        quadEqua1.setSectionB(-7);
        quadEqua2.setSectionB(-4872910);

        // Assert
        assertEquals(-7, quadEqua1.getSectionB());
        assertEquals(-4872910, quadEqua2.getSectionB());
    }

    @Test
    void setSectionBToZero() {
        // Act
        quadEqua1.setSectionB(0);

        // Assert
        assertEquals(0, quadEqua1.getSectionB());
    }

    @Test
    void setSectionBToDecimal() {
        // Act
        quadEqua1.setSectionB(0.7);
        quadEqua2.setSectionB(0.4872910);

        // Assert
        assertEquals(0.7, quadEqua1.getSectionB());
        assertEquals(0.4872910, quadEqua2.getSectionB());
    }

    @Test
    void getSectionC() {
        // Assert
        assertEquals(1, quadEqua1.getSectionC());
        assertEquals(4, quadEqua2.getSectionC());
    }

    @Test
    void setPositiveSectionC() {
        // Act
        quadEqua1.setSectionC(21);
        quadEqua2.setSectionC(381085016);

        // Assert
        assertEquals(21, quadEqua1.getSectionC());
        assertEquals(381085016, quadEqua2.getSectionC());
    }

    @Test
    void setNegativeSectionC() {
        // Act
        quadEqua1.setSectionC(-21);
        quadEqua2.setSectionC(-381085016);

        // Assert
        assertEquals(-21, quadEqua1.getSectionC());
        assertEquals(-381085016, quadEqua2.getSectionC());
    }

    @Test
    void setSectionCToZero() {
        // Act
        quadEqua1.setSectionC(0);

        // Assert
        assertEquals(0, quadEqua1.getSectionC());
    }

    @Test
    void setSectionCToDecimal() {
        // Arrange
        quadEqua1.setSectionC(0.21);
        quadEqua2.setSectionC(0.381085016);

        // Assert
        assertEquals(0.21, quadEqua1.getSectionC());
        assertEquals(0.381085016, quadEqua2.getSectionC());
    }

    @Test
    void convertDecimalWholeToWholeNum(){
        // Arrange
        quadEqua2.setSectionA(1.0);

        // Act
        String expected = "1";
        String actual = QuadraticEquation.convertWholeDecimalsToWholeNumbers(String.valueOf(quadEqua2.getSectionA()));

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void convertVeryLongDecimalWholeToWholeNum(){
        // Arrange
        quadEqua2.setSectionA(3144611.0); // we will get an error if we have a num longer than this due to how double works. I really REALLY wish I didn't choose double to center this project around.

        // Act
        String expected = "3144611";
        String actual = QuadraticEquation.convertWholeDecimalsToWholeNumbers(String.valueOf(quadEqua2.getSectionA()));

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void ignoreActualDecimalWhenTryingToConvertToWholeNum(){
        // Arrange
        quadEqua2.setSectionA(0.45);

        // Act
        String expected = "0.45";
        String actual = QuadraticEquation.convertWholeDecimalsToWholeNumbers(String.valueOf(quadEqua2.getSectionA()));

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void convertZeroPointZeroToWholeZero() {
        // Arrange
        quadEqua2.setSectionB(0.0);

        // Act
        String expected = "0";
        String actual = QuadraticEquation.convertWholeDecimalsToWholeNumbers(String.valueOf(quadEqua2.getSectionB()));

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void convertNegativeDecimalWholeNumToNegativeWholeNum() {
        // Arrange
        quadEqua2.setSectionA(-4.0);

        // Act
        String expected = "-4";
        String actual = QuadraticEquation.convertWholeDecimalsToWholeNumbers(String.valueOf(quadEqua2.getSectionA()));

        // Assert
        assertEquals(expected, actual);
    }

    // This is the same thing as getting section C of the quadratic equation.
    @Test
    void findYIntercept() {
        // Assert
        assertEquals("(0, 1)", quadEqua1.findYIntercept());
    }

    @Test
    void setDecimalToFraction(){
        // Arrange
        quadEqua2.setSectionA(0.75);

        // Act
        String actual = QuadraticEquation.decimalToFraction(quadEqua2.getSectionA());

        // Assert
        assertEquals("3/4", actual);
    }

    @Test
    void setWholeNumDecimalToFraction() {
        // Arrange
        quadEqua2.setSectionA(8); // You can set this to "8" or "8.0", due to how doubles work, either works.

        // Act
        String actual = QuadraticEquation.decimalToFraction(quadEqua2.getSectionA());

        // Assert
        assertEquals("8", actual);
    }

    @Test
    void setDecimalWithExtendedZerosToFraction(){
        // Arrange
        quadEqua2.setSectionA(1.0000000);

        // Act
        String actual = QuadraticEquation.decimalToFraction(quadEqua2.getSectionA());

        // Assert
        assertEquals("1", actual);
    }

    @Test
    void setInfiniteLengthDecimalToFraction(){ // "0.333333333333333" -> "1/3", I can make something to check for a single repeating digit but there are other infinitely long decimals that have two or three digits repeating that I can't check for.
        // Arrange
        quadEqua2.setSectionA(0.33333333333333333333333);

        // Act
        String actual = QuadraticEquation.decimalToFraction(quadEqua2.getSectionA());

        // Assert
        assertEquals("0.3333", actual);
    }

    @Test
    void returnYInterceptWhenGivenADecimal(){
        // Act
        quadEqua1.setSectionC(0.25);

        // Assert
        assertEquals("(0, 1/4)", quadEqua1.findYIntercept());
    }

    @Test
    void findVertex() {
        // Act
        String[] expected1 = {"-1/2", "3/4"}; // good(?)
        String[] expected2 = {"-0.3333", "3.6667"}; // issue

        String[] actual1 = quadEqua1.findVertex();

        String[] actual2 = quadEqua2.findVertex();

        // Assert
        assertEquals(Arrays.toString(expected1), Arrays.toString(actual1));
        assertEquals(Arrays.toString(expected2), Arrays.toString(actual2));
    }

    @Test
    void convertToVertexFormWithWholeNumbers() { // y = ax^2+bx+c -> y = a(x-h)^2 + k
        // Arrange
        String[] vertex = {"3", "4"};

        // Act
        String actual = quadEqua1.convertToVertexForm(vertex);

        // Assert
        assertEquals("(x-3)+4", actual);
    }

    @Test
    void convertToVertexFormWithFractions(){
        // Arrange
        String[] vertex = {"1/4", "7/8"};

        // Act
        String actual = quadEqua2.convertToVertexForm(vertex);

        // Assert
        assertEquals("3(x-1/4)+7/8", actual);
    }

    @Test
    void convertToVertexFormWithNegatives(){
        // Arrange
        String[] vertex = {"-3", "-4"};

        // Act
        String actual = quadEqua2.convertToVertexForm(vertex);

        // Assert
        assertEquals("3(x+3)-4", actual);
    }

    @Test
    void positiveDiscriminantInQuadraticFormula(){
        // Arrange
        quadEqua2 = new QuadraticEquation(1, 2.75, -5);
        String[] expected = {"-4", "5/4"};

        // Act
        String[] actual = quadEqua2.findRoots();

        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    void zeroAsDiscriminantInQuadraticFormula(){
        // Arrange
        quadEqua2 = new QuadraticEquation(1, 2, 1);
        String[] expected = {"-1", ""};

        // Act
        String[] actual = quadEqua2.findRoots();

        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    void negativeDiscriminantInQuadraticFormula(){
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> quadEqua1.findRoots());

    }

    @Test
    void convertToSlopeInterceptForm() { // ax^2+bx+c -> a(x-p)(x-q)
        // Arrange
        quadEqua2 = new QuadraticEquation(1, 2, -3);

        // Act
        String actual = quadEqua2.convertToSlopeInterceptForm(quadEqua2.findRoots(), quadEqua2.getSectionA());

        // Assert
        assertEquals("(x+3)(x-1)", actual); // -3 = a(0+3)(0-1) -> -3 = -3a -> -3/-3 = a -> a = 1
    }

    @Test
    void covertToSlopeInterceptFormWhenASectionIs11(){
        // Arrange
        quadEqua2 = new QuadraticEquation(11, 2, -3);

        // Act
        String actual = quadEqua2.convertToSlopeInterceptForm(quadEqua2.findRoots(), quadEqua2.getSectionA());

        // Assert
        assertEquals("11(x+621/1000)(x-0.4392)", actual);
    }
}