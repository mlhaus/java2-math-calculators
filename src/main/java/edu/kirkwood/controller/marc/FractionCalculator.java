package edu.kirkwood.controller.marc;

import static edu.kirkwood.view.Messages.fractionGoodbye;
import static edu.kirkwood.view.Messages.fractionGreet;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getString;

public class FractionCalculator {
    public static final String INVALID_FORMAT_MSG = "Invalid format. Ensure operator (+, -, *, /) has a space on both sides.";
    public static final String INVALID_FIRST_FRACTION = "Invalid first fraction";
    public static final String INVALID_SECOND_FRACTION = "Invalid second fraction";
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
}
