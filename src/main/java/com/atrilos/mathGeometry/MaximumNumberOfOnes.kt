package com.atrilos.mathGeometry

/**
 * [1183](https://leetcode.com/problems/maximum-number-of-ones/description/)
 */
class MaximumNumberOfOnes {
    fun maximumNumberOfOnes(width: Int, height: Int, sideLength: Int, maxOnes: Int): Int {
        var maxOnes = maxOnes
        if (width < height) {
            return maximumNumberOfOnes(height, width, sideLength, maxOnes)
        }
        val nw = width / sideLength
        val rw = width % sideLength
        val nh = height / sideLength
        val rh = height % sideLength
        var ans = (nw * nh * maxOnes + (nw + nh + 1) * minOf(maxOnes, (rw * rh)))
        maxOnes -= rw * rh
        if (maxOnes > 0) {
            ans += nw * minOf(maxOnes, ((sideLength - rw) * rh))
            maxOnes -= (sideLength - rw) * rh
            if (maxOnes > 0) {
                ans += nh * minOf(maxOnes, (rw * (sideLength - rh)))
            }
        }
        return ans
    }
}