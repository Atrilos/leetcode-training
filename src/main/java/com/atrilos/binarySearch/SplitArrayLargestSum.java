package com.atrilos.binarySearch;

/**
 * <a href="https://leetcode.com/problems/split-array-largest-sum/description/">410. Split Array Largest Sum</a>
 */
public class SplitArrayLargestSum {
    private boolean isValid(int[] nums, int k, int mid) {
        int count = 1, currentSum = 0;

        for (int num : nums) {
            currentSum += num;
            if (currentSum > mid) {
                count++;
                currentSum = num;
                if (count > k) {
                    return false;
                }
            }
        }

        return true;
    }

    public int splitArray(int[] nums, int k) {
        int left = 0;
        int right = 0;
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isValid(nums, k, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
