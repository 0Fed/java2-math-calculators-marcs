package edu.kirkwood.model.jocelyn;

import java.util.Objects;

/**
 * Calculates user BMI based on height and weight.
 * Asks user fitness goal and activity level and offers caloric goal to reach fitness goal
 */
public class HealthCalculator implements Comparable<HealthCalculator>{

    private int age;
    private double height;
    private double weight;
    private int activityLevel;
    private int fitnessGoal;
    public static final String INVALID_INPUT = "You must enter a number greater than 0.";

    /**
     * Constructor
     * @param age the user's age in years
     * @param height the user's height in inches
     * @param weight the user's weight in pounds
     * @param activityLevel the user's activityLevel from 1 (Sedentary) to 5 (Very Active)
     * @param fitnessGoal the user's fitnessGoal from 1 (Lose) to 3 (Gain)
     */
    public HealthCalculator(int age, double height, double weight,
                            int activityLevel, int fitnessGoal) {
        setAge(age);
        setHeight(height);
        setWeight(weight);
        setActivityLevel(activityLevel);
        setFitnessGoal(fitnessGoal);
    }

    /**
     * Gets user's age
     * @return user's age in years
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets user's height
     * @return user's height in inches
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets user's weight
     * @return user's height in pounds
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Gets user's activity level from 1 (Sedentary) to 5 (Very Active)
     * @return user's activity level
     */
    public int getActivityLevel() {
        return activityLevel;
    }

    /**
     * Gets user's Fitness goal from 1 (lose) to 3 (gain)
     * @return user's fitness goal
     */
    public int getFitnessGoal() {return fitnessGoal;}

    /**
     * Sets the user's age
     * @param age the user's age in years
     * @throws IllegalArgumentException if age is zero or negative
     */
    public void setAge(int age) {
        if (age <= 0) {
           throw new IllegalArgumentException(INVALID_INPUT);
        }
        this.age = age;
    }

    /**
     * Sets the user's height
     * @param height the user's height in inches
     * @throws IllegalArgumentException if input is equal or below zero or
     *          an infinity value or not a number value is entered
     */
    public void setHeight(double height) {
        if (!Double.isFinite(height) || height <= 0) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        this.height = height;
    }

    /**
     * Sets the user's weight
     * @param weight the user's weight
     * @throws IllegalArgumentException if input is equal or below zero or
     *          an infinity value or not a number value is entered
     */
    public void setWeight(double weight) {
        if (!Double.isFinite(weight) || weight <= 0) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        this.weight = weight;
    }

    /**
     * Sets the user's activity level
     * @param activityLevel the user's activity level
     * @throws IllegalArgumentException if input is not an integer from 1-5
     */
    public void setActivityLevel(int activityLevel) {
        if (activityLevel < 1 || activityLevel > 5) {
            throw new IllegalArgumentException("Activity level must be between 1 and 5");
        }
        this.activityLevel = activityLevel;
    }

    /**
     * Sets the user's fitness goal
     * @param fitnessGoal the user's fitness goal
     * @throws IllegalArgumentException if input is not an integer from 1-3
     */
    public void setFitnessGoal(int fitnessGoal) {
        if (fitnessGoal < 1 || fitnessGoal > 3) {
            throw new IllegalArgumentException("Fitness goal must be between 1 and 3");
        }
        this.fitnessGoal = fitnessGoal;
    }

    @Override
    public String toString() {
        return "HealthCalculator{" +
                "age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", activityLevel=" + activityLevel +
                ", fitnessGoal=" + fitnessGoal +
                '}';
    }

    /**
     * Calculates user's BMI
     * @return calculated BMI
     */
    public double calculateBMI() {
        return (weight * 703) / (height * height);
    }

    /**
     * Calculates user's BMI category
     * @return BMI category
     */
    public String determineBMICategory() {
        double bmi = calculateBMI();

        if (bmi < 18.5){
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    /**
     * Calculates user's basal metabolic rate
     *      (number of calories body burns to maintain essential functions)
     * @return calculated basal metabolic rate
     */
    public double calculateBMR() {
        double weightKG = weight / 2.20462;
        return weightKG * 24;
    }

    /**
     * Calculates user's total daily energy expenditure
     * @return the calculated TDEE
     */
    public double calculateTDEE() {
        double multiplier;

        if (activityLevel == 1) {
            multiplier = 1.2;
        } else if (activityLevel == 2) {
            multiplier = 1.375;
        } else if (activityLevel == 3) {
            multiplier = 1.55;
        } else if (activityLevel == 4) {
            multiplier = 1.725;
        } else if (activityLevel == 5) {
            multiplier = 1.9;
        } else {
            throw new IllegalArgumentException("Invalid activity level");
        }

        return calculateBMR() * multiplier;
    }

    /**
     * Calculates recommended daily caloric target
     * @return recommended daily calorie target
     */
    public double calculateCalorieTarget() {
        double tdee = calculateTDEE();

        if (fitnessGoal == 1) {
            return tdee - 500;
        } else if (fitnessGoal == 2) {
            return tdee;
        } else if (fitnessGoal == 3) {
            return tdee + 500;
        } else {
            throw new IllegalArgumentException("Invalid fitness goal");
        }
    }

    /**
     * Calculates minimum healthy weight for user's height
     * @return minimum healthy body weight
     */
    public double calculateHealthyWeightMinimum() {
        return (18.5 * height * height) / 703;
    }

    /**
     * Calculates the maximum healthy weight for user's height
     * @return maximum healthy weight
     */
    public double calculateHealthyWeightMaximum() {
        return (24.9 * height * height) / 703;
    }

    /**
     * Compares two HealthCalculator objects
     * @param other HealthCalculator object to compare
     * @return negative integer, zero, or a positive integer depending on the comparison
     */
    @Override
    public int compareTo(HealthCalculator other) {
        int result = Integer.compare(age, other.age);
        if (result != 0) return result;

        result = Double.compare(height, other.height);
        if (result != 0) return result;

        result = Double.compare(weight, other.weight);
        if (result != 0) return result;

        result = Integer.compare(activityLevel, other.activityLevel);
        if (result != 0) return result;

        return Integer.compare(fitnessGoal, other.fitnessGoal);
    }

    /**
     * Checks whether two HealthCalculator objects have the same attribute values.
     * @param obj the object to compare
     * @return true if the objects have the same values, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if (!(obj instanceof HealthCalculator)) {
            return false;
        }

        HealthCalculator other = (HealthCalculator) obj;

        return age == other.age
                && Double.compare(height, other.height) == 0
                && Double.compare(weight, other.weight) == 0
                && activityLevel == other.activityLevel
                && fitnessGoal == other.fitnessGoal;
    }

    /**
     * Generates hashcode using object's attributes
     * @return the object's hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(age, height, weight,
                activityLevel, fitnessGoal);
    }
}
