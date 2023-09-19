package com.atrilos.trees

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
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
    val root = TreeNode(0)
    fun helper(index: Int, root: TreeNode?): TreeNode? {
        if (index !in indices || this[index] == null) return null
        root?.`val` = this[index]!!
        root?.left = helper(index * 2 + 1, TreeNode(0))
        root?.right = helper(index * 2 + 2, TreeNode(0))

        return root
    }
    return helper(0, root)
}

fun maxDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    return maxOf(maxDepth(root.right), maxDepth(root.left)) + 1
}


