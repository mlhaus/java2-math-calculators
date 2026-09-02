package edu.kirkwood.model;

import org.junit.jupiter.api.BeforeEach;
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
        assertEquals(-3, f1.getDenominator());
        assertEquals("-1/3", f1.toString());
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
        fail();
    }

    @Test
    void lcm() {
        fail();
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
    void add() {
        fail();
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