package com.atrilos.trees

/**
 * [2265](https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/description/)
 */
class CountNodesEqualToAverage {
    var res = 0

    fun averageOfSubtree(root: TreeNode?): Int {
        dfs(root)
        return res
    }

    private fun dfs(node: TreeNode?): SubTreeState {
        if (node == null) {
            return SubTreeState(0, 0)
        }
        val left = dfs(node.left)
        val right = dfs(node.right)
        val sum = left.sum + right.sum + node.`val`
        val size = left.size + right.size + 1

        if (sum / size == node.`val`) {
            res++
        }

        return SubTreeState(sum, size)
    }

    data class SubTreeState(val sum: Int, val size: Int)
}