package com.atrilos.heapPrioQueue

import java.util.*

fun main() {
    println(maxScore(intArrayOf(1, 3, 3, 2), intArrayOf(2, 1, 3, 4), 3))
}

fun maxScore(nums1: IntArray, nums2: IntArray, k: Int): Long {
    val n = nums1.size
    val pairs = Array(n) { IntArray(2) }

    for (i in 0 until n) {
        pairs[i] = intArrayOf(nums1[i], nums2[i])
    }

    Arrays.sort(pairs) { a: IntArray, b: IntArray -> b[1] - a[1] }

    val topKHeap = PriorityQueue(k) { a: Int, b: Int -> a - b }
    var topKSum: Long = 0

    for (i in 0 until k) {
        topKSum += pairs[i][0].toLong()
        topKHeap.add(pairs[i][0])
    }

    var answer = topKSum * pairs[k - 1][1]
    for (i in k until n) {
        topKSum += (pairs[i][0] - topKHeap.poll()).toLong()
        topKHeap.add(pairs[i][0])
        answer = maxOf(answer, topKSum * pairs[i][1])
    }

    return answer
}