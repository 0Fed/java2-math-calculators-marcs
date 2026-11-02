package edu.kirkwood.model.leesonj;

import edu.kirkwood.model.marc.Fraction;

import java.util.Arrays;
import java.util.Random;

/**
 * Represents a pool of dice.
 * The initial form of this class provides three methods to operate on the odds of the dice pool:
 * 1) A calculation that will return the chance of rolling dice and getting the target sum.
 * 2) An evaluation which will go through all possible combinations of the dice and return a Fraction representing
 *      the number of combinations that sums to the target number over the total number of combinations.
 * 3) A simulation which will iterate over a given number of attempts, generating a pseudorandom number for each dice,
 *      then return a Fraction representing the total iterations that the sum equaled the target number over the
 *      total number of attempts.
 */
public class DiePool {
    public static final int MAX_COMBINATIONS = 100000000;

    private int dice;
    private int sides;

    /**
     * The default constructor. Will create a die pool of one six-sided die.
     */
    public DiePool() {
        dice = 1;
        sides = 6;
    }

    /**
     * Constructs a die pool with a number of dice, each having a number of sides.
     * @param dice the number of dice
     * @param sides the number of sides on each die
     */
    public DiePool(int dice, int sides) {
        setDice(dice);
        setSides(sides);
    }

    /**
     * Gets the number of dice in the die pool
     * @return the number of dice
     */
    public int getDice() {
        return dice;
    }

    /**
     * Sets the number of dice in the die pool.
     * @param dice the number of dice
     * @throws IllegalArgumentException if dice less than or equal to 0
     */
    public void setDice(int dice) throws IllegalArgumentException {
        if (dice <= 0) {
            throw new IllegalArgumentException("A die pool must have dice");
        }
        this.dice = dice;
    }

    /**
     * Gets the number of sides for each dice in the die pool.
     * @return the number of sides
     */
    public int getSides() {
        return sides;
    }

    /**
     * Sets the number of sides for each dice in the die pool.
     * @param sides the number of sides
     * @throws IllegalArgumentException if sides less than or equal to 0
     */
    public void setSides(int sides) throws IllegalArgumentException {
        if (sides <=0) {
            throw new IllegalArgumentException("Can't have dice without sides");
        }
        this.sides = sides;
    }

    /**
     * Calculate the odds of achieving a particular result exactly when rolling the dice in the pool.
     * @param target The target number to be rolled.
     * @return The odds of rolling the target number exactly.
     */
    public double calculateTargetOdds(int target) {
        // Check if target is out of range
        if (target < dice || target > dice * sides) {
            return 0.0;
        }

        // Code Source: Adapted from code sourced from Gemini
        long favorableOutcomes = 0;
        // 2. Determine the upper limit for the summation loop (the floor function)
        int maxI = (target - dice) / sides;

        // 3. Apply the combinatorics summation formula
        for (int i = 0; i <= maxI; i++) {
            long sign = (i % 2 == 0) ? 1 : -1;
            long nChooseI = binomialCoefficient(dice, i);
            long remainderChooseNMinus1 = binomialCoefficient(target - sides * i - 1, dice - 1);

            favorableOutcomes += sign * nChooseI * remainderChooseNMinus1;
        }

        // 4. Divide by total possible outcomes (s^n)
        double totalOutcomes = Math.pow(sides, dice);
        // Note: For now, I did not use the Helpers rounding method as that returns a String and I may need to do
        //      further modification on this number prior to display.
        return Math.round((favorableOutcomes/totalOutcomes)* 100000.0) / 100000.0;

    }

    /**
     * Helper method to calculate binomial coefficients (n choose k) efficiently.
     * Source: Gemini
     */
    private static long binomialCoefficient(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;

        // Optimize by taking advantage of symmetry
        if (k > n / 2) {
            k = n - k;
        }

        long result = 1;
        for (int i = 1; i <= k; i++) {
            result = result * (n - k + i) / i;
        }
        return result;
    }


