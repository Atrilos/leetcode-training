package com.atrilos.dp2d

/**
 * [3117](https://leetcode.com/problems/minimum-sum-of-values-by-dividing-array/description/)
 */
class MinSumByDividingArray {
    private var n = 0
    private var m = 0
    private val maxValue = 1000000000
    private val allBits = Int.MAX_VALUE

    fun minimumValueSum(nums: IntArray, andValues: IntArray): Int {
        n = nums.size
        m = andValues.size
        val dp = Array(n) { Array(m) { mutableMapOf<Int, Int>() } }

        val res = dfs(nums, andValues, 0, 0, allBits, dp)

        return if (res == maxValue) -1 else res
    }

    private fun dfs(
        nums: IntArray,
        andValues: IntArray,
        pos: Int,
        andPos: Int,
        mask: Int,
        dp: Array<Array<MutableMap<Int, Int>>>
    ): Int{
        if (pos == n && andPos == m) {
            return 0
        }
        if (pos == n || andPos == m) {
            return maxValue
        }
        if (mask in dp[pos][andPos]) {
            return dp[pos][andPos][mask]!!
        }

        val newMask = mask and nums[pos]
        if (newMask < andValues[andPos]) {
            return maxValue
        }

        var split = maxValue
        if (newMask == andValues[andPos]) {
            split = nums[pos] + dfs(nums, andValues, pos + 1, andPos + 1, allBits, dp)
        }

        val notSplit = dfs(nums, andValues, pos + 1, andPos, newMask, dp)

        val res = minOf(notSplit, split)
        dp[pos][andPos][newMask] = res

        return res
    }
}