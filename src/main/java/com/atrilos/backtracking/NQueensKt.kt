package com.atrilos.backtracking

/**
 * [51](https://leetcode.com/problems/n-queens/)
 */
class NQueensKt {
    fun solveNQueens(n: Int): List<List<String>> {
        val res = mutableListOf<List<String>>()

        fun backtrack(
            row: Int,
            cols: MutableSet<Int>,
            diagonals: MutableSet<Int>,
            antiDiagonals: MutableSet<Int>,
            entry: MutableList<String>
        ) {
            if (row == n) {
                res += entry.toList()
                return
            }
            for (col in 0..<n) {
                val diagonal = row - col
                val antiDiagonal = row + col
                if (col in cols || diagonal in diagonals || antiDiagonal in antiDiagonals) {
                    continue
                }
                diagonals += diagonal
                antiDiagonals += antiDiagonal
                cols += col
                entry[row] = ".".repeat(col) + "Q" + ".".repeat(n - col - 1)

                backtrack(row + 1, cols, diagonals, antiDiagonals, entry)

                diagonals -= diagonal
                antiDiagonals -= antiDiagonal
                cols -= col
                entry[row] = ".".repeat(n)
            }
        }

        backtrack(0, mutableSetOf(), mutableSetOf(), mutableSetOf(), MutableList(n) { ".".repeat(n) })

        return res
    }

}

/**
 * [52](https://leetcode.com/problems/n-queens-ii/description/)
 */
class NQueens2Kt {
    fun totalNQueens(n: Int): Int {

        fun backtrack(
            row: Int,
            cols: MutableSet<Int>,
            diagonals: MutableSet<Int>,
            antiDiagonals: MutableSet<Int>
        ): Int {
            if (row == n) {
                return 1
            }
            var solutions = 0
            for (col in 0..<n) {
                val diagonal = row + col
                val antiDiagonal = row - col
                if (col in cols || diagonal in diagonals || antiDiagonal in antiDiagonals) {
                    continue
                }
                diagonals += diagonal
                antiDiagonals += antiDiagonal
                cols += col

                solutions += backtrack(row + 1, cols, diagonals, antiDiagonals)

                diagonals -= diagonal
                antiDiagonals -= antiDiagonal
                cols -= col
            }

            return solutions
        }

        return backtrack(0, mutableSetOf(), mutableSetOf(), mutableSetOf())
    }
}