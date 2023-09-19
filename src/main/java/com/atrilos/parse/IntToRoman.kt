package com.atrilos.parse

/**
 * [12](https://leetcode.com/problems/integer-to-roman/description/)
 */
fun intToRoman(given: Int): String {
    var num = given
    val sb = StringBuilder()
    val dict = listOf(
            1000 to "M", 900 to "CM", 500 to "D", 400 to "CD", 100 to "C", 90 to "XC", 50 to "L",
            40 to "XL", 10 to "X", 9 to "IX", 5 to "V", 4 to "IV", 1 to "I",
    )

    var i = 0
    while (num > 0) {
        val (div, str) = dict[i]
        if (num >= div) {
            sb.append(str)
            num -= div
        } else {
            i++
        }
    }

    return sb.toString()
}