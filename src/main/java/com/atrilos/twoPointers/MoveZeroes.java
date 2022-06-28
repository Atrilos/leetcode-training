package com.atrilos.twoPointers;

import java.util.Arrays;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 *
 * Follow up: Could you minimize the total number of operations done?
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        int start = 0, end = 0;

        for (; end < nums.length; end++) {
            if (nums[end] != 0) {
                int tmp = nums[start];
                nums[start] = nums[end];
                nums[end] = tmp;
                start++;
            }
        }
    }
}
