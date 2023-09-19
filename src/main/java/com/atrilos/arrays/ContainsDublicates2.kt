package com.atrilos.arrays

import kotlin.math.abs

/**
 * [219](https://leetcode.com/problems/contains-duplicate-ii/description/)
 */
fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
    val map = mutableMapOf<Int, Int>()
    for ((i, num) in nums.withIndex()) {
        if (map.contains(num) && abs(i - map[num]!!) <= k) {
            return true
        }
        map[num] = i
    }

    return false
}

fun main() {
    containsNearbyDuplicate(intArrayOf(0,1,2,3,4,5,6),2)
}