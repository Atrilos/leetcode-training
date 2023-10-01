package com.atrilos.intervals

/**
 * [163](https://leetcode.com/problems/missing-ranges/)
 */
fun findMissingRanges(nums: IntArray, lower: Int, upper: Int): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    var lo = lower
    for (num in nums) {
        if (num == lo) {
            lo = num + 1
        } else if (num > lo) {
            res += listOf(lo, num - 1)
            lo = num + 1
        }
    }
    if (lo <= upper) res += listOf(lo, upper)
    return res
}