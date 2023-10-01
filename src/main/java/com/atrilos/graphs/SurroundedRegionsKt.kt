package com.atrilos.graphs

/**
 * [130](https://leetcode.com/problems/surrounded-regions/description/)
 */
class SurroundedRegionsKt {
    fun solve(board: Array<CharArray>) {
        val dirs = arrayOf(0 to 1, 0 to -1, -1 to 0, 1 to 0)

        fun dfs(i: Int, j: Int) {
            if (i !in board.indices || j !in board[0].indices) return
            if (board[i][j] == 'O') {
                board[i][j] = 'Z'
                dirs.forEach { dfs(i + it.first, j + it.second) }
            }
        }
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (i == 0 || j == 0 || i == board.lastIndex || j == board[i].lastIndex) {
                    dfs(i, j)
                }
            }
        }
        for (i in board.indices) {
            for (j in board[i].indices) {
                when (board[i][j]) {
                    'O' -> board[i][j] = 'X'
                    'Z' -> board[i][j] = 'O'
                }
            }
        }
    }
}