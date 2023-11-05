package com.atrilos.systemDesign

/**
 * [271](https://leetcode.com/problems/encode-and-decode-strings/)
 */
class Codec {

    // Encodes a list of strings to a single string.
    fun encode(strs: List<String>): String {
        return strs.joinToString("") { "${it.length}#$it" }
    }


    // Decodes a single string to a list of strings.
    fun decode(s: String): List<String> {
        var index = 0
        val list = mutableListOf<String>()

        while (index < s.length) {
            var cnt = 0
            while (s[index] != '#') {
                cnt = cnt * 10 + (s[index++] - '0')
            }
            index++
            list += s.substring(index, index + cnt)
            index += cnt
        }

        return list
    }

}