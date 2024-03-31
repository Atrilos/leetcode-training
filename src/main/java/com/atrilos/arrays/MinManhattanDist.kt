package com.atrilos.arrays

import java.util.*
import kotlin.math.abs

/**
 * [3102](https://leetcode.com/problems/minimize-manhattan-distances/description/)
 */
class MinManhattanDist {
    fun minimumDistance(points: Array<IntArray>): Int {
        val xPlusY = TreeMap<Int, Int>()
        val xMinusY = TreeMap<Int, Int>()
        for (point in points) {
            val x = point[0]
            val y = point[1]
            val s = x + y
            val d = x - y

            xPlusY.merge(s, 1, Int::plus)
            xMinusY.merge(d, 1, Int::plus)
        }

        var res = Int.MAX_VALUE
        for (point in points) {
            val x = point[0]
            val y = point[1]
            val s = x + y
            val d = x - y

            // simulate removal
            xPlusY.merge(s, -1, Int::plus)
            if (xPlusY[s] == 0) {
                xPlusY.remove(s)
            }
            xMinusY.merge(d, -1, Int::plus)
            if (xMinusY[d] == 0) {
                xMinusY.remove(d)
            }

            res = minOf(
                maxOf(
                    abs(xPlusY.lastKey() - xPlusY.firstKey()),
                    abs(xMinusY.lastKey() - xMinusY.firstKey())
                ), res
            )

            // backtrack
            xPlusY.merge(s, 1, Int::plus)
            xMinusY.merge(d, 1, Int::plus)
        }

        return res
    }
}