package com.atrilos.dp1d

/**
 * https://leetcode.com/problems/house-robber/
 * 198
 */
fun main() {
    println(rob(intArrayOf(2,1,1,2)))
}
fun rob(nums: IntArray): Int {
    var prev2 = 0
    var prev1 = 0
    var next: Int
    for (i in 0..nums.lastIndex) {
        next = Math.max(prev2 + nums[i], prev1)
        prev2 = prev1
        prev1 = next
    }
    return prev1
}