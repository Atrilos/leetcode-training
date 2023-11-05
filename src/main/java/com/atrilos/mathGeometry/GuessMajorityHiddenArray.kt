package com.atrilos.mathGeometry

/**
 * [1538](https://leetcode.com/problems/guess-the-majority-in-a-hidden-array/description/)
 */
class GuessMajorityHiddenArray {
    private var cntEqual = 1
    private var cntDiffer = 0
    private var indexDiffer = -1

    private fun f(equal: Boolean, i: Int) {
        if (equal) {
            cntEqual++
        } else {
            cntDiffer++
            indexDiffer = i
        }
    }

    fun guessMajority(reader: ArrayReader): Int {
        val n = reader.length()
        val query0123 = reader.query(0, 1, 2, 3)
        val query1234 = reader.query(1, 2, 3, 4)
        f(query1234 == query0123, 4)
        for (i in 5 until n) {
            f(reader.query(1, 2, 3, i) == query0123, i)
        }
        f(reader.query(0, 2, 3, 4) == query1234, 1)
        f(reader.query(0, 1, 3, 4) == query1234, 2)
        f(reader.query(0, 1, 2, 4) == query1234, 3)
        return if (cntEqual > cntDiffer) 0 else if (cntDiffer > cntEqual) indexDiffer else -1
    }

    interface ArrayReader {
        fun query(a: Int, b: Int, c: Int, d: Int): Int

        fun length(): Int
    }
}