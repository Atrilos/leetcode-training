package com.atrilos.backtracking

/**
 * [698](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/description/)
 */
class PartitionToKEqualSumSubsets {
    private var memo = mutableSetOf<Int>()

    fun canPartitionKSubsets(nums: IntArray, k: Int): Boolean {
        if (k > nums.size) return false
        val sum = nums.sum()
        if (sum % k != 0) return false

        return backtrack(nums, 0, k, 0, 0, sum / k)
    }

    fun backtrack(nums: IntArray, start: Int, remaining: Int, bucket: Int, used: Int, target: Int): Boolean {
        if (remaining == 0) return true

        if (bucket == target) {
            val isValid = backtrack(nums, 0, remaining - 1, 0, used, target)

            memo += used
            return isValid
        }

        if (used in memo) {
            return false
        }

        for (i in start until nums.size) {
            if (used and (1 shl i) != 0 || bucket + nums[i] > target) continue

            if (backtrack(nums, i + 1, remaining, bucket + nums[i], used or (1 shl i), target)) return true
        }

        return false
    }
}