package com.atrilos.slidingWindow;

import java.util.stream.IntStream;

/**
 * The frequency of an element is the number of times it occurs in an array.
 * <p>
 * You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and increment the element at that index by 1.
 * <p>
 * Return the maximum possible frequency of an element after performing at most k operations.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,4], k = 5
 * Output: 3
 * Explanation: Increment the first element three times and the second element two times to make nums = [4,4,4].
 * 4 has a frequency of 3.
 * Example 2:
 * <p>
 * Input: nums = [1,4,8,13], k = 5
 * Output: 2
 * Explanation: There are multiple optimal solutions:
 * - Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
 * - Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
 * - Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
 * Example 3:
 * <p>
 * Input: nums = [3,9,6], k = 2
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= 10^5
 */
public class MaxFrequency {

    public static void main(String[] args) {
        System.out.println(new MaxFrequency().maxFrequency(new int[]{3, 9, 6}, 2));
    }

    /**
     * 0 ... i ... j, j + 1 ... n - 1
     * |---window--|
     * PreSum = sum(0, j)
     * Expected = nums[j] * windowLength
     * Expected - PreSum <= k
     */
    public int maxFrequency(int[] nums, int k) {
        countingSort(nums);
        int l = 0;
        long total = 0L;
        int res = 1;
        for (int r = 0; r < nums.length; r++) {
            total += nums[r];
            while ((long) nums[r] * (r - l + 1) > k + total) {
                total -= nums[l++];
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    // Counting Sort: O(n)
    private void countingSort(int[] nums) {
        int max = IntStream.of(nums).max().orElseThrow();
        int[] map = new int[max + 1];
        IntStream.of(nums).forEach(i -> map[i]++);
        int i = 0;
        int j = 0;
        while (i <= max) {
            if (map[i] > 0) {
                map[i]--;
                nums[j++] = i;
            } else {
                i++;
            }
        }
    }
}
