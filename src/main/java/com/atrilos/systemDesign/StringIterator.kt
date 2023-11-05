package com.atrilos.systemDesign

/**
 * [604](https://leetcode.com/problems/design-compressed-string-iterator/)
 */
class StringIterator(val compressedString: String) {

    private var currentChar = ' '
    private var cnt = 0
    private var index = 0

    fun next(): Char {
        if (!hasNext()) {
            return ' '
        }
        if (cnt == 0) {
            currentChar = compressedString[index++]
            while (index < compressedString.length && compressedString[index].isDigit()) {
                cnt = cnt * 10 + compressedString[index++].digitToInt()
            }
        }
        cnt--
        return currentChar
    }

    fun hasNext(): Boolean {
        return index != compressedString.length || cnt != 0
    }
}

fun main() {
    val stringIterator = StringIterator("L10e2t1C1o1d1e11")
    while (stringIterator.hasNext()) {
        print(stringIterator.next())
    }
}