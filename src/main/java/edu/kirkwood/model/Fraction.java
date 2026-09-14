package edu.kirkwood.model;


import java.util.Objects;

/**
 * Represents a fraction with an integer numerator and denominator.
 * This class provides methods for fraction arithmetic, simplification,
 * and comparison.
 */
public class Fraction implements Comparable<Fraction> {
    private int numerator;
    private int denominator;

    /**
     * Default constructor
     * Intializes a new fraction to 1/1
     */
    public Fraction() {
        numerator = 1;
        denominator = 1;
    }

    /**
     * Constructs a fraction with a specified numerator and denominator
     * @param numerator the top portion of the fraction
     * @param denominator the bottom portion of the fraction
     */
    public Fraction(int numerator, int denominator) {
        setNumerator(numerator);
        setDenominator(denominator);
    }

    /**
     * Returns this fraction as a numerator and denominator separated by a slash.
     *
     * @return the string representation of this fraction
     */
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    /**
     * Gets the numerator of this fraction.
     *
     * @return the numerator
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Sets the numerator of this fraction.
     *
     * @param numerator the new numerator
     */
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    /**
     * Gets the denominator of this fraction.
     *
     * @return the denominator
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Sets the denominator of this fraction.
     *
     * @param denominator the new denominator
     * @throws ArithmeticException if the denominator is zero
     */
    public void setDenominator(int denominator) throws ArithmeticException {
        if(denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero.");
        }
        if(denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }
        this.denominator = denominator;
    }

    /**
     * Compares this fraction with another fraction.
     *
     * @param o the fraction to compare with this fraction
     * @return a negative integer, zero, or a positive integer when this fraction
     *         is less than, equal to, or greater than {@code o}, respectively
     */
    @Override
    public int compareTo(Fraction o) {
        return 0;
    }

    /**
     * Determines if two fraction objects are the same
     *
     * @param o the other object to be compared.
     * @return a boolean true is both objects are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    /**
     * This method is supported for the benefit of hash tables such as HashMap and HashSet.
     *
     * @return an integer representing a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }


    /**
     * Calculates the greatest common divisor of two integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the greatest common divisor of {@code a} and {@code b}
     */
    public static int gcd(int a, int b) {
        // Source: https://stackoverflow.com/a/30693436
        if (b == 0) {
            return Math.abs(a);
        }
        return gcd(b, a % b);
    }

    /**
     * Calculates the least common multiple of two integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the least common multiple of {@code a} and {@code b}
     */
    public static int lcm(int a, int b) {
        // Source: https://stackoverflow.com/a/4202114
        if(a == 0 || b == 0) {
            return 0;
        }
        return a * (b / gcd(a, b));
    }

    /**
     * Reduces this fraction to its simplest equivalent form.
     */
    public void simplify() {
        int g = gcd(this.numerator, this.denominator);
        setNumerator(this.numerator/g);
        setDenominator(this.denominator/g);
    }

    /**
     * Returns this fraction in mixed-number form.
     *
     * @return the mixed-number representation of this fraction
     */
    public String toMixedNumber(){
        String result = "";
        double numerator = this.numerator;
        double denominator = this.denominator;

        int remainder = Math.abs((int)numerator % (int)denominator);
        int wholeNumber = (int)Math.floor(numerator/denominator);
        Fraction remainingFraction = new Fraction(remainder, (int)denominator);
        remainingFraction.simplify();

        if(remainder != 0){
            result = wholeNumber+" "+remainingFraction.toString();
        }
        else{
            result = Integer.toString(wholeNumber);
        }
        return result;
    }

    /**
     * Adds another fraction to this fraction.
     *
     * @param other the fraction to add
     * @return the sum of this fraction and {@code other}
     */
    public Fraction add(Fraction other) {
        int newNumerator = this.numerator * other.denominator + this.denominator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        Fraction result = new Fraction(newNumerator, newDenominator);
        result.simplify();
        return result;
    }

    /**
     * Subtracts another fraction from this fraction.
     *
     * @param other the fraction to subtract
     * @return the difference between this fraction and {@code other}
     */
    public Fraction subtract(Fraction other) {
        int newNumerator = this.numerator * other.denominator - this.denominator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        Fraction result = new Fraction(newNumerator, newDenominator);
        result.simplify();
        return result;
    }

    /**
     * Multiplies this fraction by another fraction.
     *
     * @param other the fraction to multiply by
     * @return the product of this fraction and {@code other}
     */
    public Fraction multiply(Fraction other) {
        int newNumerator = this.numerator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        Fraction result = new Fraction(newNumerator, newDenominator);
        result.simplify();
        return result;
    }

    /**
     * Divides this fraction by another fraction.
     *
     * @param otherFraction the fraction to divide by
     * @return the quotient of this fraction divided by {@code other}
     */
    public Fraction divide(Fraction otherFraction){
        Fraction newFraction = null;
        try {
            int newNumerator = numerator * otherFraction.getDenominator();
            int newDenominator = denominator * otherFraction.getNumerator();
            newFraction = new Fraction(newNumerator,newDenominator);
            newFraction.simplify();
        } catch (ArithmeticException e) {
            throw e;
        }
        return newFraction;
    }
}
