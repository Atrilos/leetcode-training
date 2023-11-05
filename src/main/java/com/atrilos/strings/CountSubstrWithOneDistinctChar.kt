package com.atrilos.strings

/**
 * [1180](https://leetcode.com/problems/count-substrings-with-only-one-distinct-letter/)
 */
class CountSubstrWithOneDistinctChar {
    fun countLetters(s: String): Int {
        var total = 0
        var cnt = 0
        for (i in 1..s.lastIndex) {
            if (s[i - 1] == s[i]) {
                cnt++
            } else {
                cnt = 1
            }
            total += cnt
        }
        return total
    }
}