package com.abhishek;

import org.junit.jupiter.api.*;

public class JunitTest {
    @Test
    void testSquareRoot() {
        Assertions.assertEquals(3.0, Main.squareRoot(9), 0.0001);
    }

    @Test
    void testFactorial() {
        Assertions.assertEquals(120, Main.factorial(5));
    }

    @Test
    void testNaturalLog() {
        Assertions.assertEquals(0.0, Main.naturalLog(1), 0.0001);
    }

    @Test
    void testPower() {
        Assertions.assertEquals(8.0, Main.power(2, 3), 0.0001);
    }

    @Test
    void testFactorialNegativeNumber() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Main.factorial(-1);
        });
        Assertions.assertEquals("Factorial of negative number is not defined", exception.getMessage());
    }

    @Test
    void testNaturalLogNonPositive() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Main.naturalLog(0);
        });
        Assertions.assertEquals("Logarithm of non-positive number is not defined", exception.getMessage());
    }
}
