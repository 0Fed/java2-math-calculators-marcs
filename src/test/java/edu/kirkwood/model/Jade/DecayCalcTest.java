package edu.kirkwood.model.Jade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the calculations and validation methods in DecayCalc.
 * Includes different input values, comparison scenarios, and boundary cases.
 *
 * AI Assistance: I used ChatGPT to help organize the tests, improve
 * comments and Javadocs, and troubleshoot failing tests.
 *
 */
class DecayCalcTest {

    private DecayCalc problem;

    /** Creates a standard DecayCalc object before each test. */
    @BeforeEach
    void setUp() {
        problem = new DecayCalc(100.0, 50.0, 0.1, 10.0);
    }



    // -------------------- Remaining Amount Tests --------------------

    /**
     * Tests the calculation of the remaining amount.
     * The initial amount is 100, the decay constant is 0.1,
     * and the elapsed time is 10. The expected remaining
     * amount is approximately 36.79.
     */
    @Test
    void calculateRemainingAmount() {
        double result = DecayCalc.calculateRemainingAmount(problem);

        assertEquals(36.79, result, 0.01);
    }

    /**
     * Tests the remaining amount calculation using a different
     * initial amount, decay constant, and elapsed time.
     */
    @Test
    void calculateRemainingAmountWithDifferentValues() {
        DecayCalc problem =
                new DecayCalc(200.0, 1.0, 0.05, 5.0);

        double result = DecayCalc.calculateRemainingAmount(problem);

        assertEquals(155.76, result, 0.01);
    }

    /**
     * Tests that a larger amount of elapsed time causes
     * the calculated remaining amount to become smaller.
     *
     * AI Assistance: I used ChatGPT to help me come up with the idea
     * of comparing two different elapsed times to test that the
     * remaining amount decreases over time.
     *
     */
    @Test
    void longerTimeProducesSmallerRemainingAmount() {
        DecayCalc shortTime =
                new DecayCalc(100.0, 1.0, 0.1, 5.0);

        DecayCalc longTime =
                new DecayCalc(100.0, 1.0, 0.1, 10.0);

        double shortTimeResult =
                DecayCalc.calculateRemainingAmount(shortTime);

        double longTimeResult =
                DecayCalc.calculateRemainingAmount(longTime);

        assertTrue(longTimeResult < shortTimeResult);
    }


    // -------------------- Initial Amount Tests --------------------

    /**
     * Tests the calculation of the initial amount.
     * The remaining amount, decay constant, and elapsed time
     * are used to calculate an initial amount of approximately 100.
     */
    @Test
    void calculateInitialAmount() {
        DecayCalc problem =
                new DecayCalc(100.0, 36.79, 0.1, 10.0);

        double result = DecayCalc.calculateInitialAmount(problem);

        assertEquals(100.0, result, 0.01);
    }

    /**
     * Tests the initial amount calculation using a different
     * set of values.
     */
    @Test
    void calculateInitialAmountWithDifferentValues() {
        DecayCalc problem =
                new DecayCalc(200.0, 155.76, 0.05, 5.0);

        double result = DecayCalc.calculateInitialAmount(problem);

        assertEquals(200.0, result, 0.01);
    }


    // -------------------- Decay Constant Tests --------------------

    /**
     * Tests the calculation of the decay constant.
     * The initial amount, remaining amount, and elapsed time
     * are used to calculate a decay constant of approximately 0.1.
     */
    @Test
    void calculateDecayConstant() {
        DecayCalc problem =
                new DecayCalc(100.0, 36.7879, 0.1, 10.0);

        double result = DecayCalc.calculateDecayConstant(problem);

        assertEquals(0.1, result, 0.01);
    }

    /**
     * Tests the decay constant calculation using different
     * input values.
     */
    @Test
    void calculateDecayConstantWithDifferentValues() {
        DecayCalc problem =
                new DecayCalc(200.0, 155.76, 0.1, 5.0);

        double result = DecayCalc.calculateDecayConstant(problem);

        assertEquals(0.05, result, 0.01);
    }

    /**
     * Tests that no decrease between the initial and remaining
     * amounts produces a decay constant of zero.
     *
     * AI Assistance: I used ChatGPT to help me figure out what
     * the decay constant should be when the initial and remaining
     * amounts are equal.
     */
    @Test
    void equalAmountsProduceZeroDecayConstant() {
        DecayCalc problem =
                new DecayCalc(100.0, 100.0, 0.1, 10.0);

        double result = DecayCalc.calculateDecayConstant(problem);

        assertEquals(0.0, result, 0.01);
    }


