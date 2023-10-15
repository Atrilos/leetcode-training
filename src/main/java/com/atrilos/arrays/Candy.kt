package com.atrilos.arrays

/**
 * [135](https://leetcode.com/problems/candy/)
 */
fun candy(ratings: IntArray): Int {
    val helper = IntArray(ratings.size) { 1 }

    for (i in 1..ratings.lastIndex) {
        if (ratings[i] > ratings[(i - 1).coerceAtLeast(0)]) {
            helper[i] = helper[i - 1] + 1
        }
    }
    for (i in (0..<ratings.lastIndex).reversed()) {
        if (ratings[i] > ratings[i + 1]) {
            helper[i] = maxOf(helper[i], helper[i + 1] + 1)
        }
    }

    return helper.sum()
}