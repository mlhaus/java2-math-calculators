package edu.kirkwood.controller.marc;

import edu.kirkwood.model.Fraction;

import static edu.kirkwood.view.Messages.fractionGoodbye;
import static edu.kirkwood.view.Messages.fractionGreet;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getString;

public class FractionCalculator {
    public static final String INVALID_FORMAT_MSG = "Invalid format. Ensure operator (+, -, *, /) has a space on both sides.";
    public static final String INVALID_FIRST_FRACTION = "Invalid first fraction";
    public static final String INVALID_SECOND_FRACTION = "Invalid second fraction";
    public static final String INVALID_FRACTION = "Invalid fraction format";

    public static void start() {
        fractionGreet();
        // Get input
        while(true) {
            String value = getString("Enter your equation (or 'q' to quit)");
            if(value.equalsIgnoreCase("q") || value.equalsIgnoreCase("quit")) {
                break;
            }

        }
        // TODO: Validate input
        // TODO: Perform mathematical operations
        // TODO: Display output
        fractionGoodbye();
        pressEnterToContinue();
    }


    /**
     * Splits the user input string into three parts: first fraction, operator, and second fraction.
     *
     * @param input the raw input string from the user.
     * @return a String array of size 3.
     * @throws IllegalArgumentException if the input format or operator is invalid.
     */
    public static String[] splitCalculation(String input) throws IllegalArgumentException {
        // Find the operator
        String operator = "";
        int operatorIndex = -1;

        if(input.contains(" + ")) {
            operator = "+";
            operatorIndex = input.indexOf(" + ");
        } else if(input.contains(" - ")) {
            operator = "-";
            operatorIndex = input.indexOf(" - ");
        } else if(input.contains(" * ")) {
            operator = "*";
            operatorIndex = input.indexOf(" * ");
        } else if(input.contains(" / ")) {
            operator = "/";
            operatorIndex = input.indexOf(" / ");
        }
        if(operator.equals("")) {
            throw new IllegalArgumentException(INVALID_FORMAT_MSG);
        }
        // I have a valid mathematical operator!
        // Does first fraction exist?
        String fractionStr1 = input.substring(0, operatorIndex).trim();
        if(fractionStr1.isEmpty()) {
            throw new IllegalArgumentException(INVALID_FIRST_FRACTION);
        }
        // Does second fraction exist?
        String fractionStr2 = input.substring(operatorIndex + 3).trim();
        if(fractionStr2.isEmpty()) {
            throw new IllegalArgumentException(INVALID_SECOND_FRACTION);
        }
        // All three values are acceptable
        return new String[]{fractionStr1, operator, fractionStr2};
    }

    /**
     * Parse a string into a Fraction object. Handles whole numbers, proper/improper fractions, mixed fractions.
     * @param str the string to parse
     * @return a Fraction object representing the parsed string
     * @throws NumberFormatException If the numerator or denominator can not valid integers
     * @throws IllegalArgumentException If the fraction format is not valid
     */
    public static Fraction parseFraction(String str) throws NumberFormatException, IllegalArgumentException {
        // Check mixed fractions
        if(str.contains(" ")) {
            // Break str into two parts (whole number and fraction)
            String[] parts = str.split(" ", 2);
            // Validate the whole number
            int whole = 0;
            try {
                whole = Integer.parseInt(parts[0]);
            } catch(NumberFormatException e) {
                throw new NumberFormatException("Invalid whole number");
            }
            // Break the fraction into two parts
            String[] parts2 = parts[1].split("/", 2); // "1/2" => {"1", "2"}
            // Validate the numerator
            int numerator = 0;
            try {
                numerator = Integer.parseInt(parts2[0]);
            } catch(NumberFormatException e) {
                throw new NumberFormatException("Invalid numerator");
            }
            // Validate the denominator
            int denominator = 0;
            try {
                denominator = Integer.parseInt(parts2[1]);
            } catch(NumberFormatException e) {
                throw new NumberFormatException("Invalid denominator");
            }
            // No validation errors
            if(whole >= 0) { // Calculates positive fraction
                numerator = whole * denominator + numerator;
            } else { // Calculates negative fraction
                numerator = whole * denominator - numerator;
            }
            Fraction result = new Fraction(numerator, denominator);
            return result;
        }
        // Check proper/improper fractions, doesn't contain space, but it does contain a slash
        else if(str.contains("/")) {
            // Split the fraction into numerator and denominator parts
            String[] parts = str.split("/");
            int num = 0;
            int den = 0;
            // Validate the numerator
            try {
                num = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                // This will be thrown if the numerator is not a number
                throw new NumberFormatException("Invalid numerator: '" + parts[0] + "'");
            }
            // Validate the denominator
            try {
                den = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                // This will be thrown if the denominator is not a number
                throw new NumberFormatException("Invalid denominator: '" + parts[1] + "'");
            }

            Fraction result = null;
            try {
                result = new Fraction(num, den);
            } catch (ArithmeticException e) {
                // This will be thrown if the denominator is zero
                throw new ArithmeticException(e.getMessage() + ": '" + str + "'");
            }
            return result;
        }
        // Check whole numbers

        return null;
    }
}
