package com.atrilos.binarySearch

/**
 * 2300. Successful Pairs of Spells and Potions
 */

fun main() {
    println(
            successfulPairs(intArrayOf(8,0,8),
            intArrayOf(1,2,3,4,5),
            7).joinToString()
    )
}
fun successfulPairs(spells: IntArray, potions: IntArray, success: Long): IntArray {
    val n = spells.size
    val m = potions.size
    val sortedPotions = potions.sorted()

    val pairs = IntArray(n)

    for (i in 0 until n) {
        var lo = 0
        var hi = m

        while (lo < hi) {
            val mid = lo + (hi - lo) / 2
            if (spells[i].toLong() * sortedPotions[mid] < success) {
                lo = mid + 1
            } else {
                hi = mid
            }
        }
        pairs[i] = m - lo
    }

    return pairs
}