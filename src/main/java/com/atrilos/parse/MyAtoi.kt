package com.atrilos.parse

/**
 * [8](https://leetcode.com/problems/string-to-integer-atoi/description/)
 */
fun myAtoi(s: String): Int {
    var i = 0
    while (i != s.length && s[i] == ' ') i++

    if (i == s.length) {
        return 0
    }

    var sign = 1
    if (s[i] == '-') {
        sign *= -1
        i++
    } else if (s[i] == '+') {
        i++
    }

    while (i != s.length && s[i] == '0') i++
    var res = 0L
    while (i != s.length && s[i] in '0'..'9') {
        res = res * 10 + (s[i] - '0')
        if (res.toInt().toLong() != res) {
            return if (sign == 1) Int.MAX_VALUE else Int.MIN_VALUE
        }
        i++
    }
    return res.toInt() * sign
}