package com.atrilos.trees

/**
 * [298](https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/description/)
 */
class LongestConsecutive {
    fun longestConsecutive(root: TreeNode?): Int {
        return dfs(root, null, 0)
    }

    private fun dfs(node: TreeNode?, parent: TreeNode?, length: Int): Int {
        if (node == null) {
            return length
        }
        val newLength = if (parent != null && parent.`val` + 1 == node.`val`) length + 1 else 1

        return maxOf(length,
            maxOf(dfs(node.left, node, newLength), dfs(node.right, node, newLength))
        )
    }
}