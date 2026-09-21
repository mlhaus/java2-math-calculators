package edu.kirkwood.controller.marc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionCalculatorTest {

    @Test
    @DisplayName("Split given two proper fractions and +")
    void splitCalculationAdditionWithProperFractions() {
        // Arrange
        String input = "1/2 + 3/4";
        String[] expected = {"1/2", "+", "3/4"};
        // Act
        String[] actual = FractionCalculator.splitCalculation(input);
        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Split given one mixed fraction and -")
    void splitCalculationSubtractionWithMixedFractions() {
        // Arrange
        String input = "3 1/4 - 1/2";
        String[] expected = {"3 1/4", "-", "1/2"};
        // Act
        String[] actual = FractionCalculator.splitCalculation(input);
        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Split given one mixed fraction and *")
    void splitCalculationMultiplicationWithMixedFractions() {
        // Arrange
        String input = "-5 * -2 1/3";
        String[] expected = {"-5", "*", "-2 1/3"};
        // Act
        String[] actual = FractionCalculator.splitCalculation(input);
        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Split given one improper fraction and /")
    void splitCalculationDivisonWithImproperFractions() {
        // Arrange
        String input = "10/3 / 5";
        String[] expected = {"10/3", "/", "5"};
        // Act
        String[] actual = FractionCalculator.splitCalculation(input);
        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Split given two proper fractions, +, and extra spaces")
    void splitCalculationAdditionWithExtraSpaces() {
        // Arrange
        String input = "    1/2    +  3/4    ";
        String[] expected = {"1/2", "+", "3/4"};
        // Act
        String[] actual = FractionCalculator.splitCalculation(input);
        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Split no spaces around operator")
    void splitCalculationNoSpacesAroundOperator() {
        // Arrange
        String input = "1/2+3/4";
        // Act and Assert
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> FractionCalculator.splitCalculation(input));

        // Arrange
        String expectedMessage = FractionCalculator.INVALID_FORMAT_MSG;
        // Act
        String actualMessage = e.getMessage();
        // Assert
        assertEquals(expectedMessage, actualMessage);

    }

    @Test
    @DisplayName("Test splitCalculation with unknown operator should throw exception")
    void splitCalculationWithUnknownOperator() {
        // Arrange
        String input = "1/2 ^ 3/4";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> FractionCalculator.splitCalculation(input));
    }

    @Test
    @DisplayName("Test splitCalculation with missing first fraction should throw exception")
    void splitCalculationWithMissingFirstFraction() {
        // Arrange
        String input = " + 3/4";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FractionCalculator.splitCalculation(input);
        });

        String expectedMessage = FractionCalculator.INVALID_FIRST_FRACTION;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Test splitCalculation with missing second fraction should throw exception")
    void splitCalculationWithMissingSecondFraction() {
        // Arrange
        String input = "1/2 + ";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FractionCalculator.splitCalculation(input);
        });

        String expectedMessage = FractionCalculator.INVALID_SECOND_FRACTION;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}