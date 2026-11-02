package edu.kirkwood.model.colby;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a triangle with three sides and three angles.
 * This class provides methods for calculating the missing sides
 * and angles of a triangle.
 */
public class Triangle implements Comparable<Triangle>{

    /**
     * Constant for the value an angle must be less than
     * Also acts as the sum of the three angles of a triangle
     */
    private static final double MAX_ANGLE_VALUE = 180.00;
    /**
     * Represents a value close to zero
     * This is used to help with floating point precision errors in the solveSSA() method
     */
    private static final double EPSILON = 1e-7;

    /**
     * Side a of the triangle
     */
    private double sideA;

    /**
     * Side b of the triangle
     */
    private double sideB;

    /**
     * Side c of the triangle
     */
    private double sideC;

    /**
     * Angle A of the triangle measured in degrees
     */
    private double angleA;

    /**
     * Angle B of the triangle measured in degrees
     */
    private double angleB;

    /**
     * Angle C of the triangle measured in degrees
     */
    private double angleC;

    /**
     * Default constructor for Triangle objects
     * Initializes a new Triangle to side lengths of 1 and angles of 60 degrees
     */
    public Triangle() {
        sideA = 1.0;
        sideB = 1.0;
        sideC = 1.0;
        angleA = 60.0;
        angleB = 60.0;
        angleC = 60.0;
    }

    /**
     * Constructs a Triangle with specified sides and angles
     *
     * @param sideA Side a of the Triangle
     * @param sideB Side b of the Triangle
     * @param sideC Side c of the Triangle
     * @param angleA Angle A of the Triangle
     * @param angleB Angle B of the Triangle
     * @param angleC Angle C of the Triangle
     */
    public Triangle(double sideA, double sideB, double sideC, double angleA, double angleB, double angleC){
        setSideA(sideA);
        setSideB(sideB);
        setSideC(sideC);
        setAngleA(angleA);
        setAngleB(angleB);
        setAngleC(angleC);
    }

    /**
     * Gets side a of the Triangle
     *
     * @return Side a
     */
    public double getSideA() {
        return sideA;
    }

    /**
     * Sets side a of the Triangle
     *
     * @param sideA Side a of the Triangle
     */
    public void setSideA(double sideA) throws IllegalArgumentException {
        if(sideA <= 0){
            throw new IllegalArgumentException("Side a must be greater than zero.");
        }
        this.sideA = sideA;
    }

    /**
     * Gets side b of the Triangle
     *
     * @return Side b
     */
    public double getSideB() {

        return sideB;
    }

    /**
     * Sets side b of the Triangle
     *
     * @param sideB Side b of the Triangle
     */
    public void setSideB(double sideB) throws IllegalArgumentException {
        if(sideB <= 0){
            throw new IllegalArgumentException("Side b must be greater than zero.");
        }
        this.sideB = sideB;
    }

    /**
     * Gets side c of the Triangle
     *
     * @return Side c
     */
    public double getSideC() {
        return sideC;
    }

    /**
     * Sets side c of the Triangle
     *
     * @param sideC Side c of the Triangle
     */
    public void setSideC(double sideC) throws IllegalArgumentException {
        if(sideC <= 0){
            throw new IllegalArgumentException("Side c must be greater than zero.");
        }
        this.sideC = sideC;
    }

    /**
     * Gets angle A of the Triangle
     *
     * @return Angle A
     */
    public double getAngleA() {
        return angleA;
    }

    /**
     * Sets angle A of the Triangle
     *
     * @param angleA Angle A of the Triangle
     */
    public void setAngleA(double angleA) throws IllegalArgumentException {
        if(angleA <= 0){
            throw new IllegalArgumentException("Angle A must be greater than zero.");
        }
        if(angleA >= MAX_ANGLE_VALUE){
            throw new IllegalArgumentException("Angle A must be less than 180 degrees.");
        }
        this.angleA = angleA;
    }

    /**
     * Gets angle B of the Triangle
     *
     * @return Angle B
     */
    public double getAngleB() {
        return angleB;
    }

