package com.atrilos.strings

/**
 * [14](https://leetcode.com/problems/longest-common-prefix/)
 */
fun longestCommonPrefix(strs: Array<String>): String {
    val sb = StringBuilder()

    for (i in 0..strs[0].lastIndex) {
        val ch = strs[0][i]
        for (j in 1..strs.lastIndex) {
            if (i > strs[j].lastIndex || ch != strs[j][i]) {
                return sb.toString()
            }
        }
        sb.append(ch)
    }

    return sb.toString()
}

fun convert(s: String, numRows: Int): String {
    if (numRows == 1) {
        return s
    }

    val result = StringBuilder()
    val n = s.length
    val interval = 2 * numRows - 2

    for (i in 0 until numRows) {
        for (j in i until n step interval) {
            result.append(s[j])
            if (i != 0 && i != numRows - 1 && j + interval - 2 * i < n) {
                result.append(s[j + interval - 2 * i])
            }
        }
    }

    return result.toString()
}

fun main() {
    println(convert("windlord", 3))
}