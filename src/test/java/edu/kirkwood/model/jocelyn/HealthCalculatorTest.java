package edu.kirkwood.model.jocelyn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthCalculatorTest {

    HealthCalculator h1;
    HealthCalculator h2;


    @BeforeEach
    void setUp() {
        h1 = new HealthCalculator(25, 68.0, 160.0,
                3, 2);

        h2 = new HealthCalculator(35, 72.0, 200.0,
                4, 1);
    }

    @Test
    void getAge() {
        assertEquals(25, h1.getAge());
        assertEquals(35, h2.getAge());
    }

    @Test
    void getHeight() {
        assertEquals(68.0, h1.getHeight());
        assertEquals(72.0, h2.getHeight());
    }

    @Test
    void getWeight() {
        assertEquals(160.0, h1.getWeight());
        assertEquals(200.0, h2.getWeight());
    }

    @Test
    void getActivityLevel() {
        assertEquals(3, h1.getActivityLevel());
        assertEquals(4, h2.getActivityLevel());
    }

    @Test
    void getFitnessGoal() {
        assertEquals(2, h1.getFitnessGoal());
        assertEquals(1, h2.getFitnessGoal());
    }

    @Test
    void setAge() {
        // Act
        h1.setAge(30);

        // Assert
        assertEquals(30, h1.getAge());
    }

    @Test
    void setAgeInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> h1.setAge(0));
        assertThrows(IllegalArgumentException.class,
                () -> h1.setAge(-5));
    }

    @Test
    void setHeight() {
        // Act
        h1.setHeight(70.0);

        // Assert
        assertEquals(70.0, h1.getHeight());
    }

    @Test
    void setHeightInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> h1.setHeight(0));
        assertThrows(IllegalArgumentException.class,
                () -> h1.setHeight(-10));
    }

    @Test
    void setWeight() {
        // Act
        h1.setWeight(175.0);

        // Assert
        assertEquals(175.0, h1.getWeight());
    }

    @Test
    void setWeightInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> h1.setWeight(0));
        assertThrows(IllegalArgumentException.class,
                () -> h1.setWeight(-150));
    }

    @Test
    void setActivityLevel() {
        // Act
        h1.setActivityLevel(4);

        // Assert
        assertEquals(4, h1.getActivityLevel());
    }

    @Test
    void setActivityLevelInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> h1.setActivityLevel(0));

        assertThrows(IllegalArgumentException.class,
                () -> h1.setActivityLevel(6));
    }

    @Test
    void setFitnessGoal() {
        // Act
        h1.setFitnessGoal(1);

        // Assert
        assertEquals(1, h1.getFitnessGoal());
    }

    @Test
    void setFitnessGoalInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> h1.setFitnessGoal(0));

        assertThrows(IllegalArgumentException.class,
                () -> h1.setFitnessGoal(4));
    }

    @Test
    void testToString() {
        assertEquals(
                "HealthCalculator{age=25, height=68.0, weight=160.0, activityLevel=3, fitnessGoal=2}",
                h1.toString()
        );

        assertEquals(
                "HealthCalculator{age=35, height=72.0, weight=200.0, activityLevel=4, fitnessGoal=1}",
                h2.toString()
        );
    }

    @Test
    void calculateBMI() {
        assertEquals(24.33, h1.calculateBMI(), 0.01);
    }

    @Test
    void determineBMICategory() {
        assertEquals("Normal", h1.determineBMICategory());
    }

    @Test
    void determineBMICategoryOverweight() {
        HealthCalculator calculator =
                new HealthCalculator(30, 68.0, 180.0,
                        3, 2);

        assertEquals("Overweight", calculator.determineBMICategory());
    }

    @Test
    void determineBMICategoryUnderweight() {
        HealthCalculator calculator =
                new HealthCalculator(30, 68.0, 110.0,
                        3, 2);

        assertEquals("Underweight", calculator.determineBMICategory());
    }

    @Test
    void determineBMICategoryObese() {
        HealthCalculator calculator =
                new HealthCalculator(30, 68.0, 220.0,
                        3, 2);

        assertEquals("Obese", calculator.determineBMICategory());
    }

    @Test
    void determineBMICategoryBoundaryNormal() {
        double weight = (18.5 * 68 * 68) / 703;

        h1.setWeight(weight);

        assertEquals("Normal", h1.determineBMICategory());
    }

    @Test
    void determineBMICategoryBoundaryOverweight() {
        double weight = (25.0 * 68 * 68) / 703;

        h1.setWeight(weight);

        assertEquals("Overweight", h1.determineBMICategory());
    }

    @Test
    void determineBMICategoryBoundaryObese() {
        double weight = (30.0 * 68 * 68) / 703;

        h1.setWeight(weight);

        assertEquals("Obese", h1.determineBMICategory());
    }

    @Test
    void calculateBMR() {
        // Arrange
        HealthCalculator calculator =
                new HealthCalculator(25, 68.0, 160.0,
                        3, 2);

        // Act
        double result = calculator.calculateBMR();

        // Assert
        assertEquals(1741.8, result, 0.01);
    }

    @Test
    void calculateTDEESedentary() {
        // Arrange
        HealthCalculator calculator =
                new HealthCalculator(25, 68.0, 160.0,
                        1, 2);

        // Act
        double result = calculator.calculateTDEE();

        // Assert
        assertEquals(2090.16, result, 0.01);
    }

    @Test
    void calculateTDEELight() {
        // Arrange
        HealthCalculator calculator =
                new HealthCalculator(25, 68.0, 160.0,
                        2, 2);

        // Act
        double result = calculator.calculateTDEE();

        // Assert
        assertEquals(2394.97, result, 0.01);
    }

    @Test
    void calculateTDEEModerate() {
        // Arrange
        HealthCalculator calculator =
                new HealthCalculator(25, 68.0, 160.0,
                        3, 2);

        // Act
        double result = calculator.calculateTDEE();

        // Assert
        assertEquals(2699.78, result, 0.01);
    }

    @Test
    void calculateTDEEActive() {
        // Arrange
        HealthCalculator calculator =
                new HealthCalculator(25, 68.0, 160.0,
                        4, 2);

        // Act
        double result = calculator.calculateTDEE();

        // Assert
        assertEquals(3004.60, result, 0.01);
    }

    @Test
    void calculateTDEEVeryActive() {
        // Arrange
        HealthCalculator calculator =
                new HealthCalculator(25, 68.0, 160.0,
                        5, 2);

        // Act
        double result = calculator.calculateTDEE();

        // Assert
        assertEquals(3309.41, result, 0.01);
    }

    @Test
    void calculateCalorieTargetLose() {
        // Arrange
        HealthCalculator calculator =
                new HealthCalculator(25, 68.0, 160.0,
                        3, 1);

        // Act
        double result = calculator.calculateCalorieTarget();

        // Assert
        assertEquals(2199.78, result, 0.01);
    }

    @Test
    void calculateCalorieTargetMaintain() {
        // Arrange
        HealthCalculator calculator =
                new HealthCalculator(25, 68.0, 160.0,
                        3, 2);

        // Act
        double result = calculator.calculateCalorieTarget();

        // Assert
        assertEquals(2699.78, result, 0.01);
    }

    @Test
    void calculateCalorieTargetGain() {
        // Arrange
        HealthCalculator calculator =
                new HealthCalculator(25, 68.0, 160.0,
                        3, 3);

        // Act
        double result = calculator.calculateCalorieTarget();

        // Assert
        assertEquals(3199.78, result, 0.01);
    }

    @Test
    void calculateHealthyWeightMinimum() {
        // Arrange
        HealthCalculator calculator =
                new HealthCalculator(25, 68.0, 160.0,
                        3, 2);

        // Act
        double result = calculator.calculateHealthyWeightMinimum();

        // Assert
        assertEquals(121.68, result, 0.01);
    }

    @Test
    void calculateHealthyWeightMinimumDifferentHeight() {
        // Arrange
        HealthCalculator calculator =
                new HealthCalculator(25, 72.0, 180.0,
                        3, 2);

        // Act
        double result = calculator.calculateHealthyWeightMinimum();

        // Assert
        assertEquals(136.42, result, 0.01);
    }

    @Test
    void calculateHealthyWeightMaximum() {
        // Arrange
        HealthCalculator calculator =
                new HealthCalculator(25, 68.0, 160.0,
                        3, 2);

        // Act
        double result = calculator.calculateHealthyWeightMaximum();

        // Assert
        assertEquals(163.78, result, 0.01);
    }

    @Test
    void calculateHealthyWeightMaximumDifferentHeight() {
        // Arrange
        HealthCalculator calculator =
                new HealthCalculator(25, 72.0, 180.0,
                        3, 2);

        // Act
        double result = calculator.calculateHealthyWeightMaximum();

        // Assert
        assertEquals(183.62, result, 0.01);
    }

    @Test
    void testEquals() {
        HealthCalculator same = new HealthCalculator(
                25, 68.0, 160.0, 3, 2);

        assertEquals(h1, same);
        assertNotEquals(h1, h2);
    }

    @Test
    void testHashCode() {
        HealthCalculator same = new HealthCalculator(
                25, 68.0, 160.0, 3, 2);

        assertEquals(h1.hashCode(), same.hashCode());
    }

    @Test
    void testCompareTo() {
        HealthCalculator same = new HealthCalculator(
                25, 68.0, 160.0, 3, 2);

        assertTrue(h1.compareTo(h2) < 0);
        assertTrue(h2.compareTo(h1) > 0);
        assertEquals(0, h1.compareTo(same));
    }
}