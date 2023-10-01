package com.atrilos.graphs

/**
 * [133](https://leetcode.com/problems/clone-graph/)
 */
class CloneGraphKt {
    class Node(var `val`: Int) {
        var neighbors: ArrayList<Node?> = ArrayList()
    }
    fun cloneGraph(node: Node?): Node? {
        node ?: return null

        val map = mutableMapOf<Int, Node>()
        return dfs(node, map)
    }

    private fun dfs(node: Node, map: MutableMap<Int, Node>): Node {
        val nodeClone = Node(node.`val`)
        map[node.`val`] = nodeClone
        for (nbr in node.neighbors.filterNotNull()) {
            val nbrClone = if (nbr.`val` !in map) {
                dfs(nbr, map)
            } else {
                map[nbr.`val`]!!
            }
            nodeClone.neighbors += nbrClone
        }
        return nodeClone
    }

}