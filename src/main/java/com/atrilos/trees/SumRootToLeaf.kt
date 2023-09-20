package com.atrilos.trees

/**
 * [129](https://leetcode.com/problems/sum-root-to-leaf-numbers/)
 */
class SumRootToLeaf {

    fun sumNumbers(root: TreeNode?): Int {
        return dfs(root, 0)
    }

    private fun dfs(root: TreeNode?, sum: Int): Int {
        if (root == null) return 0
        val nextSum = sum * 10 + root.`val`
        if (root.left == null && root.right == null) return nextSum

        return dfs(root.left, nextSum) + dfs(root.right, nextSum)
    }
}