package com.atrilos.matrix

/**
 * [289](https://leetcode.com/problems/game-of-life/)
 */
fun gameOfLife(board: Array<IntArray>) {
    val nextState = Array(board.size) { IntArray(board[0].size) }
    val dirs = arrayOf(1 to 0, 1 to 1, 1 to -1, 0 to 1, 0 to -1, -1 to -1, -1 to 0, -1 to 1)
    for (i in board.indices) {
        for (j in board[i].indices) {
            var neighbors = 0
            for ((ii, jj) in dirs) {
                val newI = i + ii
                val newJ = j + jj
                if (newI !in board.indices || newJ !in board[i].indices) continue
                if (board[newI][newJ] == 1) neighbors++
            }
            if (board[i][j] == 0 && neighbors == 3) {
                nextState[i][j] = 1
            } else if (board[i][j] == 1) {
                nextState[i][j] = when {
                    neighbors == 2 || neighbors == 3 -> 1
                    neighbors > 3 -> 0
                    else -> 0
                }
            }
        }
    }
    for (i in board.indices) {
        for (j in board[i].indices) {
            board[i][j] = nextState[i][j]
        }
    }
}