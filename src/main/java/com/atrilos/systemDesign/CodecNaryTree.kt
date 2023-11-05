package com.atrilos.systemDesign

/**
 * [428](https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/description/)
 */
class CodecNaryTree {
    // Encodes a tree to a single string.
    fun serialize(root: Node?): String {
        if (root == null) return ""

        val sb = StringBuilder()
        sb.append("${root.`val`}#${root.children.size} ")
        root.children.forEach { sb.append(serialize(it)) }
        return sb.toString()
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): Node? {
        val str = data.trim()
        if (str == "") return null
        val nodes = str.split(" ")
        var i = 0
        fun deserializeImpl(): Node {
            val nodeStr = nodes[i].split("#")
            val value = nodeStr[0].toInt()
            val childrenSize = nodeStr[1].toInt()
            val list = mutableListOf<Node>()
            repeat(childrenSize) {
                i++
                list.add(deserializeImpl())
            }

            return Node(value).apply { children = list }
        }

        return deserializeImpl()
    }

    class Node(var `val`: Int) {
        var children: List<Node?> = listOf()
    }
}