package com.atrilos.trees

/**
 * [114](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/)
 */
class FlattenBinaryTree {
    fun flatten(root: TreeNode?): Unit {
        if (root == null) return
        val preorder = mutableListOf<TreeNode>()
        preorderHelper(preorder, root)

        var curr: TreeNode = root
        for (i in 1..preorder.lastIndex) {
            val next = preorder[i]
            curr.right = next
            curr.left = null
            curr = curr.right!!
        }
    }

    private fun preorderHelper(preorder: MutableList<TreeNode>, root: TreeNode?) {
        if (root == null) return
        preorder += root
        preorderHelper(preorder, root.left)
        preorderHelper(preorder, root.right)
    }
}