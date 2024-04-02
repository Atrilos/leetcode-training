package com.atrilos.bst

import com.atrilos.trees.TreeNode

/**
 * [95](https://leetcode.com/problems/unique-binary-search-trees-ii/description/)
 */
class UniqueBST2 {
    fun generateTrees(n: Int): List<TreeNode?> {
        return dfs(1, n)
    }

    private fun dfs(leftBorder: Int, rightBorder: Int): List<TreeNode?> {
        if (leftBorder > rightBorder) {
            return listOf(null)
        }

        val trees = mutableListOf<TreeNode?>()
        for (i in leftBorder..rightBorder) {
            for (left in dfs(leftBorder, i - 1)) {
                for (right in dfs(i + 1, rightBorder)) {
                    val root = TreeNode(i)
                    root.run {
                        this.left = left
                        this.right = right
                    }
                    trees.add(root)
                }
            }
        }

        return trees
    }
}