    // -------------------- Elapsed Time Tests --------------------

    /**
     * Tests the calculation of elapsed time.
     * The initial amount, remaining amount, and decay constant
     * are used to calculate an elapsed time of approximately 10.
     */
    @Test
    void calculateElapsedTime() {
        DecayCalc problem =
                new DecayCalc(100.0, 36.7879, 0.1, 10.0);

        double result = DecayCalc.calculateElapsedTime(problem);

        assertEquals(10.0, result, 0.01);
    }

    /**
     * Tests the elapsed time calculation using different
     * input values.
     */
    @Test
    void calculateElapsedTimeWithDifferentValues() {
        DecayCalc problem =
                new DecayCalc(200.0, 155.76, 0.05, 10.0);

        double result = DecayCalc.calculateElapsedTime(problem);

        assertEquals(5.0, result, 0.01);
    }

    // ==================== Tests migrated from DecayResultTest.java ====================
    // -------------------- Calculation Result Tests --------------------

    /**
     ** Tests that the remaining amount calculation returns
     ** the expected result.
     ** Starting with 100, a decay constant of 0.1,
     ** and 10 units of time, approximately 36.79 remains.
     **/
    @Test
    void remainingAmountResultIsCorrect() {
        DecayCalc problem = new DecayCalc(100.0, 1.0, 0.1, 10.0);
        double result = DecayCalc.calculateRemainingAmount(problem);
        assertEquals(36.79, result, 0.01);
    }

    /**
     ** Tests that the initial amount calculation returns
     ** the expected original amount.
     ** A remaining amount of approximately 36.79
     ** should correspond to an initial amount of 100.
     **/
    @Test
    void initialAmountResultIsCorrect() {
        DecayCalc problem = new DecayCalc(1.0, 36.79, 0.1, 10.0);
        double result = DecayCalc.calculateInitialAmount(problem);
        assertEquals(100.0, result, 0.02);
    }

    /**
     ** Tests that the decay constant calculation returns
     ** the expected rate of decay.
     **/
    @Test
    void decayConstantResultIsCorrect() {
        double result = DecayCalc.calculateDecayConstant(problem);

        assertEquals(0.06931, result, 0.00001);
    }

    /**
     ** Tests that the elapsed time calculation returns
     ** the expected amount of time.
     **/
    @Test
    void elapsedTimeResultIsCorrect() {
        DecayCalc problem = new DecayCalc(100.0, 50.0, 0.1, 1.0);
        double result = DecayCalc.calculateElapsedTime(problem);
        assertEquals(6.93147, result, 0.00001);
    }

    /**
     ** Tests that changing the initial amount changes
     ** the calculated remaining amount.
     **/
    @Test
    void changingInitialAmountChangesResult() {
        DecayCalc firstProblem = new DecayCalc(100.0, 1.0, 0.1, 10.0);
        DecayCalc secondProblem = new DecayCalc(200.0, 1.0, 0.1, 10.0);
        double firstResult = DecayCalc.calculateRemainingAmount(firstProblem);
        double secondResult = DecayCalc.calculateRemainingAmount(secondProblem);
        assertTrue(secondResult > firstResult);
    }

    /**
     ** Tests that identical inputs produce identical
     ** numerical results.
     **/
    @Test
    void identicalInputsProduceEqualResults() {
        DecayCalc firstProblem = new DecayCalc(100.0, 1.0, 0.1, 10.0);
        DecayCalc secondProblem = new DecayCalc(100.0, 1.0, 0.1, 10.0);
        double firstResult = DecayCalc.calculateRemainingAmount(firstProblem);
        double secondResult = DecayCalc.calculateRemainingAmount(secondProblem);
        assertEquals(firstResult, secondResult, 0.000001);
    }

    /**
     ** Tests that two different decay calculations
     ** produce different numerical results.
     **/
    @Test
    void differentInputsProduceDifferentResults() {
        DecayCalc firstProblem = new DecayCalc(100.0, 1.0, 0.1, 10.0);
        DecayCalc secondProblem = new DecayCalc(100.0, 1.0, 0.2, 10.0);
        double firstResult = DecayCalc.calculateRemainingAmount(firstProblem);
        double secondResult = DecayCalc.calculateRemainingAmount(secondProblem);
        assertNotEquals(firstResult, secondResult);
    }