    /**
     * Generate each possible result achievable by the defined die pool and evaluate each against a particular target.
     * The result will be a Fraction representing the number of dice combinations that hit the target over the total
     * number of combinations. Die pool sizes generating more than MAX_COMBINATIONS will be rejected.
     * @param target The target number to be rolled.
     * @return A Fraction representing numberOfSuccessfulCombinations/totalCombinations
     * @throws ArithmeticException if sides ^ dice > Integer.MAX_VALUE or if combinations exceed MAX_COMBINATIONS
     */
    public Fraction evaluateTargetOdds(int target) throws ArithmeticException {
        // Check if target is out of range
        if (target < dice || target > dice * sides) {
            return new Fraction(0,1);
        }
        double val = Math.pow(Integer.MAX_VALUE, (double)1/dice);
        if (val  <  sides) {
            throw new ArithmeticException("Combinations exceed the ability to represent");
        }

        if (Math.pow(sides, dice) >= MAX_COMBINATIONS) {
            throw new ArithmeticException("Evaluating combinations will take too long.");
        }

        int[] combination = new int[dice];

        // set starting combination
        for (int j = 0; j < dice; j++) {
            combination[j] = 1;
        }

        int totalTargets = 0;
        int totalCombinations = 0;

        while (combination != null) {
            if (testTarget(combination, target)) {
                totalTargets ++;
            }
            totalCombinations ++;
            combination = nextCombination(combination);
        }
        return new Fraction(totalTargets, totalCombinations);
        }

    /**
     * Simulate the dice pool with pseudo-random values a number of times, testing if each results in the target value.
     * @param target The target number to be rolled.
     * @param attempts The number of times to roll the dice pool before reporting the results.
     * @param seed The optional random number seed to allow for repeatable results.
     * @return A fraction representing numberOfSuccessfulRolls/totalAttempts
     * @throws IllegalArgumentException if attempting to simulate more than MAX_COMBINATIONS rolls.
     */
    public Fraction simulateTargetOdds(int target, int attempts, long seed) throws IllegalArgumentException {
        if (attempts > MAX_COMBINATIONS) {
            throw new IllegalArgumentException("Too many simulations.");
        }
        Random rand = new Random();
        rand.setSeed(seed);
        int totalTargets = 0;
        for (int i = 0; i < attempts; i++) {
            int[] combination = new int[dice];
            for (int j = 0; j < dice; j++) {
                combination[j] = rand.nextInt(sides) + 1;
            }
            if (testTarget(combination, target)) {
                totalTargets++;
            }
        }
        return new Fraction(totalTargets, attempts);
    }

    /**
     * Simulate the dice pool with pseudo-random values a number of times, testing if each results in the target value.
     * @param target The target number to be rolled.
     * @param attempts The number of times to roll the dice pool before reporting the results.
     * @return A fraction representing numberOfSuccessfulRolls/totalAttempts
     */
    public Fraction simulateTargetOdds(int target, int attempts) {
        return simulateTargetOdds(target, attempts, System.nanoTime());
    }

    /**
     * Increment the dice combination to the next value.
     * @param diceCombination The current dice combination.
     * @return The next possible combination of dice in the pool or null if there are no more combinations.
     */
    public int[] nextCombination(int[] diceCombination) {
        int[] returnVal = Arrays.copyOf(diceCombination, diceCombination.length);

        int index = dice - 1;
        while (index >= 0) {
            if (diceCombination[index] < sides) {
                returnVal[index]++;
                return returnVal;
            } else {
                returnVal[index] = 1;
                index--;
            }
        }
        return null;
    }

    /**
     * Check whether the dice combination achieves the target.
     * @param combination The die combination to be tested.
     * @param target The target number to check.
     * @return Whether the target was achieved.
     */
    public boolean testTarget(int[] combination, int target) {
        int sum = 0;
        for (int j : combination) {
            sum += j;
        }
        return sum == target;
    }
}
