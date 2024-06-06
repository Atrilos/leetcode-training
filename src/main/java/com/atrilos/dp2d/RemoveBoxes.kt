package com.atrilos.dp2d

/**
 * [546](https://leetcode.com/problems/remove-boxes/description/)
 */
class RemoveBoxes {
    fun removeBoxes(boxes: IntArray): Int {
        val n = boxes.size
        val dp = Array(n) { Array(n) { IntArray(n) } }
        return removeBoxesSub(boxes, 0, n - 1, 0, dp)
    }

    private fun removeBoxesSub(boxes: IntArray, i: Int, j: Int, k: Int, dp: Array<Array<IntArray>>): Int {
        var i = i
        var k = k
        if (i > j) return 0
        if (dp[i][j][k] > 0) return dp[i][j][k]

        val i0 = i
        val k0 = k // Need to record the initial values of i and k in order to apply the following optimization
        while (i + 1 <= j && boxes[i + 1] == boxes[i]) {
            // optimization: all boxes of the same color counted continuously from the first box should be grouped together
            i++
            k++
        }
        var res = (k + 1) * (k + 1) + removeBoxesSub(boxes, i + 1, j, 0, dp)

        for (m in i + 1..j) {
            if (boxes[i] == boxes[m]) {
                res = maxOf(
                    res,
                    removeBoxesSub(boxes, i + 1, m - 1, 0, dp) + removeBoxesSub(boxes, m, j, k + 1, dp)
                )
            }
        }

        dp[i0][j][k0] = res // When updating the dp matrix, we should use the initial values of i, j and k
        return res
    }
}