package edu.kirkwood.controller;

import edu.kirkwood.controller.colman.SlopeCalculator;
import edu.kirkwood.model.Fraction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SlopeCalculatorTest {

    @Test
    void inputCoords() {
        // Arrange
        int[][] expected = {
                {1, 2},
                {3, 4}
        };

        // Act
        int[][] actual = SlopeCalculator.inputCoords("(1, 2) (3, 4)");

        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    void inputCoordsNoBueno() {
        // Arrange
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> SlopeCalculator.inputCoords("1 2 3 4"));
    }


    @Test
    void splitCoordsPositiveXY() {
        // Arrange
        int[] expected = {1, 2};
        // Act
        int[] actual = SlopeCalculator.splitCoord("(1, 2)");
        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    void splitCoordsBadInput() {
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> SlopeCalculator.splitCoord("M"));
    }

    @Test
    void splitCoordsOutOfBounds() {
        // Act and Assert
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> SlopeCalculator.splitCoord("1"));
    }

    @Test
    void calcDxAndDy() {
        // Arrange
        int[][] coords = SlopeCalculator.inputCoords("(1, 2) (3, 4)");
        int expectedDx = 2;
        int expectedDy = 2;

        // Act
        int actualDx = SlopeCalculator.calcDx(coords[0][0], coords[1][0]);
        int actualDy = SlopeCalculator.calcDy(coords[0][1], coords[1][1]);

        // Assert
        assertEquals(expectedDx, actualDx);
        assertEquals(expectedDy, actualDy);
    }

    @Test
    void calcSlopeReg() {
        // Arrange
        int[][] mockCoords = {
                {1, 2},
                {3, 4}
        };

        //Act
        Fraction expectedSlope = new Fraction(1, 1);
        Fraction actualSlope = SlopeCalculator.calcSlope(mockCoords);

        // Assert
        assertEquals(expectedSlope, actualSlope);
    }

    @Test
    void calcSlopeVert() {
        //Arrange
        int[][] mockCoords = {
                {1, 4},
                {1, 8}
        };
        //Act & Assert
        assertThrows(ArithmeticException.class, () -> SlopeCalculator.calcSlope(mockCoords));

    }
}
