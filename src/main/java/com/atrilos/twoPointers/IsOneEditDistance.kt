package com.atrilos.twoPointers


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