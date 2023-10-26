package com.atrilos.twoPointers

/**
 * [1793](https://leetcode.com/problems/maximum-score-of-a-good-subarray/description/)
 */
class MaxScoreInGoodSubarray {
    fun maximumScore(nums: IntArray, k: Int): Int {
        val n = nums.size
        var left = k
        var right = k
        var ans = nums[k]
        var currMin = nums[k]

        while (left > 0 || right < n - 1) {
            currMin = if ((if (left > 0) nums[left - 1] else 0) < (if (right < n - 1) nums[right + 1] else 0)) {
                right++
                minOf(currMin, nums[right])
            } else {
                left--
                minOf(currMin, nums[left])
            }
            ans = maxOf(ans, (currMin * (right - left + 1)))
        }

        return ans
    }
}