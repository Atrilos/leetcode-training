package com.atrilos.trees

import java.util.ArrayDeque

fun rightSideView(root: TreeNode?): List<Int> {
    if (root == null) return emptyList()

    val deque = ArrayDeque<TreeNode>()
    val res = mutableListOf<Int>()

    deque.addLast(root)

    while (deque.isNotEmpty()) {
        res += deque.peekLast().`val`
        val size = deque.size
        repeat(size) {
            val current = deque.removeFirst()
            current.left?.let { item -> deque.addLast(item) }
            current.right?.let { item -> deque.addLast(item) }
        }
    }

    return res
}