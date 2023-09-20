package com.atrilos.trees

import java.util.*
import kotlin.collections.ArrayList

/**
 * [103](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal)
 */
fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
    root ?: return emptyList()
    val queue = LinkedList<TreeNode>()
    val res = mutableListOf<MutableList<Int>>()
    queue.offer(root)
    while (queue.isNotEmpty()) {
        val currentLevel = ArrayList<Int>()
        repeat(queue.size) {
            val next = queue.pop()
            currentLevel += next.`val`
            if (next.left != null) queue.offer(next.left)
            if (next.right != null) queue.offer(next.right)
        }
        res += if (res.size % 2 == 0) currentLevel else currentLevel.apply { reverse() }
    }
    return res
}