package edu.kirkwood.model.Jade;

import java.util.Objects;

/**
 * Performs calculations for exponential decay problems.
 * It calculates remaining amount, initial amount, decay constant,
 * and elapsed time, and stores and validates decay problem values.
 *
 * AI Assistance: I used ChatGPT to help write and organize Javadoc
 * comments, clean up code formatting, and troubleshoot errors.
 */
public class DecayCalc implements Comparable<DecayCalc> {

    // -------------------- Remaining Amount Calculation --------------------

    /**
     * Calculates the remaining amount using the exponential decay formula.
     * Formula: A = A₀e^(-kt)
     *
     * AI Assistance: I used ChatGPT to help figure out how to convert
     * this formula into java.
     *
     * @param problem the DecayCalc object containing the initial amount,
     *                decay constant, and elapsed time
     * @return the calculated remaining amount
     */
    public static double calculateRemainingAmount(DecayCalc problem) {

        // Multiply the initial amount by e raised to the
        // negative decay constant multiplied by elapsed time.
        return problem.getInitialAmount()
                * Math.exp(-problem.getDecayConstant()
                * problem.getElapsedTime());
    }


    // -------------------- Initial Amount Calculation --------------------

    /**
     * Calculates the initial amount using the exponential decay formula.
     * Formula: A₀ = A / e^(-kt)
     *
     * @param problem the DecayCalc object containing the remaining amount,
     *                decay constant, and elapsed time
     * @return the calculated initial amount
     */
    public static double calculateInitialAmount(DecayCalc problem) {

        // Divide the remaining amount by the exponential
        // decay factor to find the original amount.
        return problem.getRemainingAmount()
                / Math.exp(-problem.getDecayConstant()
                * problem.getElapsedTime());
    }


    // -------------------- Decay Constant Calculation --------------------

    /**
     * Calculates the decay constant using the exponential decay formula.
     * Formula: k = -ln(A/A₀) / t
     *
     * @param problem the DecayCalc object containing the initial amount,
     *                remaining amount, and elapsed time
     * @return the calculated decay constant
     */
    public static double calculateDecayConstant(DecayCalc problem) {

        // Divide the remaining amount by the initial amount,
        // take the natural logarithm, make it negative,
        // and divide by the elapsed time.
        return -Math.log(problem.getRemainingAmount()
                / problem.getInitialAmount())
                / problem.getElapsedTime();
    }


    // -------------------- Elapsed Time Calculation --------------------

    /**
     * Calculates the elapsed time using the exponential decay formula.
     * Formula: t = -ln(A/A₀) / k
     *
     * @param problem the DecayCalc object containing the initial amount,
     *                remaining amount, and decay constant
     * @return the calculated elapsed time
     */
    public static double calculateElapsedTime(DecayCalc problem) {

        // Divide the remaining amount by the initial amount,
        // take the natural logarithm, make it negative,
        // and divide by the decay constant.
        return -Math.log(problem.getRemainingAmount()
                / problem.getInitialAmount())
                / problem.getDecayConstant();
    }

    // -------------------- Originally from DecayProblem.java --------------------

    // -------------------- Fields --------------------

    // Stores the original amount before decay occurs.
    private double initialAmount;

    // Stores the remaining amount used in the exponential decay formula.
    private double remainingAmount;

    // Stores the decay constant used in the exponential decay formula.
    private double decayConstant;

    // Stores the amount of time that has passed.
    private double elapsedTime;


    // -------------------- Constructor --------------------

    /**
     * Creates a DecayCalc object with the specified values.
     *
     * @param initialAmount   the original amount
     * @param remainingAmount the amount remaining after decay
     * @param decayConstant   the decay constant
     * @param elapsedTime     the amount of time that has passed
     */
    public DecayCalc(double initialAmount, double remainingAmount,
                     double decayConstant, double elapsedTime) {

        // Validate and store the initial amount.
        setInitialAmount(initialAmount);

        // Validate and store the remaining amount.
        setRemainingAmount(remainingAmount);

        // Validate and store the decay constant.
        setDecayConstant(decayConstant);

        // Validate and store the elapsed time.
        setElapsedTime(elapsedTime);
    }


    // -------------------- Getter Methods --------------------

    /**
     * Gets the initial amount.
     *
     * @return the initial amount
     */
    public double getInitialAmount() {

        // Return the initial amount stored in this object.
        return initialAmount;
    }

    /**
     * Gets the remaining amount.
     *
     * @return the remaining amount
     */
    public double getRemainingAmount() {

        // Return the remaining amount stored in this object.
        return remainingAmount;
    }

    /**
     * Gets the decay constant.
     *
     * @return the decay constant
     */
    public double getDecayConstant() {

        // Return the decay constant stored in this object.
        return decayConstant;
    }

    /**
     * Gets the elapsed time.
     *
     * @return the elapsed time
     */
    public double getElapsedTime() {

        // Return the elapsed time stored in this object.
        return elapsedTime;
    }


    // -------------------- Setter Methods --------------------

    /**
     * Sets the initial amount.
     *
     * @param initialAmount the new initial amount
     * @throws IllegalArgumentException if the initial amount is zero or negative
     */
    public void setInitialAmount(double initialAmount) throws IllegalArgumentException {
        if (initialAmount <= 0) {
            throw new IllegalArgumentException("Initial amount must be greater than zero.");
        }

        this.initialAmount = initialAmount;
    }

    /**
     * Sets the remaining amount.
     *
     * @param remainingAmount the new remaining amount
     * @throws IllegalArgumentException if the remaining amount is zero or negative
     */
    public void setRemainingAmount(double remainingAmount)
            throws IllegalArgumentException {

        if (remainingAmount <= 0) {
            throw new IllegalArgumentException(
                    "Remaining amount must be greater than zero.");
        }

        this.remainingAmount = remainingAmount;
    }

