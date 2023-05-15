package com.atrilos.trees

fun pathSum(root: TreeNode?, targetSum: Int): Int {
    if(root == null) return 0
    return pathSumFromRoot(root, targetSum.toLong()) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum)
}

fun pathSumFromRoot(root: TreeNode?, targetSum: Long): Int {
    if (root == null) return 0

    val match = if (targetSum == root.`val`.toLong()) 1 else 0
    return pathSumFromRoot(root.left, targetSum - root.`val`) + pathSumFromRoot(root.right, targetSum - root.`val`) + match
}

fun main() {
    val root = TreeNode(1)
    root.right = TreeNode(2)
    root.right?.right = TreeNode(3)
    root.right?.right?.right = TreeNode(4)
    root.right?.right?.right?.right = TreeNode(5)
    println(pathSum(root, 3))
}
