package com.atrilos.bit;

/**
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: a = 1, b = 2
 * Output: 3
 * Example 2:
 * <p>
 * Input: a = 2, b = 3
 * Output: 5
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -1000 <= a, b <= 1000
 */
public class SumOfTwoInteger {

    public int getSum(int a, int b) {
        while (b != 0) {
            int tmp = (a & b) << 1;
            a = a ^ b;
            b = tmp;
        }

        return a;
    }
}
