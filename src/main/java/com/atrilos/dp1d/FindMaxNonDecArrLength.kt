package com.atrilos.dp1d

import java.util.*

/**
 * [2945](https://leetcode.com/problems/find-maximum-non-decreasing-array-length/description/)
 */
class FindMaxNonDecArrLength {
    fun findMaximumLength(nums: IntArray): Int {
        val n = nums.size
        val pre = IntArray(n + 2)
        val dp = IntArray(n + 1)
        val acc = LongArray(n + 1)

        for (i in 1..n) {
            acc[i] = acc[i - 1] + nums[i - 1]
        }

        var i = 0
        var j = 1

        // i....j....k
        while (j <= n) {
            i = maxOf(i, pre[j])
            dp[j] = dp[i] + 1
            var k = Arrays.binarySearch(acc, 2 * acc[j] - acc[i])
            if (k < 0) {
                k = -k - 1
            }
            pre[k] = j
            j++
        }

        return dp[n]
    }
}