package com.atrilos.dp1d;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums, return the length of the longest strictly increasing
 * subsequence.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 * <p>
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 * <p>
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(new int[]{10, 11, 2, 5, 3, 7, 101, 18}));
    }

    public int lengthOfLIS(int[] nums) {
        List<Integer> arr = new ArrayList<>();
        arr.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int index = binSearch(arr, nums[i]);
            if (index == arr.size()) {
                arr.add(nums[i]);
            } else {
                arr.set(index, nums[i]);
            }
        }
        return arr.size();
    }

    private int binSearch(List<Integer> list, int target) {
        int l = 0;
        int h = list.size();
        while (l < h) {
            int mid = (l + h) / 2;
            if (list.get(mid) < target) {
                l = mid + 1;
            } else {
                h = mid;
            }
        }
        return l;
    }
}
