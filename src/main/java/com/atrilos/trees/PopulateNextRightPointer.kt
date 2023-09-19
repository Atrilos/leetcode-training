package com.atrilos.trees

/**
 * [117](https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/)
 */
class PopulateNextRightPointer {
    class Node(var `val`: Int, var right: Node?, var left: Node?, var next: Node?)

    fun connect(root: Node?): Node? {
        val map = LinkedHashMap<Int, MutableList<Node>>()
        traversalHelper(map, 0, root)
        for ((_, nodes) in map) {
            for (i in nodes.indices) {
                val node = nodes[i]
                if (node == nodes[nodes.lastIndex]) continue
                node.next = nodes[i + 1]
            }
        }
        return root
    }

    private fun traversalHelper(map: LinkedHashMap<Int, MutableList<Node>>, depth: Int, root: Node?) {
        if (root == null) return
        val nodes = map.getOrPut(depth) { mutableListOf() }
        nodes += root
        traversalHelper(map, depth + 1, root.left)
        traversalHelper(map, depth + 1, root.right)
    }
}