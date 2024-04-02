package com.atrilos.arrays

/**
 * [1685](https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/description/)
 */
class GetSumAbsoluteDiff {
    fun getSumAbsoluteDifferences(nums: IntArray): IntArray {
        val res = IntArray(nums.size)
        var leftSum = 0
        var rightSum = nums.sum()

        for (i in nums.indices) {
            res[i] = rightSum - leftSum + nums[i] * (2 * i - nums.size)
            leftSum += nums[i]
            rightSum -= nums[i]
        }

        return res
    }
}