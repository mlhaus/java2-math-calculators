package edu.kirkwood.controller.marc;

import edu.kirkwood.model.Fraction;

import static edu.kirkwood.view.Messages.fractionGoodbye;
import static edu.kirkwood.view.Messages.fractionGreet;
import static edu.kirkwood.view.UIUtility.displayError;
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
            // Validate input
            String[] parts  = null;
            try {
                parts = splitCalculation(value);
            } catch (IllegalArgumentException e) {
                displayError(e.getMessage());
                continue;
            }
            String fractionStr1 = parts[0];
            String operator = parts[1];
            String fractionStr2 = parts[2];

            Fraction f1 = null;
            Fraction f2 = null;
            try {
                f1 = parseFraction(fractionStr1);
                f2 = parseFraction(fractionStr2);
            } catch(Exception e) {
                displayError(e.getMessage());
                continue;
            }
            // Perform mathematical operations
            Fraction result = null;
            if(operator.equals("+")) {
                result = f1.add(f2);
            } else if(operator.equals("-")) {
                result = f1.subtract(f2);
            } else if(operator.equals("*")) {
                result = f1.multiply(f2);
            } else if(operator.equals("/")) {
                try {
                    result = f1.divide(f2);
                } catch(ArithmeticException e) { // Can't divide by 0
                    displayError(e.getMessage());
                    continue;
                }
            }
            // Display output
            System.out.printf("Result: %s %s %s = %s%n%n",
                    f1.toMixedNumber(), operator, f2.toMixedNumber(), result.toMixedNumber());
        }

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
            if(parts.length < 2) {
                throw new IllegalArgumentException("Invalid fraction format");
            }
            // Validate the whole number
            int whole = 0;
            try {
                whole = Integer.parseInt(parts[0]);
            } catch(NumberFormatException e) {
                throw new NumberFormatException("Invalid whole number: '" + parts[0] + "'");
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
            boolean isNegative = whole < 0 || numerator < 0;
            numerator = Math.abs(whole) * denominator + Math.abs(numerator);
            if(isNegative) {
                numerator = -numerator;
            }

            Fraction result = null;
            try {
                result = new Fraction(numerator, denominator);
            } catch (ArithmeticException e) {
                // This will be thrown if the denominator is zero
                throw new ArithmeticException(e.getMessage() + ": '" + str + "'");
            }
            return result;
        }
        // Check proper/improper fractions, doesn't contain space, but it does contain a slash
        else if(str.contains("/")) {
            // Split the fraction into numerator and denominator parts
            String[] parts = str.split("/");
            if(parts.length < 2) {
                throw new IllegalArgumentException("Invalid fraction format");
            }
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
        else {
            int whole = 0;
            try {
                whole = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid whole number: '" + str + "'");
            }
            return new Fraction(whole, 1);
        }
    }

    /**
     * Parse a string into a Fraction objecct. Handles whole numbers, proper and improper fractions, and mixed numbers
     * @param str The string input to parse
     * @return a Fraction representing the parsed string
     * @throws NumberFormatException if the numerator or denominator are not valid integers
     * @throws IllegalArgumentException if the fraction format is not valid
     */
    // Implemented by GitHub Copilot with the following prompt:
    // Implement the `parseFraction` method in the `FractionCalculator` class to get the
    // related unit tests in the `FractionCalculatorTest` class to pass.
    // I used this as a follow-up prompt
    // I'm new to Java programming. Can you help me better understand the implementation of the `parseFraction` method.
//    public static Fraction parseFraction(String str) throws NumberFormatException, IllegalArgumentException {
//        if(str.contains(" ")) {
//            String[] parts = str.split(" ", 2);
//            int whole;
//            try {
//                whole = Integer.parseInt(parts[0]);
//            } catch(NumberFormatException e) {
//                throw new NumberFormatException("Invalid mixed number format");
//            }
//
//            if(parts.length != 2) {
//                throw new IllegalArgumentException("Invalid mixed number format");
//            }
//
//            String[] fractionParts = parts[1].split("/", -1);
//            if(fractionParts.length != 2) {
//                throw new IllegalArgumentException("Invalid mixed number format");
//            }
//
//            int numerator;
//            try {
//                numerator = Integer.parseInt(fractionParts[0]);
//            } catch(NumberFormatException e) {
//                throw new NumberFormatException("Invalid numerator");
//            }
//
//            int denominator;
//            try {
//                denominator = Integer.parseInt(fractionParts[1]);
//            } catch(NumberFormatException e) {
//                throw new NumberFormatException("Invalid denominator");
//            }
//
//            boolean isNegative = whole < 0 || numerator < 0;
//            numerator = Math.abs(whole) * denominator + Math.abs(numerator);
//
//            if (isNegative) {
//                numerator = -numerator;
//            }
//
//            return new Fraction(numerator, denominator);
//        }
//
//        if(str.contains("/")) {
//            String[] parts = str.split("/", -1);
//            if(parts.length != 2) {
//                throw new IllegalArgumentException("Invalid fraction format");
//            }
//
//            int numerator;
//            try {
//                numerator = Integer.parseInt(parts[0]);
//            } catch(NumberFormatException e) {
//                throw new NumberFormatException("Invalid numerator");
//            }
//
//            int denominator;
//            try {
//                denominator = Integer.parseInt(parts[1]);
//            } catch(NumberFormatException e) {
//                throw new NumberFormatException("Invalid denominator");
//            }
//            return new Fraction(numerator, denominator);
//        }
//
//        int whole;
//        try {
//            whole = Integer.parseInt(str);
//        } catch(NumberFormatException e) {
//            throw new NumberFormatException("Invalid whole number");
//        }
//        return new Fraction(whole, 1);
//    }
}
