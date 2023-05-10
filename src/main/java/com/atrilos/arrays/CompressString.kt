package com.atrilos.arrays

fun compress(chars: CharArray): Int {
    var cnt = 1
    var res = 0

    for (i in chars.indices) {
        val current = chars[i]
        val next = chars[(i + 1).coerceIn(chars.indices)]

        if (current == next && i != chars.lastIndex) {
            cnt++
        } else {
            chars[res++] = current
            if (cnt > 1) {
                cnt.toString().toCharArray().forEach { chars[res++] = it }
            }
            cnt = 1
        }
    }

    return res
}
