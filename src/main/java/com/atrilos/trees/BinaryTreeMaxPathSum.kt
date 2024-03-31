package com.atrilos.trees

/**
 * [124](https://leetcode.com/problems/binary-tree-maximum-path-sum/)
 */
class BinaryTreeMaxPathSum {
    fun maxPathSum(root: TreeNode?): Int {
        if (root == null) return 0
        var max = root.`val`

        fun dfs(node: TreeNode?): Int {
            if (node == null) return 0
            val left = maxOf(0, dfs(node.left))
            val right = maxOf(0, dfs(node.right))
            max = maxOf(left + right + node.`val`, max)
            return maxOf(left, right) + node.`val`
        }

        dfs(root)
        return max
    }
}
