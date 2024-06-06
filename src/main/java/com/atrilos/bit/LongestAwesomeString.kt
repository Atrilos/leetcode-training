package com.atrilos.bit

/**
 * [1542](https://leetcode.com/problems/find-longest-awesome-substring/description/)
 */
class LongestAwesomeString {
    fun longestAwesome(s: String): Int {
        var mask = 0

        var res = 0
        val map = mutableMapOf(0 to -1)
        for (i in s.indices) {
            val digit = s[i] - '0'
            mask = mask xor (1 shl digit)
            for (shift in 0..9) {
                val newMask = mask xor (1 shl shift)
                res = maxOf(res, i - map.getOrDefault(newMask, i))
            }
            if (mask in map) {
                res = maxOf(res, i - map[mask]!!)
            } else {
                map[mask] = i
            }
        }

        return res
    }
}