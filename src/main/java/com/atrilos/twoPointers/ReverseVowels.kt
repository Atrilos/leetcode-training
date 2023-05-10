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