package com.atrilos.dp1d

/**
 * https://leetcode.com/problems/min-cost-climbing-stairs/
 * 746
 */
fun minCostClimbingStairs(cost: IntArray): Int {
    var f = cost[0]
    var s = cost[1]
    var current: Int
    for (i in 2..cost.lastIndex) {
        current = cost[i] + Math.min(f, s)
        f = s
        s = current
    }

    return Math.min(f, s)
}