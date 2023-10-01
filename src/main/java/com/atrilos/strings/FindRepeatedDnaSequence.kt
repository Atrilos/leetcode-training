package com.atrilos.strings


/**
 * [187](https://leetcode.com/problems/repeated-dna-sequences/description/)
 */
class FindRepeatedDnaSequence {
    private val len = 10 // length of substring
    private val b = 4 // radix
    private val toNum = mapOf('A' to 0, 'C' to 1, 'G' to 2, 'T' to 3)


    fun findRepeatedDnaSequences(s: String): List<String> {
        val n = s.length
        if (n <= len) {
            return emptyList()
        }

        var h = hash(s, 0, len) // hash the substring using base-4 numeral system
        val seen = HashSet<Int>()
        val output = HashSet<String>()
        seen.add(h)
        var bPow = 1
        for (i in 0 until len - 1) {
            bPow *= b
        }
        for (i in 1 until n - len + 1) {
            h = (h - toNum[s[i - 1]]!! * bPow) * b + toNum[s[i + 9]]!!

            // if we have seen the current rolling hash before, we know that the
            // current substring has been seen, without checking for collision.
            // The reason is that using base-4 nuermal system, there is one-to-one
            // relationship between a substring and a hash, and no room for collision
            // unlike when using other hashing mechanisms such as modular hashing, for example.
            if (seen.contains(h)) {
                output.add(s.substring(i, i + len))
            }
            seen.add(h)
        }
        return output.toList()
    }

    private fun hash(s: String, start: Int, end: Int): Int {
        var ret = 0
        for (i in start until end) {
            ret = ret * b + toNum[s[i]]!!
        }
        return ret
    }
}