    /**
     * Sets angle B of the Triangle
     *
     * @param angleB Angle B of the Triangle
     */
    public void setAngleB(double angleB) throws IllegalArgumentException {
        if(angleB <= 0){
            throw new IllegalArgumentException("Angle B must be greater than zero.");
        }
        if(angleB >= MAX_ANGLE_VALUE){
            throw new IllegalArgumentException("Angle B must be less than 180 degrees.");
        }
        this.angleB = angleB;
    }

    /**
     * Gets angle C of the Triangle
     *
     * @return Angle C
     */
    public double getAngleC() {
        return angleC;
    }

    /**
     * Sets angle C of the Triangle
     *
     * @param angleC Angle C of the Triangle
     */
    public void setAngleC(double angleC) throws IllegalArgumentException {
        if(angleC <= 0){
            throw new IllegalArgumentException("Angle C must be greater than zero.");
        }
        if(angleC >= MAX_ANGLE_VALUE){
            throw new IllegalArgumentException("Angle C must be less than 180 degrees.");
        }
        this.angleC = angleC;
    }

    /*
     I referenced w3schools.com for the Math.sin(), Math.cos(), Math.asin(), Math.acos(), Math.toRadians(),
     and Math.toDegrees() methods
     */


    /**
     * Solves Side-Side-Side(SSS) Triangle
     * Determines the three angles of the Triangle based on the three sides
     *
     * @param sideA Side a of the Triangle
     * @param sideB Side b of the Triangle
     * @param sideC Side c of the Triangle
     *
     * @return The Triangle with all its sides and angles
     */
    public Triangle solveSSS(double sideA, double sideB, double sideC){
        // I referenced this site for SSS calculations: https://www.mathsisfun.com/algebra/trig-solving-sss-triangles.html
        // Validate side lengths
        if(!isValidTriangle(sideA, sideB, sideC)){
            throw new IllegalArgumentException("The provided sides do not form a valid triangle. " +
                    "The sum of two sides must be greater than the third side.");
        }
        // Find angles using Law of Cosines
        double angleA = Math.acos((sideB * sideB + sideC * sideC - sideA * sideA) / (2 * sideB * sideC));
        double angleB = Math.acos((sideA * sideA + sideC * sideC - sideB * sideB) / (2 * sideA * sideC));
        double angleC = Math.acos((sideA * sideA + sideB * sideB - sideC * sideC) / (2 * sideA * sideB));
        // Convert radians to degrees (Java does angle calculations in Radians)
        angleA = Math.toDegrees(angleA);
        angleB = Math.toDegrees(angleB);
        angleC = Math.toDegrees(angleC);

        return new Triangle(sideA, sideB, sideC, angleA, angleB, angleC);
    }

    /**
     * Solves Side-Angle-Side(SAS) Triangle
     * Determines side c, angle A, and angle B of the Triangle based on the given inputs
     *
     * @param sideA Side a of the Triangle
     * @param sideB Side b of the Triangle
     * @param angleC Angle C of the Triangle
     *
     * @return The Triangle with all its sides and angles
     */
    public Triangle solveSAS(double sideA, double sideB, double angleC){
        // I referenced this site for SAS calculations: https://www.mathsisfun.com/algebra/trig-solving-sas-triangles.html
        // Convert angleC to Radians
        double angleCRadians = Math.toRadians(angleC);
        // Law of Cosines to find side c
        double sideC = Math.sqrt(sideA * sideA + sideB * sideB - 2 * sideA * sideB * Math.cos(angleCRadians));
        // Law of Cosines to find angle A
        double angleARadians = Math.acos((sideB * sideB + sideC * sideC - sideA * sideA) / (2 * sideB * sideC));
        // Convert angle A to Degrees
        double angleA = Math.toDegrees(angleARadians);
        // Triangle sum theorem to find angle B
        double angleB = MAX_ANGLE_VALUE - angleA - angleC;

        return new Triangle(sideA, sideB, sideC, angleA, angleB, angleC);
    }