    /**
     * Sets the decay constant.
     *
     * @param decayConstant the new decay constant
     * @throws IllegalArgumentException if the decay constant is zero or negative
     */
    public void setDecayConstant(double decayConstant)
            throws IllegalArgumentException {

        if (decayConstant <= 0) {
            throw new IllegalArgumentException(
                    "Decay constant must be greater than zero.");
        }

        this.decayConstant = decayConstant;
    }

    /**
     * Sets the elapsed time.
     *
     * @param elapsedTime the new elapsed time
     * @throws IllegalArgumentException if the elapsed time is zero or negative
     */
    public void setElapsedTime(double elapsedTime)
            throws IllegalArgumentException {

        if (elapsedTime <= 0) {
            throw new IllegalArgumentException(
                    "Elapsed time must be greater than zero.");
        }

        this.elapsedTime = elapsedTime;
    }


    // -------------------- Equals Method --------------------

    /**
     * Compares this DecayCalc object with another object.
     * Two DecayCalc objects are equal when all four stored
     * values are the same.
     *
     * @param other the object to compare with this DecayCalc
     * @return true if the objects have the same values, false otherwise
     */
    @Override
    public boolean equals(Object other) {

        // A null object or different class cannot be equal.
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        // Convert the object so its values can be compared.
        DecayCalc problem = (DecayCalc) other;

        // All four values must be the same.
        return Double.compare(initialAmount, problem.initialAmount) == 0
                && Double.compare(remainingAmount, problem.remainingAmount) == 0
                && Double.compare(decayConstant, problem.decayConstant) == 0
                && Double.compare(elapsedTime, problem.elapsedTime) == 0;
    }

    /**
     * Generates a hash code using the four stored values.
     * Equal DecayCalc objects will produce the same hash code.
     *
     * @return an integer representing the hash code for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                initialAmount,
                remainingAmount,
                decayConstant,
                elapsedTime
        );
    }


    // -------------------- CompareTo Method --------------------

    /**
     * Compares this DecayCalc object to another DecayCalc object
     * based on the initial amount.
     *
     * @param other the DecayCalc object to compare with this one
     * @return a negative number if this initial amount is smaller,
     * zero if the initial amounts are equal, or a positive number
     * if this initial amount is larger
     */
    @Override
    public int compareTo(DecayCalc other) {

        // Compare the initial amounts of the two problems.
        return Double.compare(initialAmount, other.initialAmount);
    }


    /**
     * Validates that the remaining amount is less than
     * the initial amount for an exponential decay calculation.
     *
     * @param initialAmount   the starting amount
     * @param remainingAmount the amount remaining after decay
     * @throws IllegalArgumentException if the remaining amount is greater
     *                                  than or equal to the initial amount
     */
    public static void validateAmounts(double initialAmount, double remainingAmount) {
        if (remainingAmount == initialAmount) {
            throw new IllegalArgumentException(
                    "The remaining amount equals the initial amount. " +
                            "No decay has occurred. Enter a remaining amount that is less than the initial amount."
            );
        }

        if (remainingAmount > initialAmount) {
            throw new IllegalArgumentException(
                    "The remaining amount is greater than the initial amount. " +
                            "Exponential decay represents a decrease, not an increase. " +
                            "Enter a remaining amount that is less than the initial amount."
            );
        }
    }

    // -------------------- Originally from InputValidator.java --------------------
    // -------------------- Amount Validation --------------------

    /**
     * Checks whether an amount is valid.
     * An amount is valid when it is greater than zero.
     *
     * @param amount the amount to validate
     * @return true if the amount is greater than zero; false otherwise
     */
    public static boolean isValidAmount(double amount) {

        // An amount must be greater than zero to be valid.
        return amount > 0;
    }


    // -------------------- Decay Constant Validation --------------------

    /**
     * Checks whether a decay constant is valid.
     * A decay constant is valid when it is greater than zero.
     *
     * @param decayConstant the decay constant to validate
     * @return true if the decay constant is greater than zero; false otherwise
     */
    public static boolean isValidDecayConstant(double decayConstant) {

        // The decay constant must be greater than zero.
        return decayConstant > 0;
    }


    // -------------------- Time Validation --------------------

    /**
     * Checks whether an elapsed time is valid.
     * An elapsed time is valid when it is greater than zero.
     *
     * @param time the time to validate
     * @return true if the time is greater than zero; false otherwise
     */
    public static boolean isValidTime(double time) {

        // Elapsed time must be greater than zero.
        return time > 0;
    }


    // -------------------- Complete Problem Validation --------------------

    /**
     * Checks whether a complete decay problem is valid.
     * A valid problem must contain positive amounts, a positive
     * decay constant, a positive elapsed time, and a remaining
     * amount that is not greater than the initial amount.
     *
     * @param problem the DecayCalc object to validate
     * @return true if the object is non-null and all required values are valid
     */
    public static boolean isValidDecayProblem(DecayCalc problem) {

        // Make sure a DecayCalc object was provided.
        // A null object cannot be used for validation.
        if (problem == null) {
            return false;
        }

        // Check each value in the problem.
        // All conditions must be true for the complete
        // decay problem to be considered valid.
        return isValidAmount(problem.getInitialAmount())
                && isValidAmount(problem.getRemainingAmount())
                && isValidDecayConstant(problem.getDecayConstant())
                && isValidTime(problem.getElapsedTime())
                && problem.getRemainingAmount()
                <= problem.getInitialAmount();
    }
}