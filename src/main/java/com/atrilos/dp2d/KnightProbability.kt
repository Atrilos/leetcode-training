package com.atrilos.dp2d

/**
 * [688](https://leetcode.com/problems/knight-probability-in-chessboard/description/)
 */
class KnightProbability {
    fun knightProbability(n: Int, k: Int, row: Int, column: Int): Double {
        // Define possible directions for the knight's moves
        val directions = arrayOf(
            intArrayOf(1, 2), intArrayOf(1, -2), intArrayOf(-1, 2), intArrayOf(-1, -2),
            intArrayOf(2, 1), intArrayOf(2, -1), intArrayOf(-2, 1), intArrayOf(-2, -1)
        )

        // Initialize the dynamic programming table
        val dp = Array(k + 1) { Array(n) { DoubleArray(n) } }
        dp[0][row][column] = 1.0

        // Iterate over the number of moves
        for (moves in 1..k) {
            // Iterate over the cells on the chessboard
            for (i in 0 until n) {
                for (j in 0 until n) {
                    // Iterate over possible directions
                    for (direction in directions) {
                        val prevI = i - direction[0]
                        val prevJ = j - direction[1]
                        // Check if the previous cell is within the chessboard
                        if (prevI in 0..<n && prevJ in 0..<n) {
                            // Add the previous probability divided by 8
                            dp[moves][i][j] += dp[moves - 1][prevI][prevJ] / 8.0
                        }
                    }
                }
            }
        }

        // Calculate total probability by summing probabilities for all cells
        var totalProbability = 0.0
        for (i in 0 until n) {
            for (j in 0 until n) {
                totalProbability += dp[k][i][j]
            }
        }
        return totalProbability
    }
}