    /**
     ** Tests that a larger decay constant produces
     ** a smaller remaining amount when the initial
     ** amount and elapsed time are the same.
     **/
    @Test
    void largerDecayConstantProducesSmallerResult() {
        DecayCalc slowerDecay = new DecayCalc(100.0, 1.0, 0.1, 10.0);
        DecayCalc fasterDecay = new DecayCalc(100.0, 1.0, 0.2, 10.0);
        double slowerResult = DecayCalc.calculateRemainingAmount(slowerDecay);
        double fasterResult = DecayCalc.calculateRemainingAmount(fasterDecay);
        assertTrue(fasterResult < slowerResult);
    }

    /**
     ** Tests that a longer elapsed time produces
     ** a smaller remaining amount when the initial
     ** amount and decay constant stay the same.
     **/
    @Test
    void longerElapsedTimeProducesSmallerResult() {
        DecayCalc shorterTime = new DecayCalc(100.0, 1.0, 0.1, 5.0);
        DecayCalc longerTime = new DecayCalc(100.0, 1.0, 0.1, 10.0);
        double shorterResult = DecayCalc.calculateRemainingAmount(shorterTime);
        double longerResult = DecayCalc.calculateRemainingAmount(longerTime);
        assertTrue(longerResult < shorterResult);
    }

    /**
     ** Tests that the calculated remaining amount
     ** is positive and less than the initial amount.
     **/
    @Test
    void remainingAmountStaysWithinExpectedRange() {
        DecayCalc problem = new DecayCalc(100.0, 1.0, 0.1, 10.0);
        double result = DecayCalc.calculateRemainingAmount(problem);
        assertTrue(result > 0);
        assertTrue(result < 100.0);
    }

    // ==================== Tests migrated from InputValidatorTest.java ====================
    // -------------------- Amount Tests --------------------

    /**
     * Tests that a positive amount is accepted as valid.
     * An amount must be greater than zero, so 100.0 should
     * pass the amount validation.
     */
    @Test
    void validAmountIsAccepted() {
        assertTrue(DecayCalc.isValidAmount(100.0));
    }

    /**
     * Tests that an amount of zero is rejected.
     * Zero is not a valid amount for the decay calculations,
     * so the validation method should return false.
     */
    @Test
    void zeroAmountIsRejected() {
        assertFalse(DecayCalc.isValidAmount(0.0));
    }

    /**
     * Tests that a negative amount is rejected.
     * Amounts cannot be negative, so -50.0 should be
     * rejected by the validation method.
     */
    @Test
    void negativeAmountIsRejected() {
        assertFalse(DecayCalc.isValidAmount(-50.0));
    }

    /**
     * Tests that a very small positive amount is accepted.
     * Any amount greater than zero should be valid.
     */
    @Test
    void smallPositiveAmountIsAccepted() {
        assertTrue(DecayCalc.isValidAmount(0.01));
    }


    // -------------------- Decay Constant Tests --------------------

    /**
     * Tests that a positive decay constant is accepted.
     * The decay constant must be greater than zero, so
     * 0.1 should be accepted.
     */
    @Test
    void validDecayConstantIsAccepted() {
        assertTrue(DecayCalc.isValidDecayConstant(0.1));
    }

    /**
     * Tests that a decay constant of zero is rejected.
     * A zero decay constant is not considered valid by
     * the validation rules.
     */
    @Test
    void zeroDecayConstantIsRejected() {
        assertFalse(DecayCalc.isValidDecayConstant(0.0));
    }

    /**
     * Tests that a negative decay constant is rejected.
     * The decay constant must be greater than zero, so
     * -0.1 should be rejected.
     */
    @Test
    void negativeDecayConstantIsRejected() {
        assertFalse(DecayCalc.isValidDecayConstant(-0.1));
    }

    /**
     * Tests that a very small positive decay constant
     * is still accepted as valid.
     */
    @Test
    void smallPositiveDecayConstantIsAccepted() {
        assertTrue(DecayCalc.isValidDecayConstant(0.001));
    }


    // -------------------- Time Tests --------------------

    /**
     * Tests that a positive elapsed time is accepted.
     * An elapsed time greater than zero is valid, so
     * 10.0 should be accepted.
     */
    @Test
    void validTimeIsAccepted() {
        assertTrue(DecayCalc.isValidTime(10.0));
    }

    /**
     * Tests that an elapsed time of zero is rejected.
     * The validation rules require the elapsed time to be
     * greater than zero.
     */
    @Test
    void zeroTimeIsRejected() {
        assertFalse(DecayCalc.isValidTime(0.0));
    }

    /**
     * Tests that a negative elapsed time is rejected.
     * Time cannot be negative for this calculation, so
     * -10.0 should be rejected.
     */
    @Test
    void negativeTimeIsRejected() {
        assertFalse(DecayCalc.isValidTime(-10.0));
    }

