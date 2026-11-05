package edu.kirkwood.controller.colman;

import edu.kirkwood.view.Helpers;
import edu.kirkwood.model.Fraction;

import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getString;

public class SlopeCalculator {


    public static void start() {

        slopeGreet();

        while(true) {
            String value = getString("Enter coords in format '(x, y) (x, y)' (or 'q' to quit).");
            if (value.equalsIgnoreCase("q") || value.equalsIgnoreCase("quit")) {
                break;
            }

            int[][] coords = null;

            try {
                coords = inputCoords(value);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                slopeGoodbye();
                pressEnterToContinue();

                continue;
            }

            String hyp = null;
            Fraction slope = null;
            String theta = null;
            try {
                theta = Helpers.round(calcTheta(coords), 2);
                hyp = Helpers.round(calcHyp(coords), 2);
                slope = (calcSlope(coords));
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
                slopeGoodbye();
                pressEnterToContinue();
                continue;
            }

            System.out.println("Slope: " + slope + " units");
            System.out.println("Hypotenuse: " + hyp + " units");
            System.out.println("Angle: " + theta + " degrees");
        }
        slopeGoodbye();
        pressEnterToContinue();
    }

    /**
     * Get user input in the form of "(x1, y1) (x2, y2)"
     * @param input The user's input coord values
     * @return two smaller coord arrays of {x, y}
     */
    public static int[][] inputCoords(String input) {
        // 1. Split the string where the two coordinate groups meet: ") ("
        String[] pointStrings = input.trim().split("\\)\\s*\\(");

        // Guard check
        if (pointStrings.length != 2) {
            throw new IllegalArgumentException("Invalid input format. Must be two coordinate pairs like (x1, y1) (x2, y2)");
        }

        int[][] coords = new int[pointStrings.length][];

        for (int i = 0; i < pointStrings.length; i++) {

            coords[i] = splitCoord(pointStrings[i]);
            }

        return coords;
    }

    /**
     * Take user input, like "(1,2)" and converts it into an array, like {1, 2}
     * @param coordStr The user's raw input
     * @return An array of two integers
     */
    public static int[] splitCoord(String coordStr) {

        String clean = coordStr.replace("(", "").replace(")", "");

        String[] parts = clean.split(",");

        int x;
        try {
            x = Integer.parseInt(parts[0].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid X coord");
        }

        int y;
        try {
            y = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(("Invalid Y coord"));
        }
        return new int[]{x, y};
    }

    public static int calcDx(int x1, int x2){
        return x2 - x1;
    }

    public static int calcDy(int y1, int y2){
        return y2 - y1;
    }

    public static Fraction calcSlope(int[][] coords) {

        int x1 = coords[0][0];
        int y1 = coords[0][1];
        int x2 = coords[1][0];
        int y2 = coords[1][1];

        int dx = calcDx(x1, x2);

        if (dx == 0) {
            throw new ArithmeticException("Undefined slope: Vertical line.");
        }

        int dy = calcDy(y1, y2);

        Fraction slope = new Fraction(dy, dx);
        slope.simplify();
        return slope;
    }

    public static double calcHyp(int[][] coords){

        int Dx = calcDx(coords[0][0], coords[1][0]);
        int Dy = calcDy(coords[0][1], coords[1][1]);
        return (Math.sqrt(Math.pow(Dx, 2) + Math.pow(Dy, 2)));
    }

    public static double calcTheta(int[][] coords) {
        int Dx = calcDx(coords[0][0], coords[1][0]);
        int Dy = calcDy(coords[0][1], coords[1][1]);
        if (Dx == 0 && Dy == 0){

            throw new ArithmeticException("Undefined angle of single point.");
            }
            return Math.toDegrees(Math.atan2(Dy, Dx));
    }

}

