package com.atrilos.heapPrioQueue

import java.util.PriorityQueue

/**
 * 2462. Total Cost to Hire K Workers
 */

fun main() {
    println(totalCost(intArrayOf(28,35,21,13,21,72,35,52,74,92,25,65,77,1,73,32,43,68,8,100,84,80,14,88,42,53,98,69,64,40,60,23,99,83,5,21,76,34), 32, 12))
}
fun totalCost(costs: IntArray, k: Int, candidates: Int): Long {
    val pqL = PriorityQueue<Int>()
    val pqR = PriorityQueue<Int>()
    var lo = 0
    var hi = costs.lastIndex
    var sum = 0L
    var count = 0
    if (2 * candidates >= costs.size) while (lo <= hi) pqL.add(costs[lo++])
    while (pqL.size < candidates && lo <= hi) pqL.add(costs[lo++])
    while (pqR.size < candidates && lo < hi) pqR.add(costs[hi--])
    while (lo <= hi && count++ < k) {
        if (pqR.peek() < pqL.peek()) {
            sum += pqR.poll()
            pqR.add(costs[hi--])
        } else {
            sum += pqL.poll()
            pqL.add(costs[lo++])
        }
    }
    while (pqR.isNotEmpty()) pqL.add(pqR.poll())
    while (count++ < k && pqL.isNotEmpty()) sum += pqL.poll()
    return sum
}