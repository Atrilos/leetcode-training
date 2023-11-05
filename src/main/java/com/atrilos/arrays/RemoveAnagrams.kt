package com.atrilos.arrays

/**
 * [2273](https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/description/)
 */
class RemoveAnagrams {
    fun removeAnagrams(words: Array<String>): List<String> {
        val res = mutableListOf(words[0])
        for (i in 1..words.lastIndex) {
            if (!isAnagram(words[i - 1], words[i])) {
                res += words[i]
            }
        }
        return res
    }

    private fun isAnagram(s1: String, s2: String): Boolean {
        if (s1.length != s2.length) {
            return false
        }
        val count = IntArray(26)
        for (i in s1.indices) {
            count[s1[i] - 'a']++
            count[s2[i] - 'a']--
        }

        for (freq in count) {
            if (freq != 0) {
                return false
            }
        }

        return true
    }
}