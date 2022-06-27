package com.atrilos.arrays;

/**
 * Given an array nums of size n, return the majority element.
 * <p>
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 * <p>
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * <p>
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
public class MajorityElement {

    public int majorityElement(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int res = 0;
        int count = 0;

        for (int num : nums) {
            if (count < 1) {
                res = num;
                count++;
            } else if (num == res) {
                count++;
            } else {
                count--;
            }
        }

        return res;
    }
}
