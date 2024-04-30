package com.atrilos.dp1d;

/**
 * <a href="https://leetcode.com/problems/wiggle-subsequence/description/">376. Wiggle Subsequence</a>
 */
public class WiggleMaxLength {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int up = nums[1] - nums[0] == 0 ? 0
                : nums[1] - nums[0] > 0 ? 1 : 0;
        int res = up == 0 ? 1 : 2;

        for (int i = 2; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((up == 1 || up == 0) && diff < 0) {
                res++;
                up = -1;
            } else if ((up == 0 || up == -1) && diff > 0) {
                res++;
                up = 1;
            }
        }

        return res;
    }
}
