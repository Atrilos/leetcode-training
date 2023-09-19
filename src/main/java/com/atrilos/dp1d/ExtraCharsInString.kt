package com.atrilos.dp1d

/**
 * [2707](https://leetcode.com/problems/extra-characters-in-a-string/)
 */
class ExtraCharsInString {
    private class Trie(var w: Boolean = false) : HashMap<Char, Trie>()

    fun minExtraChar(s: String, dictionary: Array<String>): Int {
        val trie = Trie()
        for (w in dictionary) {
            var t = trie
            for (c in w) t = t.getOrPut(c) { Trie() }
            t.w = true
        }
        val cache = mutableMapOf<Int, Int>()
        fun dfs(pos: Int): Int = if (pos >= s.length) 0 else
            cache.getOrPut(pos) {
                var min = 1 + dfs(pos + 1)
                var t = trie
                for (i in pos until s.length) {
                    t = t[s[i]] ?: break
                    if (t.w) min = minOf(min, dfs(i + 1))
                }
                min
            }
        return dfs(0)
    }
}