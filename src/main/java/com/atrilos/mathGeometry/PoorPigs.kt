package com.atrilos.mathGeometry

import kotlin.math.pow

/**
 * [458](https://leetcode.com/problems/poor-pigs/)
 */
fun poorPigs(buckets: Int, minutesToDie: Int, minutesToTest: Int): Int {
    val state = minutesToTest / minutesToDie + 1.0
    var cnt = 0.0
    while (state.pow(cnt) < buckets) {
        cnt++
    }
    return cnt.toInt()
}