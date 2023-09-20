package com.atrilos.trees

/**
 * [112](https://leetcode.com/problems/path-sum/)
 */
fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
    if (root == null) return false
    if (root.left == null && root.right == null) return targetSum == root.`val`

    return hasPathSum(root.right, targetSum - root.`val`) || hasPathSum(root.left, targetSum - root.`val`)
}