    /**
     * Solves Side-Side-Angle(SSA) Triangle
     * Determines side c, angle B, and angle C of the Triangle based on the given inputs
     * This specific case may result in zero, one, or two Triangles
     *
     * @param sideA Side a of the Triangle
     * @param sideB Side b of the Triangle
     * @param angleA Angle A of the Triangle
     *
     * @return A List of Triangle objects with all its sides and angles
     */
    public List<Triangle> solveSSA(double sideA, double sideB, double angleA){
        // I referenced this site for SSA calculations: https://www.mathsisfun.com/algebra/trig-solving-ssa-triangles.html
        // Create list of Triangle objects to account for case of 0, 1, or 2 triangles
        List<Triangle> triangles = new ArrayList<>();
        // convert angle A to Radians
        double angleARadians = Math.toRadians(angleA);
        // calculate for sin(B) to determine number of triangles
        double sinB = (sideB * Math.sin(angleARadians)) / sideA;
        // if sin(B) > 1 there are no possible triangles
        /*
           I used an epsilon value for floating point precision errors
           An epsilon value is an arbitrarily small value
           1 + EPSILON represents a number like 1.000000001
           Source: Google Gemini
         */
        if(sinB > 1 + EPSILON || sinB < 0.0){
            return triangles; // empty list
        }
        /*
           After checking for sinB > 1, Math.clamp compares sin(B) to the max and min values of 0.0 and 1.0.
           If it is less than 0.0, it sets sinB to 0.0, and if it is greater than 1.0, it sets sinB to 1.0.
           I was having trouble with floating point precision errors specifically related to right triangles.
           If sin(B) is equal to a number around 1.000001 (right triangle) this will set the value of sin(B) = 1.0.
           I had help with Gemini for this solution
        */
        sinB = Math.clamp(sinB, 0.0, 1.0);
        // Calculate angle B using Law of Sines (this will be the acute angle)
        double angleB1Radians = Math.asin(sinB);
        double angleB1 = Math.toDegrees(angleB1Radians);
        // Calculate angle C using Triangle Sum Theorem
        double angleC1 = MAX_ANGLE_VALUE - angleA - angleB1;
        // Check that angle C is valid
        if(angleC1 > 0){
            // Law of Sines for side c
            double angleC1Radians = Math.toRadians(angleC1);
            double sideC1 = sideA*Math.sin(angleC1Radians) / Math.sin(angleARadians);
            triangles.add(new Triangle(sideA, sideB, sideC1, angleA, angleB1, angleC1));
        }
        /*
           Check for 2nd potential Triangle (obtuse angle B)
           This exists if sin(B) < 1 and sideA < sideB
        */
        if(sinB < (1.0 - EPSILON) && sideA < sideB){
            double angleB2 = MAX_ANGLE_VALUE - angleB1;
            double angleC2 = MAX_ANGLE_VALUE - angleA - angleB2;
            // Make sure angle C is valid
            if(angleC2 > 0){
                // Calculate side c using Law of Sines
                double angleC2Radians = Math.toRadians(angleC2);
                double sideC2 = sideA * Math.sin(angleC2Radians) / Math.sin(angleARadians);
                triangles.add(new Triangle(sideA, sideB, sideC2, angleA, angleB2, angleC2));
            }
        }
        return triangles;
    }

    /**
     * Solves Angle-Side-Angle(ASA) Triangle
     * Determines side a, side b, and angle C of the Triangle based on the given inputs
     *
     * @param angleA Angle A of the Triangle
     * @param sideC Side c of the Triangle
     * @param angleB Angle B of the Triangle
     *
     * @return The Triangle with all its sides and angles
     */
    public Triangle solveASA(double angleA, double sideC, double angleB){
        // Check angle sum is less than 180
        if(angleA + angleB >= MAX_ANGLE_VALUE){
            throw new IllegalArgumentException("The provided angle(s) and their sum must be less than 180 degrees.");
        }
        // I referenced this website for ASA calculations: https://www.mathsisfun.com/algebra/trig-solving-asa-triangles.html
        // Solve for angle C using Triangle Sum Theorem
        double angleC = MAX_ANGLE_VALUE - angleA - angleB;
        // Convert to Radians for side calculations
        double angleARadians = Math.toRadians(angleA);
        double angleBRadians = Math.toRadians(angleB);
        double angleCRadians = Math.toRadians(angleC);
        // Solve for side a using Law of Sines
        double sideA = Math.sin(angleARadians) * (sideC / Math.sin(angleCRadians));
        // Solve for side b using Law of Sines
        double sideB = Math.sin(angleBRadians) * (sideC / Math.sin(angleCRadians));

        return new Triangle(sideA, sideB, sideC, angleA, angleB, angleC);
    }

