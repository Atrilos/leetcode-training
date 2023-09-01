package com.atrilos.intervals

/**
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 * 452
 */

fun findMinArrowShots(points: Array<IntArray>): Int {
    points.sortWith(compareBy { it[0] })
    var res = 1
    var end = points[0][1]

    for (i in 1..points.lastIndex) {
        val balloon = points[i]
        end = if (balloon[0] > end) {
            res++
            balloon[1]
        } else {
            minOf(end, balloon[1])
        }
    }

    return res
}