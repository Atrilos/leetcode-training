package com.atrilos.backtracking

import kotlin.math.abs

/**
 * [723](https://leetcode.com/problems/candy-crush/)
 */
object CandyCrash {
    fun candyCrush(board: Array<IntArray>): Array<IntArray> {
        while (findAndCrush(board)) {
            gravity(board)
        }
        return board
    }

    private fun findAndCrush(board: Array<IntArray>): Boolean {
        var isCrushed = false
        for (i in 1..<board.lastIndex) {
            for (j in board[i].indices) {
                if (abs(board[i][j]) != 0
                        && abs(board[i - 1][j]) == abs(board[i][j])
                        && abs(board[i + 1][j]) == abs(board[i][j])) {
                    board[i - 1][j] = -abs(board[i][j])
                    board[i][j] = -abs(board[i][j])
                    board[i + 1][j] = -abs(board[i][j])
                }
            }
        }

        for (i in board.indices) {
            for (j in 1..<board[i].lastIndex) {
                if (abs(board[i][j]) != 0
                        && abs(board[i][j - 1]) == abs(board[i][j])
                        && abs(board[i][j + 1]) == abs(board[i][j])) {
                    board[i][j - 1] = -abs(board[i][j])
                    board[i][j] = -abs(board[i][j])
                    board[i][j + 1] = -abs(board[i][j])
                }
            }
        }

        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] < 0) {
                    board[i][j] = 0
                    isCrushed = true
                }
            }
        }

        return isCrushed
    }

    private fun gravity(board: Array<IntArray>) {
        for (j in board[0].indices) {
            var bottom = -1
            for (i in board.indices.reversed()) {
                if (board[i][j] == 0) {
                    bottom = maxOf(bottom, i)
                } else if (bottom != -1) {
                    board[bottom][j] = board[i][j]
                    board[i][j] = 0
                    bottom--
                }
            }
        }
    }

}

fun main() {
    println(
            CandyCrash.candyCrush(
                    board = arrayOf(
                            intArrayOf(110, 5, 112, 113, 114),
                            intArrayOf(210, 211, 5, 213, 214),
                            intArrayOf(310, 311, 3, 313, 314),
                            intArrayOf(410, 411, 412, 5, 414),
                            intArrayOf(5, 1, 512, 3, 3),
                            intArrayOf(610, 4, 1, 613, 614),
                            intArrayOf(710, 1, 2, 713, 714),
                            intArrayOf(810, 1, 2, 1, 1),
                            intArrayOf(1, 1, 2, 2, 2),
                            intArrayOf(4, 1, 4, 4, 1014)
                    )
            ).contentDeepToString()
    )
}