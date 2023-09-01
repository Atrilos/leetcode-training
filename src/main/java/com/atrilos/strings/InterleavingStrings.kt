package com.atrilos.strings

/**
 * https://leetcode.com/problems/interleaving-string/
 * 97
 */
fun main() {
    println(isInterleave("aabcc", "dbbca", "aadbbcbcac"))
}

fun isInterleave(s1: String, s2: String, s3: String): Boolean {
    val cache = mutableMapOf<Int, Boolean>()
    fun dfs(p1: Int, p2: Int): Boolean = cache.getOrPut(p1 + p2 * 100) {
        p1 < s1.length && p2 < s2.length && (
                s1[p1] == s3[p1 + p2] && dfs(p1 + 1, p2)
                        || s2[p2] == s3[p1 + p2] && dfs(p1, p2 + 1)
                )
                || p1 == s1.length && s2.substring(p2) == s3.substring(p1 + p2)
                || p2 == s2.length && s1.substring(p1) == s3.substring(p1 + p2)
    }
    return s1.length + s2.length == s3.length && dfs(0, 0)
}