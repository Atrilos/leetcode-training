package com.atrilos.mathGeometry;

/**
 * <a href="https://leetcode.com/problems/number-of-digit-one/">233. Number of Digit One</a>
 */
public class CountDigitOne {
    public int countDigitOne(int n) {
        long ans = 0;

        for (long pow10 = 1; pow10 <= n; pow10 *= 10) {
            final long divisor = pow10 * 10;
            final int quotient = (int) (n / divisor);
            final int remainder = (int) (n % divisor);
            ans += quotient * pow10;
            ans += Math.max(Math.min(remainder - pow10 + 1, pow10), 0);
        }

        return (int) ans;
    }
}
