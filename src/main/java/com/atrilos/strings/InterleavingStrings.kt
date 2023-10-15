package com.atrilos.strings

/**
 * https://leetcode.com/problems/interleaving-string/
 * 97
 */
class InterleavingStrings {
    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        if (s1.length + s2.length != s3.length) return false
        val visited = Array(s1.length + 1) { IntArray(s2.length + 1) { -1 } }
        return dfs(s1, s2, s3, 0, 0, 0, visited)
    }

    private fun dfs(
        s1: String,
        s2: String,
        s3: String,
        i: Int,
        j: Int,
        k: Int,
        visited: Array<IntArray>
    ): Boolean {
        if (i == s1.length) {
            return s2.substring(j) == s3.substring(k)
        }
        if (j == s2.length) {
            return s1.substring(i) == s3.substring(k)
        }
        if (visited[i][j] >= 0) {
            return visited[i][j] == 1
        }
        var ans = false
        if ((s1[i] == s3[k] && dfs(s1, s2, s3, i + 1, j, k + 1, visited))
            || (s2[j] == s3[k] && dfs(s1, s2, s3, i, j + 1, k + 1, visited))
        ) {
            ans = true
        }
        visited[i][j] = if (ans) 1 else 0

        return ans
    }
}