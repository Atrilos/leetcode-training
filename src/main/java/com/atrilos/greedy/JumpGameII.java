package com.atrilos.greedy;

import java.util.Arrays;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * You can assume that you can always reach the last index.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 1000
 */
public class JumpGameII {
    public static void main(String[] args) {
        int[] input = Arrays.stream("135456479851534681631246".split("")).mapToInt(Integer::parseInt).toArray();
        System.out.println(new JumpGameII().jump(input));
    }

    //bottom-up dp
    //TC - O(n^2)
    //SC - O(n)
    public int jumpDP(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int currentMin = dp[i];
            for (int j = 0; j <= nums[i] && i + j < n; j++) {
                if (dp[i + j] == 0) {
                    dp[i + j] = currentMin + 1;
                }
            }
        }
        return dp[n - 1];
    }

    //greedy
    //TC - O(n)
    //SC - O(1)
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int currentLevel = 0;
        int maxOnThisLevel = 0;
        int minJumps = 0;

        for (int i = 0; i < nums.length; i++) {
            maxOnThisLevel = Math.max(maxOnThisLevel, nums[i] + i);
            if (maxOnThisLevel >= nums.length - 1) {
                return minJumps + 1;
            }
            if (i == currentLevel) {
                currentLevel = maxOnThisLevel;
                minJumps++;
            }
        }

        return minJumps;
    }
}
