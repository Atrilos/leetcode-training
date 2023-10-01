package com.atrilos.twoPointers

import kotlin.math.pow


/**
 * [161](https://leetcode.com/problems/one-edit-distance/description/)
 */
fun isOneEditDistance(s: String, t: String): Boolean {
    if (s.length > t.length) return isOneEditDistance(t, s)
    if (t.length - s.length >= 2) return false

    for (i in s.indices) {
        if (s[i] != t[i]) {
            return if (s.length == t.length) {
                s.substring(i + 1) == t.substring(i + 1)
            } else {
                s.substring(i) == t.substring(i + 1)
            }
        }
    }
    return s.length + 1 == t.length
}

fun findRepeatedDnaSequences(s: String): List<String> {
    val len = 10
    val n = s.length
    if (n <= len) return emptyList()
    val b = 4.0
    val bPow = b.pow(len).toInt()
    val dict = mapOf('A' to 0, 'C' to 1, 'G' to 2, 'T' to 3)
    val nums = s.map { dict[it]!! }
    var bitmask = 0
    val seen = HashSet<Int>()
    val output = HashSet<String>()
    for (start in 0 until n - len + 1) {
        if (start != 0) {
            bitmask = bitmask shl 2
            bitmask = bitmask or nums[start + len - 1]
            bitmask = bitmask and (3 shl 2 * len).inv()
        } else {
            for (i in 0 until len) {
                bitmask = bitmask shl 2
                bitmask = bitmask or nums[i]
            }
        }
        if (seen.contains(bitmask)) output.add(s.substring(start, start + len))
        seen.add(bitmask)
    }
    return output.toList()
}
