package com.atrilos.binarySearch

/**
 * 875. Koko Eating Bananas
 */

fun minEatingSpeed(piles: IntArray, h: Int): Int {
    var lo = 1
    var hi: Int = piles.max()!!

    while (lo < hi) {
        val mid = lo + (hi - lo) / 2
        var estimate = 0
        piles.forEach { estimate += Math.ceil(it.toDouble() / mid).toInt() }
        when {
            estimate <= h -> hi = mid
            estimate > h -> lo = mid + 1
        }
    }

    return lo
}
