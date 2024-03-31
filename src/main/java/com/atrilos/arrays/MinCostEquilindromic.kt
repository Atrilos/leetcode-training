package com.atrilos.arrays

import kotlin.math.abs


/**
 * [2967](https://leetcode.com/problems/minimum-cost-to-make-array-equalindromic/description/)
 */
class MinCostEquilindromic {
    private fun isPalindrome(n: String): Boolean {
        var l = 0
        var r = n.lastIndex
        while (l < r) {
            if (n[l] != n[r]) return false
            l++
            r--
        }
        return true
    }

    fun minimumCost(nums: IntArray): Long {
        nums.sort()
        val median = nums[nums.size / 2]
        var hi = median
        var lo = median
        while (hi > 0 && !isPalindrome(hi.toString())) hi++
        while (lo > 0 && !isPalindrome(lo.toString())) lo--

        fun helper(n: Int) = nums.fold(0L) { acc: Long, i: Int -> acc + abs(n - i) }

        return minOf(helper(lo), helper(hi))
    }
}