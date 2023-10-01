package com.atrilos.trees

/**
 * [250](https://leetcode.com/problems/count-univalue-subtrees/)
 */
class CountUnivalSubtrees {

    var count = 0

    fun countUnivalSubtrees(root: TreeNode?): Int {
        dfs(root, 0)
        return count
    }

    private fun dfs(node: TreeNode?, compare: Int): Boolean {
        if (node == null) {
            return true
        }

        if (!dfs(node.left, node.`val`) or !dfs(node.right, node.`val`)) {
            return false
        }

        count++
        return node.`val` == compare
    }
}