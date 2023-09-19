package com.atrilos.strings

import java.util.*

/**
 * https://leetcode.com/problems/repeated-substring-pattern/
 * 459
 */

// 1. Naive
/**
fun repeatedSubstringPattern(s: String): Boolean {
if (s.length == 1) return false
val sb = StringBuilder()
for (i in 0..s.lastIndex / 2) {
sb.append(s[i])
if ((s.length - i - 1) % (i + 1) == 0 && checkWithCurrent(s, i + 1, sb)) return true
}
return false
}

fun checkWithCurrent(s: String, index: Int, pattern: StringBuilder): Boolean {
var i = index
while (i <= s.lastIndex) {
for (j in pattern.indices) {
if (s[i] != pattern[j]) return false
i++
}
}
return true
}
 **/

// 2.KMP

fun repeatedSubstringPattern(s: String): Boolean {
    val n = s.length
    val lps = lpsOf(s)
    return lps[n - 1] > 0
            && n % (n - lps[n - 1]) == 0
}

private fun lpsOf(s: String): IntArray {
    val lps = IntArray(s.length)
    var i = 1
    var prevLPS = 0
    while (i < s.length) {
        if (s[i] == s[prevLPS]) {
            lps[i++] = ++prevLPS
        } else {
            if (prevLPS != 0) prevLPS = lps[prevLPS - 1]
            else i++
        }
    }
    return lps
}