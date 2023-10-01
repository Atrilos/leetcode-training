package com.atrilos.dp1d

/**
 * [2393](https://leetcode.com/problems/count-strictly-increasing-subarrays/description/)
 */
fun countSubarrays(nums: IntArray): Long {
    var currSequence = 1L
    var total = 1L
    for (i in 1..nums.lastIndex) {
        if (nums[i] > nums[i - 1]) {
            currSequence++
        } else {
            currSequence = 1
        }
        total += currSequence
    }
    return total
}