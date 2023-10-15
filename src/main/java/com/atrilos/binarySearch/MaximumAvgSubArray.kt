package com.atrilos.binarySearch

/**
 * [644](https://leetcode.com/problems/maximum-average-subarray-ii/description/)
 */
class MaximumAvgSubArray2 {
    fun findMaxAverage(nums: IntArray, k: Int): Double {
        var l = -10001.0
        var r = 10001.0
        while (l + 0.00001 <= r) {
            val mid = (l + r) * 0.5
            if (check(nums, k, mid)) {
                l = mid
            } else {
                r = mid
            }
        }
        return l
    }

    private fun check(nums: IntArray, k: Int, x: Double): Boolean {
        var cur = 0.0
        var prev = 0.0
        for (i in 0..<k) {
            cur += nums[i] - x
        }
        if (cur >= 0) {
            return true
        }
        for (i in k..nums.lastIndex) {
            cur += nums[i] - x
            prev += nums[i - k] - x
            if (prev < 0) {
                cur -= prev
                prev = 0.0
            }
            if (cur >= 0) {
                return true
            }
        }
        return false
    }
}

/**
 * [643](https://leetcode.com/problems/maximum-average-subarray-i/)
 */
class MaximumAvgSubArray {
    fun findMaxAverage(nums: IntArray, k: Int): Double {
        var l = Double.MAX_VALUE
        var r = Double.MIN_VALUE
        for (num in nums) {
            l = minOf(l, num.toDouble())
            r = maxOf(r, num.toDouble())
        }
        while (l + 0.00001 <= r) {
            val mid = (l + r) * 0.5
            if (check(nums, k, mid)) {
                l = mid
            } else {
                r = mid
            }
        }
        return l
    }

    private fun check(nums: IntArray, k: Int, x: Double): Boolean {
        var cur = 0.0
        for (i in 0..<k) {
            cur += nums[i] - x
        }
        if (cur >= 0) {
            return true
        }
        for (i in k..nums.lastIndex) {
            cur += nums[i] - x
            cur -= nums[i - k] - x
            if (cur >= 0) {
                return true
            }
        }
        return false
    }
}

