package com.atrilos.heapPrioQueue

import java.util.PriorityQueue

fun findKthLargest(nums: IntArray, k: Int): Int {
    val queue = PriorityQueue<Int> { n1, n2 -> n2 - n1 }

    nums.forEach { queue += it }
    repeat(k - 1) { queue.poll() }

    return queue.poll()
}