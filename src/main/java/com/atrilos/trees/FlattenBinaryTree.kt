package com.atrilos.trees

/**
 * [114](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/)
 */
class FlattenBinaryTree {
    fun flatten(root: TreeNode?) {
        dfs(root)
    }

    private fun dfs(root: TreeNode?): TreeNode?  {
        if (root == null) return null
        val leftTail = dfs(root.left)
        val rightTail = dfs(root.right)

        if (root.left != null) {
            leftTail?.right = root.right
            root.right = root.left
            root.left = null
        }
        return rightTail ?: leftTail ?: root
    }
}