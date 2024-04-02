package com.atrilos.backtracking

/**
 * [10](https://leetcode.com/problems/regular-expression-matching/description/)
 */
class RegexMatching {

    // Backtrack
    fun isMatchBacktrack(s: String, p: String): Boolean {
        return dfs(0, 0, s, p)
    }

    private fun dfs(i: Int, j: Int, s: String, p: String): Boolean {
        if (i >= s.length && j >= p.length) {
            return true
        }
        if (j >= p.length) {
            return false
        }

        val match = ((i < s.length) && ((s[i] == p[j]) || (p[j] == '.')))

        if ((j + 1 < p.length) && (p[j + 1] == '*')) {
            return dfs(i, j + 2, s, p) || (match && dfs(i + 1, j, s, p))
        }

        if (match) {
            return dfs(i + 1, j + 1, s, p)
        }
        return false
    }

    // DP
    fun isMatchDp(s: String, p: String): Boolean {
        val lenS = s.length
        val lenP = p.length

        val dp = Array(lenS + 1) { BooleanArray(lenP + 1) { false } }
        dp[0][0] = true

        for (idxP in 0 until lenP) {
            if (p[idxP] == '*' && dp[0][idxP - 1]) {
                dp[0][idxP + 1] = true
            }
        }

        for (idxS in 0 until lenS) {
            for (idxP in 0 until lenP) {
                if (p[idxP] == '.' || p[idxP] == s[idxS]) {
                    dp[idxS + 1][idxP + 1] = dp[idxS][idxP]
                } else if (p[idxP] == '*') {
                    if (p[idxP - 1] != s[idxS] && p[idxP - 1] != '.') {
                        dp[idxS + 1][idxP + 1] = dp[idxS + 1][idxP - 1]
                    } else {
                        dp[idxS + 1][idxP + 1] = (dp[idxS + 1][idxP] || dp[idxS][idxP + 1] || dp[idxS + 1][idxP - 1])
                    }
                }
            }
        }

        return dp[lenS][lenP]
    }
}

/**
 * [44](https://leetcode.com/problems/wildcard-matching/description/)
 */
class RegexMatchingWildcard {

    fun isMatch(s: String, p: String): Boolean {
        val lenS = s.length
        val lenP = p.length
        val dp = Array(lenS + 1) { BooleanArray(lenP + 1) }
        dp[0][0] = true

        for (idxP in 1..lenP) {
            if (p[idxP - 1] == '*') {
                dp[0][idxP] = dp[0][idxP - 1]
            }
        }

        for (idxS in 1..lenS) {
            for (idxP in 1..lenP) {
                if (s[idxS - 1] == p[idxP - 1] || p[idxP - 1] == '?') {
                    dp[idxS][idxP] = dp[idxS - 1][idxP - 1]
                } else if (p[idxP - 1] == '*') {
                    dp[idxS][idxP] = dp[idxS - 1][idxP] || dp[idxS][idxP - 1]
                }
            }
        }

        return dp.last().last()
    }
}