package com.atrilos.sets

fun closeStrings(word1: String, word2: String): Boolean {
    val f: (String) -> Map<Char, Int> = { str -> str.groupingBy { it }.eachCount() }
    val (g1, g2) = f(word1) to f(word2)

    return g1.keys == g2.keys && g1.values.sorted() == g2.values.sorted()
}