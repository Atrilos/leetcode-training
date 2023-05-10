package com.atrilos.arrays

fun main() {
    println(reverseWords("a good   example"))
}
fun reverseWords(s: String): String {
    val trim = s.trim()
    val split = trim
        .split("\\s+".toRegex())
    val reversed = split
        .reversed()
    val joinToString = reversed
        .joinToString(" ")
    return joinToString
}