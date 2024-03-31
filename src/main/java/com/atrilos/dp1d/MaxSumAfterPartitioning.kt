package com.atrilos.dp1d

/**
 * [1043](https://leetcode.com/problems/partition-array-for-maximum-sum/description/)
 */
fun maxSumAfterPartitioning(arr: IntArray, k: Int): Int {
    val dp = IntArray(arr.size + 1)

    for (i in 1..arr.size) {
        var currMaxInSubArray = 0
        for (j in 1..k) {
            if (i - j < 0) break
            currMaxInSubArray = maxOf(currMaxInSubArray, arr[i - j])
            val estimate = dp[i - j] + currMaxInSubArray * j
            dp[i] = maxOf(dp[i], estimate)
        }
    }

    return dp.last()
}
