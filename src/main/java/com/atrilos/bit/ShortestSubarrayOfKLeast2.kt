package com.atrilos.bit

/**
 * [3097](https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii/description/)
 */
class ShortestSubarrayOfKLeast2 {
    fun minimumSubarrayLength(nums: IntArray, k: Int): Int {
        var running = 0
        val bitCount = IntArray(32)
        var res = Int.MAX_VALUE
        var l = 0

        for (r in nums.indices) {
            running = running or nums[r]
            setOr(nums, r, bitCount)
            while (running >= k && l <= r) {
                running = negateOr(nums, l, bitCount, running)
                res = minOf(res, r - l + 1)
                l++
            }
        }
        return if (res == Int.MAX_VALUE) -1 else res
    }

    private fun setOr(nums: IntArray, r: Int, bitCount: IntArray) {
        for (i in 0..31) {
            if ((nums[r] shr i) and 1 == 1) {
                bitCount[i]++
            }
        }
    }

    private fun negateOr(nums: IntArray, l: Int, bitCount: IntArray, running: Int): Int {
        var res = running
        for (i in 0..31) {
            if ((nums[l] shr i) and 1 == 1) {
                bitCount[i]--
                if (bitCount[i] == 0) {
                    res = res and ((1 shl i).inv())
                }
            }
        }
        return res
    }
}