package com.atrilos.bit

/**
 * [2939](https://leetcode.com/problems/maximum-xor-product/description/)
 */
class MaximumXorProduct {

    fun maximumXorProduct(a: Long, b: Long, n: Int): Int {
        val MOD = 1_000_000_007L
        val base = (a xor b)
        var mask = (1L shl n)
        var aL = (a and (-1L xor (mask - 1)))
        var bL = (b and (-1L xor (mask - 1)))

        mask = mask shr 1
        var doA = true
        if (aL > bL) {
            doA = false
        } else {
            if (bL > aL) {
                val temp = bL
                bL = aL
                aL = temp
                doA = false
            }
        }

        while (mask > 0) {
            if (base and mask == 0L) {
                aL = aL or mask
                bL = bL or mask
            } else {
                if (doA) {
                    aL = aL or mask
                    doA = false
                } else {
                    bL = bL or mask
                }
            }
            mask = mask shr 1
        }
        val res = (aL % MOD) * (bL % MOD)

        return (res % MOD).toInt()
    }
}
