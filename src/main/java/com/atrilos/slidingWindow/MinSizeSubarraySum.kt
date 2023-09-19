package com.atrilos.slidingWindow

/**
 * [209](https://leetcode.com/problems/minimum-size-subarray-sum/)
 */
fun minSubArrayLen(target: Int, nums: IntArray): Int {
    var res = -1
    var l = 0
    var r = 0
    var sum = 0
    while (r < nums.size) {
        sum += nums[r]
        if (sum >= target) {
            res = if (res == -1) r - l + 1 else minOf(res, r - l + 1)
            while (l <= r && sum >= target) {
                res = minOf(res, r - l + 1)
                sum -= nums[l++]
            }
        }
        r++
    }

    return if (res == -1) 0 else res
}