package com.atrilos.greedy

/**
 * [918](https://leetcode.com/problems/maximum-sum-circular-subarray/description/)
 */
class MaxCircularSubarray {
    fun maxSubarraySumCircular(nums: IntArray): Int {
        var localMax = 0
        var localMin = 0
        var globalMax = nums[0]
        var globalMin = nums[0]
        var sum = 0
        for (num in nums) {
            localMax = maxOf(num, localMax + num)
            globalMax = maxOf(localMax, globalMax)
            localMin = minOf(num, localMin + num)
            globalMin = minOf(localMin, globalMin)
            sum += num
        }
        globalMin = if (globalMin == sum) globalMin else sum - globalMin
        return maxOf(globalMin, globalMax)
    }
}