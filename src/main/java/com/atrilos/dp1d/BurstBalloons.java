package com.atrilos.dp1d;

/**
 * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array
 * nums. You are asked to burst all the balloons.
 * <p>
 * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of
 * bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
 * <p>
 * Return the maximum coins you can collect by bursting the balloons wisely.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,1,5,8]
 * Output: 167
 * Explanation:
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * Example 2:
 * <p>
 * Input: nums = [1,5]
 * Output: 10
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 300
 * 0 <= nums[i] <= 100
 */
public class BurstBalloons {
    public static void main(String[] args) {
        System.out.println(new BurstBalloons().maxCoins(new int[]{3, 1, 5, 8}));
    }

    public int maxCoins(int[] nums) {

        int len = nums.length;
        int[][] dp = new int[len + 2][len + 2];
        int[] n = new int[len + 2];
        System.arraycopy(nums, 0, n, 1, len);
        n[0] = 1;
        n[n.length - 1] = 1;

        return dfs(n, 1, len, dp);
    }

    public int dfs(int[] n, int l, int r, int[][] dp) {

        if (l > r)
            return 0;

        if (dp[l][r] != 0)
            return dp[l][r];

        for (int i = l; i <= r; i++) {
            int sum = n[l - 1] * n[i] * n[r + 1];

            sum += dfs(n, l, i - 1, dp);
            sum += dfs(n, i + 1, r, dp);

            dp[l][r] = Math.max(dp[l][r], sum);

        }

        return dp[l][r];
    }
}
