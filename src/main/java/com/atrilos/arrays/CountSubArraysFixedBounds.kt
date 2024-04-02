package com.atrilos.arrays

/**
 * [2444](https://leetcode.com/problems/count-subarrays-with-fixed-bounds/description/)
 */
class CountSubArraysFixedBounds {
    fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
        var minIdx = -1
        var maxIdx = -1
        var badIdx = -1
        var res = 0L

        for (i in nums.indices) {
            val num = nums[i]
            if (num > maxK || num < minK) badIdx = i
            if (num == maxK) maxIdx = i
            if (num == minK) minIdx = i

            res += maxOf(0L, minOf(maxIdx, minIdx) - badIdx.toLong())
        }

        return res
    }
}