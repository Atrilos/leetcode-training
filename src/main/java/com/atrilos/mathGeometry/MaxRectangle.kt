package com.atrilos.mathGeometry


/**
 * [85](https://leetcode.com/problems/maximal-rectangle/description/)
 */
class MaxRectangle {
    private companion object {
        private const val ONE = '1'
    }

    fun maximalRectangle(matrix: Array<CharArray>): Int {
        // Check if the matrix is empty (no rows or columns)
        if (matrix.isEmpty() || matrix[0].isEmpty()) return 0

        // Get the number of columns from the first row (assuming all rows have the same length)
        val nCols = matrix[0].size

        // Array to store heights of contiguous "1"s for each column in the current row
        val heights = IntArray(nCols) { 0 }

        var max = 0

        for (row in matrix) {
            for ((idxCol, value) in row.withIndex()) {
                if (value == ONE) {
                    ++heights[idxCol]
                } else {
                    heights[idxCol] = 0
                }
            }

            max = maxOf(max, getMaxArea(heights))
        }

        return max
    }

    private fun getMaxArea(heights: IntArray): Int {
        val nHeights = heights.size

        // Stack to keep track of indices with potentially decreasing heights
        val stack = java.util.ArrayDeque<Int>()
        var max = 0

        for (idx in 0..<nHeights) {
            // Maintain increasing stack
            while (stack.isNotEmpty() && heights[idx] < heights[stack.peekLast()]) {
                val shortest = heights[stack.removeLast()]
                val width = idx - if (stack.isEmpty()) 0 else (stack.peekLast() + 1)
                val area = shortest * width
                max = maxOf(max, area)
            }

            stack.addLast(idx)
        }

        // Calculate remaining heights from last to first
        while (stack.isNotEmpty()) {
            val shortest = heights[stack.removeLast()]
            val width = nHeights - if (stack.isEmpty()) 0 else (stack.peekLast() + 1)
            val area = shortest * width
            max = maxOf(max, area)
        }

        return max
    }
}

fun main() {
    val matrix = arrayOf(
        charArrayOf('1', '0', '1', '0', '0'),
        charArrayOf('1', '0', '1', '1', '1'),
        charArrayOf('1', '1', '1', '1', '1'),
        charArrayOf('1', '0', '0', '1', '0')
    )
    println(MaxRectangle().maximalRectangle(matrix))
}
