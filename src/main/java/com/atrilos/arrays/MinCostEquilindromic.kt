package com.atrilos.arrays

import kotlin.math.abs


/**
 * [2967](https://leetcode.com/problems/minimum-cost-to-make-array-equalindromic/description/)
 */
class MinCostEquilindromic {

    companion object {
        private const val MAX_THRESHOLD = 1_000_000_000
    }
    private fun isPalindrome(num: Int): Boolean {
        val n = num.toString()
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
        while (hi <= MAX_THRESHOLD && !isPalindrome(hi)) hi++
        while (lo > 0 && !isPalindrome(lo)) lo--

        fun helper(n: Int) = nums.fold(0L) { acc: Long, i: Int -> acc + abs(n - i) }

        return minOf(helper(lo), helper(hi))
    }
}