package com.atrilos.dp1d

/**
 * [416](https://leetcode.com/problems/partition-equal-subset-sum/description/)
 */
class PartitionEqualSubsetSum {
    fun canPartition(nums: IntArray): Boolean {
        val totalSum = nums.sum()
        // If total sum is odd, cannot partition into two equal subsets
        if (totalSum % 2 != 0) return false

        val target = totalSum / 2
        val dp = BooleanArray(target + 1)
        dp[0] = true // Base case: zero sum is always achievable

        for (num in nums) {
            // Traverse backwards to ensure each num is only used once
            for (sum in target downTo num) {
                dp[sum] = dp[sum] || dp[sum - num]
            }
        }

        return dp[target]
    }
}