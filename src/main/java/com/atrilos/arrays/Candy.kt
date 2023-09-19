package com.atrilos.arrays

/**
 * [135](Candy)
 */
fun candy(ratings: IntArray): Int {
    val helper = IntArray(ratings.size) { 1 }

    for (i in ratings.indices) {
        if (ratings[i] > ratings[(i - 1).coerceAtLeast(0)]) {
            helper[i] = helper[i - 1] + 1
        }
    }
    for (i in ratings.indices.reversed()) {
        val nextIdx = (i + 1).coerceAtMost(ratings.lastIndex)
        if (ratings[i] > ratings[nextIdx]) {
            helper[i] = helper[i].coerceAtLeast(helper[nextIdx] + 1)
        }
    }

    return helper.sum()
}