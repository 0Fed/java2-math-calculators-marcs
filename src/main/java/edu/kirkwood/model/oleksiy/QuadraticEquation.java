package edu.kirkwood.model.oleksiy;

import edu.kirkwood.model.marc.Fraction;

import java.text.DecimalFormat;

/**
 * Represents a Quadratic Equation split into three sections. [ax^2] + [bx] + [c].
 */
public class QuadraticEquation {
    private double a;
    private double b;
    private double c;

    /**
     * Default constructor, sets all inputs as 1.
     */
    public QuadraticEquation(){
        a = 1;
        b = 1;
        c = 1;
    }

    /**
     * Constructor for building a Quadratic Equation
     *
     * @param a is setting section A of a quadratic equation. eg: [ax^2]+bx+c
     * @param b is setting section B of a quadratic equation. eg: ax^2[+bx]+c
     * @param c is setting section C of a quadratic equation. eg ax^2+bx[+c]
     */
    public QuadraticEquation(double a, double b, double c){
        setSectionA(a);
        setSectionB(b);
        setSectionC(c);
    }

    /**
     * A getter for section A of a quadratic equation.
     *
     * @return section A of a quadratic equation.
     */
    public double getSectionA() {
        return a;
    }

    /**
     * A setter for section A of a quadratic equation.
     *
     * @param a is the new A section for the QuadraticEquation Object
     */
    public void setSectionA(double a) {
        if(a == 0){
            throw new IllegalArgumentException("You cannot have section A as a zero. This will make the equation linear, instead of quadratic.");
        }

        this.a = a;
    }

    /**
     * A getter for section B of a quadratic equation.
     *
     * @return section B of a quadratic equation.
     */
    public double getSectionB() {
        return b;
    }

    /**
     * A setter for section B of a quadratic equation.
     *
     * @param b is the new B section for the QuadraticEquation Object
     */
    public void setSectionB(double b) {
        this.b = b;
    }

    /**
     * A getter for section C of a quadratic equation.
     *
     * @return section C of a quadratic equation.
     */
    public double getSectionC() {
        return c;
    }

    /**
     * A setter for section C of a quadratic equation.
     *
     * @param c is the new C section for the QuadraticEquation Object
     */
    public void setSectionC(double c) {
        this.c = c;
    }

    /**
     * Converts whole numbers from the decimal whole numb, "#.0", to regular whole num, "#", as a String
     *
     * @param wholeDecimal is the decimal that you want converted
     * @return the whole number without a decimal point and a 0
     */
    public static String convertWholeDecimalsToWholeNumbers(String wholeDecimal){
        int decimalPlaces = 0;
        String result = wholeDecimal;

        if(wholeDecimal.contains(".")){
            decimalPlaces = wholeDecimal.substring(wholeDecimal.indexOf(".")+1).length();
        }

        if(decimalPlaces == 1){
            result = wholeDecimal.substring(0, wholeDecimal.indexOf("."));
        }

        return result;
    }

    /**
     * Takes in a decimal and returns a fraction as a String
     *
     * @param decimal the decimal that's needed to be converted
     * @return a fraction as a String
     */
    public static String decimalToFraction(double decimal){
        // https://www.baeldung.com/java-decimal-fraction-conversion
        // Above is where I mostly took the idea from.
        int num = 0;
        int denom = 0;
        int decimalPlaces = 0;
        String result = "";
        DecimalFormat extendedDecimalFormat = new DecimalFormat("#0.0000");

        String string = String.valueOf(decimal);
        String[] parts = string.split("\\.", 2); // "1.25 -> "1", "25"
        if(parts[1].equals("0")){ // Check if there's only a whole number and return it if there is.
            result = parts[0];
            return result;
        }

        decimalPlaces = parts[1].length();

        if(decimalPlaces >= 10){ // bandaid fix for infinitely repeating numbers
            result = extendedDecimalFormat.format(decimal);
            return result;
        }

        denom = (int) Math.pow(10, decimalPlaces);
        num = (int) (decimal * denom);
        int gcd = Fraction.gcd(num, denom); // I'm not rewriting something that's already available to me within the math calculators project. If need be, I can copy-paste that function here for use.
        num = num / gcd;
        denom = denom / gcd;

        result = num + "/" + denom;

        return result;
    }

    /**
     * Finds the y-intercept of the quadratic equation
     *
     * @return the y-intercept as a String
     */
    public String findYIntercept(){
        String result = "(0, 0)";
        double decimal = this.getSectionC();
        String convertedDecimal = QuadraticEquation.decimalToFraction(decimal);


        result = "(0, " + convertedDecimal + ")";

        return result;
    }

