package com.atrilos.greedy;

/**
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 * <p>
 * Return true if you can reach the last index, or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 10^5
 */
public class JumpGame {
    public static void main(String[] args) {
        System.out.println(new JumpGame().canJump(new int[]{3,2,1,0,4}));
    }

    //bottom-up DP
    //TC - O(n^2)
    //SP - O(n)
    public boolean canJumpDP(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return true;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        for (int i = 0; i < n && dp[i]; i++) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j >= n - 1)
                    return true;
                dp[i + j] = true;
            }
        }
        return false;
    }

    //greedy
    //TC - O(n)
    //SC - O(1)
    public boolean canJump(int[] nums) {
        int goal = nums.length - 1;
        for (int i = goal - 1; i >= 0; i--) {
            if (nums[i] + i >= goal)
                goal = i;
        }
        return goal == 0;
    }
}
