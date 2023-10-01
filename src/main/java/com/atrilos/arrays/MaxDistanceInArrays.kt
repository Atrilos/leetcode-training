package com.atrilos.arrays

/**
 * [624](https://leetcode.com/problems/maximum-distance-in-arrays/)
 */
class MaxDistanceInArrays {
    fun maxDistance(arrays: List<List<Int>>): Int {
        var minVal = arrays[0][0]
        var maxVal = arrays[0].last()
        var res = 0
        for (i in 1..arrays.lastIndex) {
            res = maxOf(res, maxVal - arrays[i][0], arrays[i].last() - minVal)
            maxVal = maxOf(maxVal, arrays[i].last())
            minVal = minOf(minVal, arrays[i][0])
        }
        return res
    }
}