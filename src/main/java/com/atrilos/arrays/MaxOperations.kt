package com.atrilos.arrays

/**
 * [1679](https://leetcode.com/problems/max-number-of-k-sum-pairs/description/)
 */

fun maxOperationsBetter(nums: IntArray, k: Int): Int {
    val numsOccurrences = nums.asSequence().groupingBy { it }.eachCount()
    return numsOccurrences
        .map { (num, count) -> minOf(numsOccurrences.getOrDefault(k - num, 0), count) }
        .sum() / 2
}

fun maxOperationsSimple(nums: IntArray, k: Int): Int {
    val numsMap = mutableMapOf<Int, Int>()
    nums.forEach {
        numsMap.merge(it, 1, Int::plus)
    }

    var cnt = 0

    val iterator = numsMap.iterator()
    while (iterator.hasNext()) {
        val (key, value) = iterator.next()
        if (key == k - key) {
            cnt += value / 2
            numsMap[key] = (value / 2) * 2
        } else if (numsMap.getOrDefault(k - key, 0) > 0) {
            val foundValue = numsMap.getOrDefault(k - key, 0)
            val diff = minOf(value, foundValue)
            cnt += diff
            numsMap[k - key] = foundValue - diff
            numsMap[key] = value - diff
        }
    }

    return cnt
}