package com.atrilos.sets

/**
 * [1814](https://leetcode.com/problems/count-nice-pairs-in-an-array/description/)
 */
class CountNicePairs {
    fun countNicePairs(nums: IntArray): Int {
        val map = mutableMapOf<Int, Int>()
        val mod = 1000000007
        var res = 0
        for (num in nums) {
            val diff = num - rev(num)
            val count = map.getOrDefault(diff, 0)
            res = (res + count) % mod
            map[diff] = count + 1
        }
        return res
    }

    private fun rev(num: Int): Int {
        var res = 0
        var num = num
        while (num != 0) {
            res *= 10
            res += num % 10
            num /= 10
        }
        return res
    }
}
