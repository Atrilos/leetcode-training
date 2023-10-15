package com.atrilos.ntrees

/**
 * [1522](https://leetcode.com/problems/diameter-of-n-ary-tree/)
 */
class Diameter {
    private var largest = 0
    fun diameter(root: Node?): Int {
        helper(root)
        return largest
    }

    private fun helper(root: Node?): Int {
        if (root == null) return 0

        var max = 0
        var secondMax = 0
        for (node in root.children) {
            val diameter = helper(node)
            if (diameter > max) {
                secondMax = max
                max = diameter
            } else if (diameter > secondMax) {
                secondMax = diameter
            }
        }

        largest = maxOf(largest, max + secondMax + 1)

        return max + 1
    }
}