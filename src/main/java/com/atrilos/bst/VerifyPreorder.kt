package com.atrilos.bst

/**
 * [255](https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/)
 */
object VerifyPreorder {
    fun verifyPreorder(preorder: IntArray): Boolean {
        var min = Int.MIN_VALUE
        val stack = java.util.ArrayDeque<Int>()

        for (num in preorder) {
            while (stack.isNotEmpty() && stack.peekFirst() < num) {
                min = stack.removeFirst()
            }
            if (num <= min) {
                return false
            }
            stack.offerFirst(num)
        }

        return true
    }
}