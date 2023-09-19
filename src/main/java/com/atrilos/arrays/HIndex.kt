package com.atrilos.arrays

/**
 * [274](https://leetcode.com/problems/h-index/)
 */
fun hIndex(citations: IntArray): Int {
    val freqArr = IntArray(1001)
    citations.forEach {
        freqArr[it]++
    }
    var maxFreq = 0
    for (i in freqArr.indices.reversed()) {
        val freq = freqArr[i]
        maxFreq += freq
        if (i <= maxFreq) return i
    }
    return 0
}