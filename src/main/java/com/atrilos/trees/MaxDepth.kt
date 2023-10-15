package com.atrilos.trees

import java.util.LinkedList

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    constructor(`val`: Int, left: TreeNode?, right: TreeNode?) : this(`val`) {
        this.left = left
        this.right = right
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TreeNode

        if (`val` != other.`val`) return false
        if (left != other.left) return false
        if (right != other.right) return false

        return true
    }

    override fun hashCode(): Int {
        var result = `val`
        result = 31 * result + (left?.hashCode() ?: 0)
        result = 31 * result + (right?.hashCode() ?: 0)
        return result
    }
}

fun Array<Int?>.toTreeNode(): TreeNode? {
    if (isEmpty()) return null
    var count = 0
    val q = LinkedList<TreeNode?>()
    val root = TreeNode(this[0]!!)
    q.offerLast(root)
    var curr: TreeNode? = null
    for (i in 1..this.lastIndex) {
        val node = this[i]?.let { TreeNode(it) }
        if (count == 0) {
            curr = q.removeFirst()
            count++
            curr?.left = node
        } else {
            count = 0
            curr?.right = node
        }
        if (this[i] != null) {
            q.offerLast(node)
        }
    }
    return root
}

fun maxDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    return maxOf(maxDepth(root.right), maxDepth(root.left)) + 1
}


