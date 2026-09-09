package edu.kirkwood.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    private Fraction f1;
    private Fraction f2;

    @BeforeEach
    void setUp() {
        f1 = new Fraction();
        f2 = new Fraction(2, 3);
    }

    @Test
    void testToString() {
        assertEquals("1/1", f1.toString());
        assertEquals("2/3", f2.toString());
    }

    @Test
    void getNumerator() {
        assertEquals(1, f1.getNumerator());
        assertEquals(2, f2.getNumerator());
    }

    @Test
    void setNumeratorPostive() {
        // Act
        f1.setNumerator(3);
        // Assert
        assertEquals(3, f1.getNumerator());
        assertEquals("3/1", f1.toString());
    }

    @Test
    void setNumeratorZero() {
        // Act
        f1.setNumerator(0);
        // Assert
        assertEquals(0, f1.getNumerator());
        assertEquals("0/1", f1.toString());
    }

    @Test
    void setNumeratorNegative() {
        // Act
        f1.setNumerator(-3);
        // Assert
        assertEquals(-3, f1.getNumerator());
        assertEquals("-3/1", f1.toString());
    }

    @Test
    void getDenominator() {
        assertEquals(1, f1.getDenominator());
        assertEquals(3, f2.getDenominator());
    }


    @Test
    void setDenominatorPostive() {
        // Act
        f1.setDenominator(3);
        // Assert
        assertEquals(3, f1.getDenominator());
        assertEquals("1/3", f1.toString());
    }

    @Test
    void setDenominatorZero() {
        // Act and Assert
        assertThrows(ArithmeticException.class, () -> f1.setDenominator(0));
    }

    @Test
    void setDenominatorNegative() {
        // Act
        f1.setDenominator(-3);
        // Assert

        assertEquals(-1, f1.getNumerator());
        assertEquals(3, f1.getDenominator());
        assertEquals("-1/3", f1.toString());
    }

    @Test
    void setNumeratorDenominatorNegative() {
        // Act
        f1.setNumerator(-3);
        f1.setDenominator(-3);
        // Assert
        assertEquals(3, f1.getNumerator());
        assertEquals(3, f1.getDenominator());
        assertEquals("3/3", f1.toString());
    }

    @Test
    void compareTo() {
        fail();
    }

    @Test
    void testEquals() {
        fail();
    }

    @Test
    void testHashCode() {
        fail();
    }

    @Test
    void gcd() {
        // Act and Assert
        assertEquals(15,Fraction.gcd(75, 45));
        assertEquals(2, Fraction.gcd(2, 4));
        assertEquals(1, Fraction.gcd(5, 7));
    }

    @Test
    void gcdPositivesAndNegatives() {
        int result1 = Fraction.gcd(5, 7);
        int result2 = Fraction.gcd(-5, 7);
        int result3 = Fraction.gcd(5, -7);
        int result4 = Fraction.gcd(-5, -7);
        assertTrue(result1 == 1);
        assertTrue(result1 == result2 && result2 == result3 && result3 == result4);
    }

    @Test
    @DisplayName("Test lcm with two prime numbers")
    void lcmWithPrimes() {
        // Act and Assert
        assertEquals(77, Fraction.lcm(7, 11));
        assertEquals(65, Fraction.lcm(5, 13));
    }

    @Test
    @DisplayName("Test LCM with two positive integers")
    void testLcmWithPositiveIntegers() {
        assertEquals(24, Fraction.lcm(6, 8));
    }

    @Test
    @DisplayName("Test LCM where one number is a multiple of the other")
    void testLcmWithMultiple() {
        assertEquals(12, Fraction.lcm(4, 12));
        assertEquals(9, Fraction.lcm(3, 9));
    }

    @Test
    @DisplayName("Test LCM with two prime numbers")
    void testLcmWithPrimes() {
        // The lcm of two prime numbers is their product.
        assertEquals(77, Fraction.lcm(7, 11));
    }

    @Test
    @DisplayName("Test LCM with the number 1")
    void testLcmWithOne() {
        assertEquals(9, Fraction.lcm(1, 9));
        assertEquals(9, Fraction.lcm(9, 1));
        assertEquals(1, Fraction.lcm(1, 1));
    }

    @Test
    @DisplayName("Test LCM with identical numbers")
    void testLcmWithIdenticalNumbers() {
        assertEquals(5, Fraction.lcm(5, 5));
    }

    @Test
    @DisplayName("Test LCM where one of the inputs is zero")
    void testLcmWithZero() {
        assertEquals(0, Fraction.lcm(10, 0));
        assertEquals(0, Fraction.lcm(0, 10));
        assertEquals(0, Fraction.lcm(0, 0));
    }

    @Test
    void simplify() {
        fail();
    }

    @Test
    void toMixedNumber() {
        fail();
    }

    @Test
    @DisplayName("Test 1/1 + 2/3 = 5/3")
    void addWholeNumberToFraction() {
        Fraction result = f1.add(f2);
        assertEquals(5, result.getNumerator());
        assertEquals(3, result.getDenominator());
    }

    @Test
    @DisplayName("-1/4 + 2/3 = 5/12")
    void addNegativeFractionToPositive() {
        // Arrange
        f1 = new Fraction(-1, 4);
        // Act
        Fraction f3 = f1.add(f2);
        // Assert
        assertEquals(5, f3.getNumerator());
        assertEquals(12, f3.getDenominator());
    }

    @Test
    @DisplayName("Test 1/4 + 1/4 = 1/2")
    void addFractionsThatNeedSimplification() {
        // Arrange
        f1 = new Fraction(1, 4);
        f2 = new Fraction(1, 4);
        // Act
        Fraction result = f1.add(f2);
        // Assert
        assertEquals(1, result.getNumerator());
        assertEquals(2, result.getDenominator());
    }

    @Test
    void subtract() {
        fail();
    }

    @Test
    void multiply() {
        fail();
    }

    @Test
    void divide() {
        fail();
    }
}