package edu.kirkwood.view;

import java.util.Scanner;

public class UserInput {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Get an integer from the user
     * @param prompt The question to ask the user
     * @param required Does the user have to answer it
     * @param min The smallest value possible
     * @param max The largest value possible.
     * @return the integer from the user
     */
    public static int getInt(String prompt, boolean required, int min, int max) {
        int value = 0;
        String minMax = "";
        // Min set, Max not set
        if(min != Integer.MIN_VALUE && max == Integer.MAX_VALUE) {
            minMax = String.format(" [minimum %d]", min);
        }
        // Min and max
        if(min != Integer.MIN_VALUE && max != Integer.MAX_VALUE) {
            minMax = String.format(" [between %d and %d]", min, max);
        }
        return value;
    }

    public static int getInt(String prompt, boolean required, int min) {
        return getInt(prompt, required, min, Integer.MAX_VALUE);
    }

    public static int getInt(String prompt, boolean required) {
        return getInt(prompt, required, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static int getInt(String prompt) {
        return getInt(prompt, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

}