    /**
     * Tests that a very small positive elapsed time
     * is still accepted as valid.
     */
    @Test
    void smallPositiveTimeIsAccepted() {
        assertTrue(DecayCalc.isValidTime(0.01));
    }


    // -------------------- Complete Decay Problem Tests --------------------

    /**
     * Tests that a complete valid decay problem is accepted.
     * All four values are valid and the remaining amount
     * is less than the initial amount.
     */
    @Test
    void validDecayProblemIsAccepted() {
        assertTrue(DecayCalc.isValidDecayProblem(problem));
    }

    /**
     * Tests that a decay problem is rejected when the
     * remaining amount is greater than the initial amount.
     */
    @Test
    void remainingAmountGreaterThanInitialIsRejected() {

        DecayCalc problem =
                new DecayCalc(50.0, 100.0, 0.1, 10.0);

        assertFalse(DecayCalc.isValidDecayProblem(problem));
    }

    /**
     * Tests that a null DecayCalc object is rejected.
     * A null object cannot contain valid decay information.
     */
    @Test
    void nullDecayProblemIsRejected() {
        assertFalse(DecayCalc.isValidDecayProblem(null));
    }

    /**
     * Tests the boundary where the remaining amount is
     * exactly equal to the initial amount.
     * The validation rule allows the remaining amount to
     * be equal to, but not greater than, the initial amount.
     *
     */
    @Test
    void remainingAmountEqualToInitialIsAccepted() {
        DecayCalc problem =
                new DecayCalc(100.0, 100.0, 0.1, 10.0);

        assertTrue(DecayCalc.isValidDecayProblem(problem));
    }

    // -------------------- Amount Validation Tests --------------------

    /**
     * Tests that an exception is thrown when the remaining
     * amount is greater than the initial amount.
     * Also verifies that the correct error message is returned.
     */
    @Test
    void validateAmountsRemainingGreaterThanInitialThrowsException() {
        // Arrange
        double initialAmount = 100.0;
        double remainingAmount = 150.0;

        // Act and Assert
        Exception e = assertThrows(
                IllegalArgumentException.class,
                () -> DecayCalc.validateAmounts(initialAmount, remainingAmount)
        );

        // Arrange
        String expectedMessage =
                "The remaining amount is greater than the initial amount. " +
                        "Exponential decay represents a decrease, not an increase. " +
                        "Enter a remaining amount that is less than the initial amount.";

        // Act
        String actualMessage = e.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Tests that valid amounts are accepted without throwing
     * an exception when the remaining amount is smaller
     * than the initial amount.
     */
    @Test
    void validateAmountsWithValidAmountsDoesNotThrow() {
        // Arrange
        double initialAmount = 100.0;
        double remainingAmount = 50.0;

        // Act and Assert
        assertDoesNotThrow(() ->
                DecayCalc.validateAmounts(initialAmount, remainingAmount)
        );
    }

    /**
     * Tests that an exception is thrown when the initial
     * and remaining amounts are equal.
     * Also verifies that the correct error message is returned.
     */
    @Test
    void validateAmountsWithEqualAmountsThrowsException() {
        // Arrange
        double initialAmount = 100.0;
        double remainingAmount = 100.0;

        // Act and Assert
        Exception e = assertThrows(
                IllegalArgumentException.class,
                () -> DecayCalc.validateAmounts(initialAmount, remainingAmount)
        );

        // Arrange
        String expectedMessage =
                "The remaining amount equals the initial amount. " +
                        "No decay has occurred. " +
                        "Enter a remaining amount that is less than the initial amount.";

        // Act
        String actualMessage = e.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    // -------- Tests Migrated from DecayProblemTest.java --------
    // -------------------- Getter Tests --------------------

    /** Tests that the initial amount set during setup is returned correctly. */
    @Test
    void initialAmountIsStoredCorrectly() {
        assertEquals(100.0, problem.getInitialAmount());
    }

    /** Tests that the remaining amount set during setup is returned correctly. */
    @Test
    void remainingAmountIsStoredCorrectly() {
        assertEquals(50.0, problem.getRemainingAmount());
    }

    /** Tests that the decay constant set during setup is returned correctly. */
    @Test
    void decayConstantIsStoredCorrectly() {
        assertEquals(0.1, problem.getDecayConstant());
    }

    /** Tests that the elapsed time set during setup is returned correctly. */
    @Test
    void elapsedTimeIsStoredCorrectly() {
        assertEquals(10.0, problem.getElapsedTime());
    }

    // -------------------- Setter Tests --------------------

    /** Tests that changing the initial amount updates its stored value. */
    @Test
    void initialAmountCanBeChanged() {
        problem.setInitialAmount(200.0);
        assertEquals(200.0, problem.getInitialAmount());
    }

    /** Tests that an initial amount of zero throws an exception. */
    @Test
    void setInitialAmountZeroThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> problem.setInitialAmount(0.0));
    }

