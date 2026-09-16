package edu.kirkwood.view;

import edu.kirkwood.controller.marc.FractionCalculator;

import static edu.kirkwood.view.UIUtility.printMenu;
import static edu.kirkwood.view.UserInput.getInt;

public class MainMenu {
    /**
     * Displays the main menu and process selections until the user quits.
     */
    public static void show() {
        String[] menuItems = {
                "Marc's Fraction Calculator",
                "STUDENT NAME's WHATEVER Calculator",
                "Quit"
        };
        while(true) {
            printMenu("Main Menu", menuItems);
            int choice = getInt("Choose an option", true, 1, menuItems.length);
            switch(choice) {
                case 1:
                    FractionCalculator.start();
                    break;
                case 2:
                    // Your calculator
                    break;
                default:
                    return;
            }
        }
    }
}
