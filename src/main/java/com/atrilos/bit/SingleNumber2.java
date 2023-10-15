package com.atrilos.bit;

/**
 * <a href="https://leetcode.com/problems/single-number-ii/description/">137</a>
 */
public class SingleNumber2 {
    /**
     * Time: O(n)
     * Space: O(1)
     */
    public int singleNumber(int[] nums) {
        int res = 0;

        for (int shift = 0; shift < 32; shift++) {
            // Find sum of bits in shift position between all nums
            // If sum ain't divisible by 3 then 1 bit is from result number
            int bitSum = 0;

            for (int num : nums) {
                bitSum += (num >> shift) & 1;
            }
            // Restoring result bit by bit by setting bits from bitSum
            res |= (bitSum % 3) << shift;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new SingleNumber2().singleNumber(new int[]{9, 2, 2, 2, 3,3,3, 4, 4, 4}));
    }
}
