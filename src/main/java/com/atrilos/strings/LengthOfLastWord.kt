package com.atrilos.strings

/**
 * [58](https://leetcode.com/problems/length-of-last-word/)
 */
fun lengthOfLastWord(s: String): Int {
    var count = 0
    for (ch in StringBuilder(s).trim().reversed()) {
        if (ch == ' ') {
            break
        }
        count++
    }
    return count
}