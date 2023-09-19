package com.atrilos.trees

/**
 * [105](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
 */
class BuildTreePreorderInorder {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        val indexMap = inorder.mapIndexed { index, item -> item to index }.toMap()

        fun buildTreeHelper(preorder: IntArray, inorder: IntArray): TreeNode? {
            if (preorder.size == 0) return null
            val root = TreeNode(preorder[0])
            val midIndex = indexMap[preorder[0]]!!
            root.left = buildTree(preorder.sliceArray(1..midIndex), inorder.sliceArray(0..<midIndex))
            root.right = buildTree(preorder.sliceArray(midIndex + 1..preorder.lastIndex), inorder.sliceArray(midIndex + 1..inorder.lastIndex))

            return root
        }
        return buildTreeHelper(preorder, inorder)
    }
}

/**
 * [105](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/)
 */
class BuildTreePostorderInorder {

    private val indexMap = mutableMapOf<Int, Int>()

    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        for ((i, item) in inorder.withIndex()) {
            indexMap[item] = i
        }

        return helper(inorder, postorder, 0, inorder.lastIndex, 0, postorder.lastIndex)
    }

    private fun helper(inorder: IntArray,
                       postorder: IntArray,
                       inOrderStart: Int,
                       inOrderEnd: Int,
                       postOrderStart: Int,
                       postOrderEnd: Int): TreeNode? {

        if (inOrderStart > inOrderEnd) {
            return null
        }

        val root = TreeNode(postorder[postOrderEnd])
        val midIndex = indexMap[postorder[postOrderEnd]]!!

        root.left = helper(
                inorder, postorder,
                inOrderStart,
                midIndex - 1,
                postOrderStart,
                postOrderStart + (midIndex - 1 - inOrderStart))
        root.right = helper(
                inorder, postorder,
                midIndex + 1,
                inOrderEnd,
                postOrderStart + (midIndex - 1 - inOrderStart) + 1,
                postOrderEnd - 1)
        return root
    }
}

fun main() {
    val given = arrayOf<Int?>(3,9,20,null,null,15,7).toTreeNode()

    val buildTree = BuildTreePostorderInorder().buildTree(
            inorder = intArrayOf(9,3,15,20,7),
            postorder = intArrayOf(9,15,7,20,3)
    )

    println(given == buildTree)
}



