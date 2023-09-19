package com.atrilos.matrix

/**
 * [54](https://leetcode.com/problems/spiral-matrix/)
 */
fun spiralOrder(matrix: Array<IntArray>): List<Int> {
    val n = matrix.size
    val m = matrix[0].size
    var up = 0
    var down = n
    var left = 0
    var right = m
    val res = mutableListOf<Int>()

    while (true) {
        for (i in left until right)
            res.add(matrix[up][i])
        up++
        for (i in up until down)
            res.add(matrix[i][right - 1])
        right--
        if (res.size == n * m) break
        for (i in right - 1 downTo left)
            res.add(matrix[down - 1][i])
        down--
        for (i in down - 1 downTo up)
            res.add(matrix[i][left])
        left++
        if (res.size == n * m) break
    }
    return res
}