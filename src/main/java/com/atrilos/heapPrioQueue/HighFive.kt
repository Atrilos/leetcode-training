package com.atrilos.heapPrioQueue

import kotlin.math.abs

/**
 * [1086](https://leetcode.com/problems/high-five/)
 */
fun highFive(items: Array<IntArray>): Array<IntArray> {
    val map = sortedMapOf<Int, java.util.PriorityQueue<Int>>()
    val res = mutableListOf<IntArray>()

    for ((id, score) in items) {
        map.getOrPut(id) { java.util.PriorityQueue(compareByDescending { it }) }.add(score)
    }

    for ((id, pq) in map) {
        var sum = 0
        repeat(5) { sum += pq.poll() }
        res.add(intArrayOf(id, sum / 5))
    }

    return res.toTypedArray()
}

/**
 * [1057](https://leetcode.com/problems/campus-bikes/description/)
 */
fun assignBikes(workers: Array<IntArray>, bikes: Array<IntArray>): IntArray {
    val m = workers.size;
    val n = bikes.size
    val buckets = Array(2001) { ArrayList<Pair<Int, Int>>() }

    for (i in 0..<m) {
        for (j in 0..<n) {
            val distance = abs(workers[i][0] - bikes[j][0]) + abs(workers[i][1] - bikes[j][1])
            buckets[distance].add(Pair(i, j))
        }
    }

    val res = IntArray(m) { -1 }
    val occupied = BooleanArray(n)
    for (bucket in buckets) {
        for ((i, j) in bucket) {
            if (res[i] == -1 && !occupied[j]) {
                res[i] = j
                occupied[j] = true
            }
        }
    }

    return res
}

