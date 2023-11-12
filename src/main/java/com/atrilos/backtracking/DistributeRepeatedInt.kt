package com.atrilos.backtracking

/**
 * [1655](https://leetcode.com/problems/distribute-repeating-integers/description/)
 */
class DistributeRepeatedInt {
    fun canDistribute(nums: IntArray, quantity: IntArray): Boolean {
        val map = mutableMapOf<Int, Int>()
        for (num in nums) {
            map[num] = map.getOrDefault(num, 0) + 1
        }
        quantity.sortDescending()
        val cnt = map.values.sorted().toMutableList()
        return backtrack(cnt, quantity, 0)
    }

    private fun backtrack(cnt: MutableList<Int>, quantity: IntArray, idx: Int): Boolean {
        if (idx == quantity.size) {
            return true
        }
        for (i in cnt.indices) {
            if (cnt[i] >= quantity[idx]) {
                cnt[i] -= quantity[idx]
                if (backtrack(cnt, quantity, idx + 1)) {
                    return true
                }
                cnt[i] += quantity[idx]
            }
        }
        return false
    }
}