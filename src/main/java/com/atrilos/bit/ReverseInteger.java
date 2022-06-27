package com.atrilos.bit;

/**
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
 * <p>
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 123
 * Output: 321
 * Example 2:
 * <p>
 * Input: x = -123
 * Output: -321
 * Example 3:
 * <p>
 * Input: x = 120
 * Output: 21
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -2^31 <= x <= 2^31 - 1
 */
public class ReverseInteger {

    public int reverse(int x) {
        int res = 0;

        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            if (res > Integer.MAX_VALUE / 10 ||
                    (res == Integer.MAX_VALUE / 10 && digit >= Integer.MAX_VALUE % 10))
                return 0;
            if (res < Integer.MIN_VALUE / 10 ||
                    (res == Integer.MIN_VALUE / 10 && digit <= Integer.MIN_VALUE % 10))
                return 0;
            res = res * 10 + digit;
        }

        return res;
    }
}
