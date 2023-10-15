package com.atrilos.mathGeometry;


/**
 * <a href="https://leetcode.com/problems/sqrtx/">69</a>
 */
public class MySqrt {
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        long square;
        int l = 1; int r = x / 2;
        while (l <= r) {
            final int mid = l + (r - l) / 2;
            square = (long) mid * mid;
            if (square == x) {
                return mid;
            } else if (square < x) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return r;
    }

}
