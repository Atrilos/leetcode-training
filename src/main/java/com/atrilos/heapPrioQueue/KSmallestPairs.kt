package com.atrilos.heapPrioQueue

/**
 * [373](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/)
 */
fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
    val pq = java.util.PriorityQueue<Pair<Int, Int>>(compareBy { nums1[it.first] + nums2[it.second] })
    val seen = mutableSetOf<Pair<Int, Int>>()
    pq.add(0 to 0)
    seen += 0 to 0

    val res = mutableListOf<List<Int>>()

    while (res.size < k && pq.isNotEmpty()) {
        val (i, j) = pq.remove()
        res += listOf(nums1[i], nums2[j])
        if (i + 1 <= nums1.lastIndex && i + 1 to j !in seen) {
            pq.add(i + 1 to j)
            seen += i + 1 to j
        }
        if (j + 1 <= nums2.lastIndex && i to j + 1 !in seen) {
            pq.add(i to j + 1)
            seen += i to j + 1
        }
    }

    return res
}