    /**
     * Solves Angle-Angle-Side(AAS) Triangle
     * Determines side b, side c, and angle C of the Triangle based on the given inputs
     *
     * @param angleA Angle A of the Triangle
     * @param angleB Angle B of the Triangle
     * @param sideA Side a of the Triangle
     *
     * @return The Triangle with all its sides and angles
     */
    public Triangle solveAAS(double angleA, double angleB, double sideA){
        // Check angle sum is less than 180
        if(angleA + angleB >= MAX_ANGLE_VALUE){
            throw new IllegalArgumentException("The provided angle(s) and their sum must be less than 180 degrees.");
        }
        // I referenced this website for AAS calculations: https://www.mathsisfun.com/algebra/trig-solving-aas-triangles.html
        // Solve for angle C using Triangle Sum Theorem
        double angleC = MAX_ANGLE_VALUE - angleA - angleB;
        // Convert to Radians for side calculations
        double angleARadians = Math.toRadians(angleA);
        double angleBRadians = Math.toRadians(angleB);
        double angleCRadians = Math.toRadians(angleC);
        // Solve for side B using Law of Sines
        double sideB = Math.sin(angleBRadians) * (sideA / Math.sin(angleARadians));
        // Solve for side C using Law of Sines
        double sideC = Math.sin(angleCRadians) * (sideA / Math.sin(angleARadians));

        return new Triangle(sideA, sideB, sideC, angleA, angleB, angleC);
    }

    /**
     * Checks that no side is greater than the sum of the other two sides
     *
     * @param sideA Side a of the Triangle
     * @param sideB Side b of the Triangle
     * @param sideC Side c of the Triangle
     *
     * @return True if it's a valid Triangle, False if not
     */
    public boolean isValidTriangle(double sideA, double sideB, double sideC){
        boolean isValid = (sideA + sideB > sideC) &&
                            (sideA + sideC > sideB) &&
                            (sideB + sideC > sideA);
        return isValid;
    }

    /**
     * Determines the perimeter of a given Triangle
     *
     * @return The perimeter of the given Triangle
     */
    public double calculatePerimeter(){
        double perimeter = sideA + sideB + sideC;
        return perimeter;
    }

    /**
     * Determines the area of a given Triangle
     *
     * @return The area of the given Triangle
     */
    public double calculateArea(){
        // Calculate using Heron's formula: https://www.mathsisfun.com/geometry/herons-formula.html
        double semiperimeter = calculatePerimeter() / 2;
        double area = Math.sqrt(semiperimeter * (semiperimeter - sideA)
                * (semiperimeter - sideB) * (semiperimeter -sideC));
        return area;
    }

    /**
     * Returns a string representation of the sides and angles of a Triangle
     *
     * @return a string representation of a Triangle
     */
    @Override
    public String toString() {
        return String.format("Sides: a=%.2f, b=%.2f, c=%.2f\nAngles: A=%.2f, B=%.2f, C=%.2f",
                sideA, sideB, sideC, angleA, angleB, angleC);
    }

    /**
     * Determines if two Triangle objects are the same
     *
     * @param o the reference object with which to compare.
     *
     * @return a boolean true if both objects are the same, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return sideA == triangle.sideA &&
                sideB == triangle.sideB &&
                sideC == triangle.sideC &&
                angleA == triangle.angleA &&
                angleB == triangle.angleB &&
                angleC == triangle.angleC;
     }

    /**
     * Compares the area of two Triangle objects
     *
     * @param o the object to be compared.
     *
     * @return a negative integer, zero, or a positive integer.
     */
    @Override
    public int compareTo(Triangle o) {
        double thisArea = this.calculateArea();
        double otherArea = o.calculateArea();

        return Double.compare(thisArea, otherArea);
    }
}
