package com.atrilos.heapPrioQueue

import java.util.*

/**
 * [2856](https://leetcode.com/problems/minimum-array-length-after-pair-removals/description/)
 */
fun minLengthAfterRemovals(nums: List<Int>): Int {
    // Step 1: Create a frequency map to count occurrences of each number
    val map = mutableMapOf<Int, Int>()
    for (num in nums) {
        map.merge(num, 1, Int::plus)
    }

    // Step 2: Create a max heap to store numbers and their frequencies in descending order
    val pq = PriorityQueue<Pair<Int, Int>> { a, b -> b.second - a.second }
    for (entry in map) {
        pq.offer(entry.key to entry.value)
    }

    // Step 3: remove  numbers with the highest frequencies
    while (pq.size > 1) {
        val maxElement = pq.poll()
        val minElement = pq.poll()

        if (maxElement.second - 1 != 0) pq.offer(maxElement.first to maxElement.second - 1)
        if (minElement.second - 1 != 0) pq.offer(minElement.first to minElement.second - 1)
    }

    // Step 4: Calculate the minimum length
    var minLength = 0
    while (pq.isNotEmpty()) {
        minLength += pq.poll().second
    }

    return minLength
}