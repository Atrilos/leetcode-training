package com.atrilos.dp2d

/**
 * [329](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/description/)
 */
class LongestIncreasingPathInMatrix {
    fun longestIncreasingPath(matrix: Array<IntArray>): Int {
        val m = matrix.size
        val n = matrix[0].size
        val memo = Array(m) { IntArray(n) { -1 } }

        fun dfs(row: Int, col: Int, prev: Int = -1): Int {
            if (row < 0 || col < 0 || row >= m || col >= n) return 0
            if (matrix[row][col] <= prev) return 0
            if (memo[row][col] != -1) return memo[row][col]

            val value = matrix[row][col]

            val up = dfs(row - 1, col, value)
            val down = dfs(row + 1, col, value)
            val left = dfs(row, col - 1, value)
            val right = dfs(row, col + 1, value)

            memo[row][col] = 1 + maxOf(up, down, maxOf(left, right))

            return memo[row][col]
        }

        var max = Int.MIN_VALUE
        for (row in matrix.indices) {
            for (col in matrix[row].indices) {
                max = maxOf(max, dfs(row, col))
            }
        }

        return max
    }
}