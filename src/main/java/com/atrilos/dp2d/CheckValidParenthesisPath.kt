package com.atrilos.dp2d


/**
 * [2267](https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path/description/)
 */
class CheckValidParenthesisPath {
    fun hasValidPath(grid: Array<CharArray>): Boolean {
        if (grid[0][0] == ')') {
            return false
        }
        if (grid.last().last() == '(') {
            return false
        }
        val n = grid.size
        val m = grid[0].size
        val seen = Array(n) { Array(m) { BooleanArray(maxOf(n, m) + 1) } }
        return dfs(0, grid, 0, 0, seen)
    }

    private fun dfs(count: Int, grid: Array<CharArray>, i: Int, j: Int, seen: Array<Array<BooleanArray>>): Boolean {
        val n = grid.size
        val m = grid[0].size

        if (i >= n || j >= m) {
            return false
        }
        val nextCount = count + (if (grid[i][j] == '(') 1 else -1)
        if (nextCount < 0 || nextCount > (n + m) / 2 || seen[i][j][nextCount]) {
            return false
        }
        if (i == grid.lastIndex && j == grid[0].lastIndex) {
            return nextCount == 0
        }

        seen[i][j][nextCount] = true
        var res = false
        res = res or dfs(nextCount, grid, i + 1, j, seen)
        res = res or dfs(nextCount, grid, i, j + 1, seen)

        return res
    }
}