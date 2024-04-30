package com.atrilos.backtracking

/**
 * [473](https://leetcode.com/problems/matchsticks-to-square/description/)
 */
class MatchsticksToSquare {
    fun makesquare(matchsticks: IntArray): Boolean {
        if (matchsticks.size < 4) {
            return false
        }

        val maxLength = matchsticks.sum() / 4

        if (matchsticks.sum() % 4 != 0) {
            return false
        }

        matchsticks.sortDescending()

        val sides = IntArray(4) { 0 }

        fun backtrack(index: Int): Boolean {
            if (index == matchsticks.size) {
                return sides[0] == maxLength && sides[1] == maxLength && sides[2] == maxLength
            }
            for (i in 0..3) {
                if (sides[i] + matchsticks[index] > maxLength) {
                    continue
                }
                sides[i] += matchsticks[index]

                if (backtrack(index + 1)) {
                    return true
                }
                sides[i] -= matchsticks[index]
                if (sides[i] == 0) { // optimization to avoid duplicate paths
                    break
                }
            }
            return false
        }

        return backtrack(0)
    }
}