    /** Tests that a negative initial amount throws an exception. */
    @Test
    void setInitialAmountNegativeThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> problem.setInitialAmount(-100.0));
    }

    /** Tests that changing the remaining amount updates its stored value. */
    @Test
    void remainingAmountCanBeChanged() {
        problem.setRemainingAmount(25.0);
        assertEquals(25.0, problem.getRemainingAmount());
    }

    /** Tests that a remaining amount of zero throws an exception. */
    @Test
    void setRemainingAmountZeroThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> problem.setRemainingAmount(0.0));
    }

    /** Tests that a negative remaining amount throws an exception. */
    @Test
    void setRemainingAmountNegativeThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> problem.setRemainingAmount(-50.0));
    }

    /** Tests that changing the decay constant updates its stored value. */
    @Test
    void decayConstantCanBeChanged() {
        problem.setDecayConstant(0.2);
        assertEquals(0.2, problem.getDecayConstant());
    }

    /** Tests that a decay constant of zero throws an exception. */
    @Test
    void setDecayConstantZeroThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> problem.setDecayConstant(0.0));
    }

    /** Tests that a negative decay constant throws an exception. */
    @Test
    void setDecayConstantNegativeThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> problem.setDecayConstant(-0.1));
    }

    /** Tests that changing elapsed time updates its stored value. */
    @Test
    void elapsedTimeCanBeChanged() {
        problem.setElapsedTime(20.0);
        assertEquals(20.0, problem.getElapsedTime());
    }

    /** Tests that an elapsed time of zero throws an exception. */
    @Test
    void setElapsedTimeZeroThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> problem.setElapsedTime(0.0));
    }

    /** Tests that a negative elapsed time throws an exception. */
    @Test
    void setElapsedTimeNegativeThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> problem.setElapsedTime(-10.0));
    }

    // -------------------- Equals Tests --------------------

    /** Tests that two DecayCalc objects with identical values are equal. */
    @Test
    void equalDecayProblemsAreEqual() {
        DecayCalc equalProblem =
                new DecayCalc(100.0, 50.0, 0.1, 10.0);
        assertEquals(problem, equalProblem);
    }

    /** Tests that two DecayCalc objects with different values are not equal. */
    @Test
    void differentDecayProblemsAreNotEqual() {
        DecayCalc differentProblem =
                new DecayCalc(100.0, 40.0, 0.1, 10.0);
        assertNotEquals(problem, differentProblem);
    }

    /** Tests that a DecayCalc object is equal to itself. */
    @Test
    void decayProblemIsEqualToItself() {
        assertEquals(problem, problem);
    }

    /** Tests that a DecayCalc object is not equal to null. */
    @Test
    void decayProblemIsNotEqualToNull() {
        assertNotEquals(problem, null);
    }

    /** Tests that a DecayCalc object is not equal to an object of another class. */
    @Test
    void decayProblemIsNotEqualToDifferentClass() {
        assertNotEquals(problem, "DecayProblem");
    }

    // -------------------- CompareTo Tests --------------------

    /** Tests that compareTo returns zero for equal initial amounts. */
    @Test
    void compareToReturnsZeroForEqualInitialAmounts() {
        DecayCalc sameInitialAmount =
                new DecayCalc(100.0, 25.0, 0.2, 5.0);
        assertEquals(0, problem.compareTo(sameInitialAmount));
    }

    /** Tests that compareTo returns positive for a larger initial amount. */
    @Test
    void compareToReturnsPositiveForLargerInitialAmount() {
        DecayCalc largerProblem =
                new DecayCalc(200.0, 50.0, 0.1, 10.0);
        assertTrue(largerProblem.compareTo(problem) > 0);
    }

    /** Tests that compareTo returns negative for a smaller initial amount. */
    @Test
    void compareToReturnsNegativeForSmallerInitialAmount() {
        DecayCalc smallerProblem =
                new DecayCalc(50.0, 25.0, 0.1, 10.0);
        assertTrue(smallerProblem.compareTo(problem) < 0);
    }
}