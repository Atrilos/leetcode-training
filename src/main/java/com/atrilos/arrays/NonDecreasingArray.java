package com.atrilos.arrays;

/**
 * Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most one element.
 *
 * We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,3]
 * Output: true
 * Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
 * Example 2:
 *
 * Input: nums = [4,2,1]
 * Output: false
 * Explanation: You can't get a non-decreasing array by modify at most one element.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 10^4
 * -105 <= nums[i] <= 10^5
 */
public class NonDecreasingArray {
    public static void main(String[] args) {
        System.out.println(new NonDecreasingArray().checkPossibility(new int[]{3, 4, 2, 4}));
    }
    public boolean checkPossibility(int[] nums) {
        int modified = 0, prev = nums[0], index = 1;
        for (; index < nums.length; ++index) {
            if (nums[index] < prev) {
                if (++modified > 1) return false;
                if (index - 2 >= 0 && nums[index - 2] > nums[index]) continue;
            }
            prev = nums[index];
        }
        return true;
    }
}
