package com.atrilos.mathGeometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountIntegerEvenDigitSumTest {

    CountIntegerEvenDigitSum out = new CountIntegerEvenDigitSum();

    @Test
    void findSum() {
        assertEquals(out.findSum(450), 9);
    }
}