package com.atrilos.binarySearch

/**
 * [1533](https://leetcode.com/problems/find-the-index-of-the-large-integer/description/)
 */
class FindIndexLargeInt {
    interface ArrayReader {
        fun compareSub(l: Int, r: Int, x: Int, y: Int): Int
        fun length(): Int
    }

    fun getIndex(reader: ArrayReader): Int {
        var (lo, hi) = 0 to (reader.length() - 1)

        while (lo < hi) {
            val mid = lo + (hi - lo) / 2
            val compare =
                if ((hi - lo) % 2 == 0) { // even elements in total, so to divide this range into two parts, include mid.
                    reader.compareSub(lo, mid, mid, hi)
                } else { // odd elements in total, we can divide this range easily into two parts
                    reader.compareSub(lo, mid, mid + 1, hi)
                }
            when (compare) {
                1 -> hi = mid
                -1 -> lo = mid + 1
                else -> return mid
            }
        }

        return lo
    }
}