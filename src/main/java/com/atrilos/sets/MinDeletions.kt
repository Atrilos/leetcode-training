package com.atrilos.sets

/**
 * [1647](https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/)
 */
fun minDeletions(s: String): Int {
    val dictMap = mutableMapOf<Char, Int>()
    for (ch in s) dictMap.merge(ch, 1, Int::plus)
    val unique = mutableSetOf<Int>()
    var res = 0
    for ((_, freq) in dictMap) {
        unique += if (freq in unique) {
            var currentFreq = freq
            while (currentFreq in unique && currentFreq > 0) {
                res++
                currentFreq--
            }
            currentFreq
        } else {
            freq
        }
    }

    return res
}