package com.atrilos.arrays

/**
 * [2391](https://leetcode.com/problems/minimum-amount-of-time-to-collect-garbage/)
 */
class MinTimeToCollectGarbage {
    fun garbageCollection(garbage: Array<String>, travel: IntArray): Int {
        var sum = 0
        var mul = 0
        var p = 0
        var g = 0
        var m = 0

        for (i in (1..garbage.lastIndex).reversed()) {
            sum += garbage[i].length

            if (mul < 3) {
                for (garbageType in garbage[i]) {
                    when (garbageType) {
                        'P' -> {
                            mul = if (p == 0) mul + 1 else mul
                            p++
                        }
                        'G' -> {
                            mul = if (g == 0) mul + 1 else mul
                            g++
                        }
                        'M' -> {
                            mul = if (m == 0) mul + 1 else mul
                            m++
                        }
                        else -> {
                        }
                    }
                }
            }

            sum += mul * travel[i - 1]
        }

        sum += garbage[0].length
        return sum
    }
}