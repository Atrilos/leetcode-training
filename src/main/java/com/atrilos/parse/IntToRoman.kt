package com.atrilos.parse

/**
 * [12](https://leetcode.com/problems/integer-to-roman/description/)
 */
fun intToRoman(num: Int): String {
    val romanNumerals = linkedMapOf(
        "M" to 1000, "CM" to 900,
        "D" to 500, "CD" to 400,
        "C" to 100, "XC" to 90,
        "L" to 50, "XL" to 40,
        "X" to 10, "IX" to 9,
        "V" to 5, "IV" to 4,
        "I" to 1
    )
    var number = num
    val sb = StringBuilder()
    for (i in romanNumerals) {
        while (number >= i.value) {
            sb.append(i.key)
            number -= i.value
        }
    }
    return sb.toString()
}