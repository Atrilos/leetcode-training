package com.atrilos.bit;

/**
 * <a href="https://leetcode.com/problems/bitwise-and-of-numbers-range/">201</a>
 */
public class RangeBitwiseAnd {
    /**
     * Brian Kernighan algo
     * Clearing least significant bit till left >= right
     */
    public int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            right &= right - 1;
        }

        return right;
    }
}