    /**
     * Finds the vertex of the quadratic equation
     *
     * @return the vertex coordinates as an Array of Strings
     */
    public String[] findVertex(){
        double xCoord = 0;
        double yCoord = 0;
        String[] result = {"0", "0"};

        double vertexFormPart1 = this.getSectionB() * -1;
        double vertexFormPart2 = this.getSectionA() * 2;
        xCoord = vertexFormPart1/vertexFormPart2;

        double quadraticEquationPart1 = Math.pow(xCoord, 2)*this.getSectionA();
        double quadraticEquationPart2 = this.getSectionB()*xCoord;
        yCoord = quadraticEquationPart1+quadraticEquationPart2+this.getSectionC();

        result[0] = QuadraticEquation.decimalToFraction(xCoord);
        result[1] = QuadraticEquation.decimalToFraction(yCoord);

        return result;
    }

    /**
     * Converts the Quadratic Equation from Standard Form to Vertex Form
     *
     * @param vertex the vertex of the Quadratic Equation
     * @return the Quadratic Equation in Vertex Form as a String
     */
    public String convertToVertexForm(String[] vertex){
        String result = "";
        String firstPart = vertex[0];
        String secondPart = vertex[1];
        String sectionA = QuadraticEquation.convertWholeDecimalsToWholeNumbers(String.valueOf(QuadraticEquation.decimalToFraction(this.getSectionA())));
        double tempNum = 0;

        if(sectionA.equals("1")){
            sectionA = "";
        }

        if(firstPart.contains("-")){ // if the first part is not a negative put the expected symbol for vertex form eg. a(x-h) + k
            tempNum = Math.abs(Double.parseDouble(firstPart));
            firstPart = "+" + tempNum;

        }
        else {
            firstPart = "-" + firstPart;
        }

        if(!secondPart.contains("-")){ // same as above but for the second part
            secondPart = "+" + secondPart;
        }

        //result = sectionA + "(x" + firstPart + ")" + secondPart;
        result = sectionA + "(x" + QuadraticEquation.convertWholeDecimalsToWholeNumbers(firstPart) + ")" + QuadraticEquation.convertWholeDecimalsToWholeNumbers(secondPart);

        return result;
    }

    public String[] findRoots() throws IllegalArgumentException{
        String[] result = {"", ""};
        double sectionA = this.getSectionA();
        double sectionB = this.getSectionB();
        double sectionC = this.getSectionC();

        double discriminant = Math.pow(sectionB, 2)-4*sectionA*sectionC;

        if(discriminant < 0){
            throw new IllegalArgumentException("This equation contains complex roots.");
        }

        double posNum = -b + Math.sqrt(discriminant);
        double negNum = -b - Math.sqrt(discriminant);
        double denom = 2 * sectionA;

        String root1 = QuadraticEquation.convertWholeDecimalsToWholeNumbers(String.valueOf(QuadraticEquation.decimalToFraction(negNum/denom)));
        String root2 = QuadraticEquation.convertWholeDecimalsToWholeNumbers(String.valueOf(QuadraticEquation.decimalToFraction(posNum/denom)));

        if(root1.equals(root2)){
            result[0] = root1;
        }
        else {
            result[0] = root1;
            result[1] = root2;
        }

        return result;
    }

    /**
     * Converts the Quadratic Equation to Slope-Intercept Form
     *
     * @param roots the roots of the Quadratic Equation
     * @param aSection the A section of the Quadratic Equation
     * @return the Quadratic Equation in Slope-Intercept Form as a String
     */
    public String convertToSlopeInterceptForm(String[] roots, double aSection){
        String result = "";
        String root1 = "";
        String root2 = "";
        String aSectionAsString = QuadraticEquation.decimalToFraction(aSection);
        double tempNum = 0;

        if(aSectionAsString.equals("1")){
            aSectionAsString = "";
        }

        aSectionAsString = QuadraticEquation.convertWholeDecimalsToWholeNumbers(String.valueOf(aSectionAsString));
        root1 = roots[0];
        root2 = roots[1];


        if(root1.contains("-")){ // if the first part is not a negative put the expected symbol for vertex form eg. a(x-h) + k
            tempNum = Math.abs(Double.parseDouble(root1));
            root1 = "+" + QuadraticEquation.convertWholeDecimalsToWholeNumbers(String.valueOf(QuadraticEquation.decimalToFraction(tempNum)));

        }
        else {
            root1 = "-" + root1;
        }

        if(root2.contains("-")){ // same as above but for the second part
            tempNum = Math.abs(Double.parseDouble(root2));
            root2 = "+" + QuadraticEquation.convertWholeDecimalsToWholeNumbers(String.valueOf(QuadraticEquation.decimalToFraction(tempNum)));
        }
        else {
            root2 = "-" + root2;
        }

        result = aSectionAsString + "(x" + root1 + ")(x" + root2 + ")";

        return result;
    }
}
