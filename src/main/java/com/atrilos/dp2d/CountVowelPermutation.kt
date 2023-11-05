package com.atrilos.dp2d

import java.lang.IllegalArgumentException

/**
 * [1220](https://leetcode.com/problems/count-vowels-permutation/description/)
 */
class CountVowelPermutation {
    fun countVowelPermutation(n: Int): Int {
        val mod = 1000000007
        var aCount = 1L
        var eCount = 1L
        var iCount = 1L
        var oCount = 1L
        var uCount = 1L
        repeat(n - 1) {
            val newA = (eCount + iCount + uCount) % mod
            val newE = (aCount + iCount) % mod
            val newI = (eCount + oCount) % mod
            val newO = iCount
            val newU = (oCount + iCount) % mod

            aCount = newA
            eCount = newE
            iCount = newI
            oCount = newO
            uCount = newU
        }
        return ((aCount + eCount + iCount + oCount + uCount) % mod).toInt()
    }
}

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