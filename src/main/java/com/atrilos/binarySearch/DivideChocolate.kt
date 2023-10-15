package com.atrilos.binarySearch

/**
 * [1231](https://leetcode.com/problems/divide-chocolate/description/)
 */
fun maximizeSweetness(sweetness: IntArray, k: Int): Int {
    val numberOfPeople = k + 1
    var lo = sweetness.minOrNull()!!
    var hi = sweetness.sum() / numberOfPeople
    while (lo < hi) {
        val mid = lo + (hi - lo + 1) / 2
        var currentSweetness = 0
        var countPpl = 0
        for (s in sweetness) {
            currentSweetness += s
            if (currentSweetness >= mid) {
                countPpl++
                currentSweetness = 0
            }
        }

        if (countPpl >= numberOfPeople) {
            lo = mid
        } else {
            hi = mid - 1
        }
    }
    return lo
}