package com.atrilos.backtracking

/**
 * [1087](https://leetcode.com/problems/brace-expansion/description/)
 */
class BraceExpansion {
    fun expand(s: String): Array<String> {
        val res = sortedSetOf<String>()
        val path = StringBuilder()

        fun dfs(start: Int) {
            var i = start
            var cnt = 0
            while (i < s.length && s[i] != '{') {
                path.append(s[i])
                i++
                cnt++
            }
            if (i == s.length) {
                res += path.toString()
            } else if (s[i] == '{') {
                val end = s.indexOf('}', i)
                val expandChars = s.substring(i + 1, end).split(',')
                for (ch in expandChars) {
                    path.append(ch)
                    dfs(end + 1)
                    path.setLength(path.length - 1)
                }
            }
            path.setLength(path.length - cnt)
        }

        dfs(0)

        return res.toTypedArray()
    }
}