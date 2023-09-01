package com.atrilos.strings

/**
 * https://leetcode.com/problems/excel-sheet-column-title/
 * 168
 */
fun main() {
    println(convertToTitle(701))
}
fun convertToTitle(columnNumber: Int): String {
    val sb = StringBuilder()
    var n = columnNumber

    while (n != 0) {
        n--
        sb.append((n % 26 + 'A'.code).toChar())
        n /= 26
    }

    return sb.reverse().toString()
}