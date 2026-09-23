package edu.kirkwood.controller.marc;

import edu.kirkwood.model.Fraction;
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

    @Test
    @DisplayName("Test parseFraction with a positive mixed fraction like '1 2/3'")
    void parseFractionMixedNumberPositive() {
        // Arrange
        Fraction expected = new Fraction(7, 3);
        // Act
        Fraction actual = FractionCalculator.parseFraction("2 1/3"); // (2 * 3 + 1) / 3
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test parseFraction with a negative mixed fraction like '-1 2/3'")
    void parseFractionMixedNumberNegative() {
        // Arrange
        Fraction expected = new Fraction(-13, 4);
        // Act
        Fraction actual = FractionCalculator.parseFraction("-3 1/4"); // (-3 * 4 - 1) 4
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test parseFraction with a simple fraction")
    void parseFractionWithSimpleFraction() {
        // Arrange
        Fraction expected = new Fraction(3, 4);
        // Act
        Fraction actual = FractionCalculator.parseFraction("3/4");
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test parseFraction with a negative improper fraction")
    void parseFractionWithNegativeImproperFraction() {
        // Arrange
        Fraction expected = new Fraction(-7, 4);
        // Act
        Fraction actual = FractionCalculator.parseFraction("-7/4");
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test parseFraction with a negative denominator improper fraction")
    void parseFractionWithNegativeImproperDenominatorFraction() {
        // Arrange
        Fraction expected = new Fraction(-7, 4);
        // Act
        Fraction actual = FractionCalculator.parseFraction("7/-4");
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test parseFraction with a positive whole number")
    void parseFractionWithPositiveWholeNumber() {
        // Arrange
        Fraction expected = new Fraction(5, 1);
        // Act
        Fraction actual = FractionCalculator.parseFraction("5");
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test parseFraction with a negative whole number")
    void parseFractionWithNegativeWholeNumber() {
        // Arrange
        Fraction expected = new Fraction(-5, 1);
        // Act
        Fraction actual = FractionCalculator.parseFraction("-5");
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void parseFractionWithTextThrowsException() {
        // Act and Assert
        NumberFormatException e = assertThrows(NumberFormatException.class, () -> FractionCalculator.parseFraction("a"));

        // Arrange
        String expectedError = "Invalid fraction format";
        // Act
        String actualError = e.getMessage();
        // Assert
        assertEquals(expectedError, actualError);
    }

    @Test
    void parseFractionWithTextNumeratorThrowsException() {
        // Act and Assert
        NumberFormatException e = assertThrows(NumberFormatException.class, () -> FractionCalculator.parseFraction("a/2"));

        // Arrange
        String expectedError = "Invalid numerator";
        // Act
        String actualError = e.getMessage();
        // Assert
        assertTrue(actualError.contains(expectedError));
    }

    @Test
    void parseFractionWithTextDenominatorThrowsException() {
        // Act and Assert
        NumberFormatException e = assertThrows(NumberFormatException.class, () -> FractionCalculator.parseFraction("2/a"));

        // Arrange
        String expectedError = "Invalid denominator";
        // Act
        String actualError = e.getMessage();
        // Assert
        assertTrue(actualError.contains(expectedError));
    }





    @Test
    void parseMixedFractionWithBadNumeratorException() {
        // Act and Assert
        NumberFormatException e = assertThrows(NumberFormatException.class, () -> FractionCalculator.parseFraction("1 a/4"));

        // Arrange
        String expectedError = "Invalid numerator";
        // Act
        String actualError = e.getMessage();
        // Assert
        assertEquals(expectedError, actualError);
    }

    @Test
    void parseMixedFractionWithZeroWholeNoException() {
        // Arrange
        Fraction expected = new Fraction (1, 3);
        // Act
        Fraction actual = FractionCalculator.parseFraction("0 1/3");
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void parseMixedFractionWithBadDenominatorException() {
        // Act and Assert
        NumberFormatException e = assertThrows(NumberFormatException.class, () -> FractionCalculator.parseFraction("1 1/a"));

        // Arrange
        String expectedError = "Invalid denominator";
        // Act
        String actualError = e.getMessage();
        // Assert
        assertEquals(expectedError, actualError);
    }

    @Test
    void parseMixedFractionWithBadWholeNumberException() {
        // Act and Assert
        NumberFormatException e = assertThrows(NumberFormatException.class, () -> FractionCalculator.parseFraction("a 1/1"));

        // Arrange
        String expectedError = "Invalid whole number";
        // Act
        String actualError = e.getMessage();
        // Assert
        assertEquals(expectedError, actualError);
    }


    @Test
    @DisplayName("Test parseFraction with invalid mixed number format should throw exception")
    void parseFractionWithInvalidMixedNumber_ThrowsException() {
        // Act and Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> FractionCalculator.parseFraction("1 2 3"));
        // Arrange
        String expectedError = "Invalid mixed number format";
        // Act
        String actualError = e.getMessage();
        // Assert
        assertTrue(actualError.contains(expectedError));
    }

    @Test
    @DisplayName("Test parseFraction with zero denominator should throw exception")
    void parseFractionWithZeroDenominator_ThrowsException() {
        Exception e = assertThrows(ArithmeticException.class, () -> FractionCalculator.parseFraction("5/0"));
        assertTrue(e.getMessage().contains("Denominator cannot be zero"));
    }
}