package com.atrilos.bst

import com.atrilos.trees.TreeNode
import java.util.*
import kotlin.math.abs

/**
 * [272](https://leetcode.com/problems/closest-binary-search-tree-value-ii/description/)
 */
class ClosestBSTValue2 {
    fun closestKValues(root: TreeNode?, target: Double, k: Int): List<Int> {
        val deque = LinkedList<Int>()

        dfs(root, target, k, deque)

        return deque.toList()
    }

    fun dfs(node: TreeNode?, target: Double, k: Int, deque: LinkedList<Int>) {
        node ?: return

        dfs(node.left, target, k, deque)
        deque.offerLast(node.`val`)
        if (deque.size > k) {
            if (abs(deque.peekFirst() - target) > abs(deque.peekLast() - target)) {
                deque.removeFirst()
            } else {
                deque.removeLast()
            }
        }
        dfs(node.right, target, k, deque)
    }
}