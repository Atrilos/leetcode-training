package com.atrilos.heapPrioQueue

/**
 * [295](https://leetcode.com/problems/find-median-from-data-stream/description/)
 */
class MedianFinder {

    val minHeap = java.util.PriorityQueue<Int>()
    val maxHeap = java.util.PriorityQueue<Int>(compareByDescending { it })

    fun addNum(num: Int) {
        if (maxHeap.isNotEmpty() && maxHeap.peek() < num) {
            minHeap += num
        } else {
            maxHeap += num
        }
        if (minHeap.size > maxHeap.size) {
            maxHeap.add(minHeap.remove())
        } else if (maxHeap.size > minHeap.size + 1) {
            minHeap.add(maxHeap.remove())
        }
    }

    fun findMedian(): Double {
        return if (minHeap.size == maxHeap.size) {
            (minHeap.peek() + maxHeap.peek()) * 0.5
        } else {
            maxHeap.peek().toDouble()
        }
    }
}