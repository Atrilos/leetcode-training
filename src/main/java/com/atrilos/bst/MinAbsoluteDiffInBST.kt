package com.atrilos.bst

import com.atrilos.trees.TreeNode
import kotlin.math.abs

/**
 * [530](https://leetcode.com/problems/minimum-absolute-difference-in-bst/)
 */
fun getMinimumDifference(root: TreeNode?): Int {
    var res = Int.MAX_VALUE
    var prev: TreeNode? = null
    fun bfs(root: TreeNode?) {
        root ?: return
        bfs(root.left)
        prev?.let { res = minOf(res, abs(root.`val` - it.`val`)) }
        prev = root
        bfs(root.right)
    }
    bfs(root)
    return res
}