package com.atrilos.twoPointers

/**
 * [345 leetcode](https://leetcode.com/problems/reverse-vowels-of-a-string/description/)
 */
fun reverseVowels(s: String): String {
    val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
    var l = 0
    var r = s.lastIndex
    val sArray = s.toCharArray()

    while (l < r) {
        while (l < r && sArray[l] !in vowels) {
            l++
        }
        while (l < r && sArray[r] !in vowels) {
            r--
        }
        if (l < r) {
            sArray[l] = sArray[r].also { sArray[r] = sArray[l] }
            l++
            r--
        }
    }

    return String(sArray)
}

fun romanToInt(s: String): Int {
    val map = mutableMapOf(
            'I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000
    )
    var prev = s[0]
    var sum = 0
    for (ch in s) {
        sum += map[ch] ?: 0
        if (((ch == 'V' || ch == 'X') && prev == 'I')
                || ((ch == 'L' || ch == 'C') && prev == 'X')
                || ((ch == 'D' || ch == 'M') && prev == 'C')) {
            sum -= 2 * (map[prev] ?: 0)
        }
        prev = ch
    }

    return sum
}

fun main() {
    println(romanToInt("MCMXCIV"))
}