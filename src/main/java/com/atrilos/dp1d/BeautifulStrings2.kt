package com.atrilos.dp1d

/**
 * [2949](https://leetcode.com/problems/count-beautiful-substrings-ii/description/)
 */
class BeautifulStrings2 {
    fun beautifulSubstrings(s: String, k: Int): Long {
        var balance = 0
        var v = 0
        var period = 1
        var res = 0L
        while ((period * period) % k > 0) { // Min amount of vowels(consonants) that is divisible by k
            period++
        }

        val map = mutableMapOf<Pair<Int, Int>, Int>() // balance, v % l -> amount

        map[0 to 0] = 1

        for (c in s) {
            if (c in "aeiou") {
                balance++
                v++
                v %= period
            } else {
                balance--
            }
            res += map[balance to v] ?: 0
            map[balance to v] = (map[balance to v] ?: 0) + 1
        }

        return res
    }
}