package com.atrilos.dp2d


/**
 * [1458](https://leetcode.com/problems/max-dot-product-of-two-subsequences/description/)
 */
class MaxDotProduct {
    fun maxDotProduct(nums1: IntArray, nums2: IntArray): Int {
        val dp = Array(nums1.size + 1) { IntArray(nums2.size + 1) }

        var max1 = Int.MIN_VALUE
        var min1 = Int.MAX_VALUE
        var max2 = Int.MIN_VALUE
        var min2 = Int.MAX_VALUE

        for (num in nums1) {
            max1 = maxOf(max1, num)
            min1 = minOf(min1, num)
        }

        for (num in nums2) {
            max2 = maxOf(max2, num)
            min2 = minOf(min2, num)
        }

        if (max1 < 0 && min2 > 0) {
            return max1 * min2
        }
        if (max2 < 0 && min1 > 0) {
            return max2 * min1
        }

        for (i in nums1.indices.reversed()) {
            for (j in nums2.indices.reversed()) {
                val use = nums1[i] * nums2[j] + dp[i + 1][j + 1]
                dp[i][j] = maxOf(use, dp[i + 1][j], dp[i][j + 1])
            }
        }

        return dp[0][0]
    }
}