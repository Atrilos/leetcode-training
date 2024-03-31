package com.atrilos.matrix

/**
 * [1074](https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/description/)
 */
class NumSubmatrixSumTarget {
    fun numSubmatrixSumTarget(matrix: Array<IntArray>, target: Int): Int {
        val n = matrix.size - 1
        val m = matrix[0].size - 1

        val prefixSum = buildPrefixSumMatrix(matrix, n, m)

        var result = 0
        for (y1 in 0..n) {
            for (x1 in 0..m) {
                val t1 = if (y1 > 0 && x1 > 0) target - prefixSum[y1 - 1][x1 - 1] else target
                for (y2 in y1..n) {
                    val t2 = if (x1 > 0) t1 + prefixSum[y2][x1 - 1] else t1
                    for (x2 in x1..m) {
                        var t3 = t2
                        if (y1 > 0) t3 += prefixSum[y1 - 1][x2]
                        if (t3 == prefixSum[y2][x2]) result++
                    }
                }
            }
        }
        return result
    }

    private fun buildPrefixSumMatrix(matrix: Array<IntArray>, n: Int, m: Int): Array<IntArray> {
        val prefixSum = Array(n + 1) { IntArray(m + 1) }
        prefixSum[0][0] = matrix[0][0]
        for (y in 1..n) {
            prefixSum[y][0] = prefixSum[y - 1][0] + matrix[y][0]
        }

        var matrixLine = matrix[0]
        var prefixLine = prefixSum[0]
        for (x in 1..m) {
            prefixLine[x] = prefixLine[x - 1] + matrixLine[x]
        }

        var lastLine = prefixSum[0]
        for (y in 1..n) {
            prefixLine = prefixSum[y]
            matrixLine = matrix[y]
            for (x in 1..m) {
                prefixLine[x] = lastLine[x] + prefixLine[x - 1] - lastLine[x - 1] + matrixLine[x]
            }
            lastLine = prefixLine
        }

        return prefixSum
    }
}
