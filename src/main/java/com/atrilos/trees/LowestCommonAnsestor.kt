package com.atrilos.trees

/**
 * [236](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)
 */
fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {

    if (root == null || p == root || q == root) return root

    val left = lowestCommonAncestor(root.left, p, q)
    val right = lowestCommonAncestor(root.right, p, q)

    if (left != null && right != null) {
        return root
    }

    return left ?: right
}