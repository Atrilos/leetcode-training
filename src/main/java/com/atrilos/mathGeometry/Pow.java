package com.atrilos.mathGeometry;

/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 * Example 2:
 * <p>
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 * Example 3:
 * <p>
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2^-2 = 1/2^2 = 1/4 = 0.25
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= x^n <= 10^4
 */
public class Pow {

    public double myPowRecursive(double x, int n) {
        if (n == 0)
            return 1;
        if (x == 0)
            return 0;
        double ans;

        if (n < 0) {
            double pow = myPowRecursive(x, n / 2);
            ans = pow * pow;
            if (n % 2 != 0)
                ans /= x;
        } else {
            double pow = myPowRecursive(x, n / 2);
            ans = pow * pow;
            if (n % 2 != 0)
                ans *= x;
        }

        return ans;
    }

    public double myPowIterative(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        // Counter edge case x (Int.MIN_VALUE, Int.MAX_VALUE)
        long longN = n;

        if (n < 0) {
            longN = -1 * longN;
            x = 1.0 / x;
        }

        double res = 1;
        for (; longN != 0; longN >>= 1) {

            if (longN % 2 == 1) {
                res *= x;
            }

            x *= x;
        }

        return res;
    }
}
