package com.atrilos.systemDesign

import kotlin.math.abs

/**
 * [348](https://leetcode.com/problems/design-tic-tac-toe/description/)
 */
class TicTacToe(val n: Int) {

    private val rows = IntArray(n)
    private val cols = IntArray(n)
    private var diagonal = 0
    private var antiDiagonal = 0

    fun move(row: Int, col: Int, player: Int): Int {
        val playerSum = if (player == 2) -1 else 1
        rows[row] += playerSum
        cols[col] += playerSum
        if (row == col) {
            diagonal += playerSum
        }
        if (col == n - 1 - row) {
            antiDiagonal += playerSum
        }
        if (abs(rows[row]) == n || abs(cols[col]) == n || abs(antiDiagonal) == n || abs(diagonal) == n) {
            return player
        }
        return 0
    }
}

fun main() {
    val ticTacToe = TicTacToe(2)
    println(ticTacToe.move(0, 0, 2))
    println(ticTacToe.move(1, 1, 1))
    println(ticTacToe.move(0, 1, 2))
}