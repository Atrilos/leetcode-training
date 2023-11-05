package com.atrilos.bit

import java.lang.IllegalArgumentException

/**
 * [1356](https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/description/)
 */
class SortIntegerByHammingWeight {
    fun sortByBits(arr: IntArray): IntArray {
        return arr.sortedWith(CustomComparator()).toIntArray()
    }

    class CustomComparator: Comparator<Int> {
        override fun compare(a: Int?, b: Int?): Int {
            if (a == null || b == null) throw IllegalArgumentException()
            val weightDiff = findWeight(a) - findWeight(b)
            return if (weightDiff != 0) {
                weightDiff
            } else {
                a - b
            }
        }

        private fun findWeight(num: Int): Int {
            var num = num
            var weight = 0
            while (num != 0) {
                num = num and (num - 1)
                weight++
            }
            return weight
        }

    }
}