package com.atrilos.arrays

/**
 * [3086](https://leetcode.com/problems/minimum-moves-to-pick-k-ones/description/)
 */
class MinMovesToPickKOnes {

    fun minimumMoves(nums: IntArray, k: Int, maxChanges: Int): Long {
        val arr = mutableListOf(0L)

        for (i in nums.indices) {
            if (nums[i] > 0) {
                arr += arr.last() + i
            }
        }

        val n = arr.lastIndex
        val m = maxOf(0, k - maxChanges)
        var res = Long.MAX_VALUE

        for (l in m..minOf(m + 3, k)) {
            for (i in 0..n - l) {
                val mid1 = i + l / 2
                val mid2 = i + l - l / 2
                val cur = arr[i + l] - arr[mid2] + arr[i] - arr[mid1]
                res = minOf(res, (cur + (k - l) * 2))
            }
        }

        return res
    }

}