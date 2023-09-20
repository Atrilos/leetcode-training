package com.atrilos.bst

import com.atrilos.trees.TreeNode

/**
 * [230](https://leetcode.com/problems/kth-smallest-element-in-a-bst/)
 */
fun kthSmallest(root: TreeNode?, k: Int): Int {
    val list = mutableListOf<Int>()

    fun dfs(root: TreeNode?) {
        root ?: return
        dfs(root.left)
        if (list.size != k) {
            list += root.`val`
            dfs(root.right)
        } else return
    }

    dfs(root)
    return list.last()
}