package com.atrilos.systemDesign

/**
 * [146](https://leetcode.com/problems/lru-cache/)
 */
class LRUCache(private val capacity: Int) {

    class Node(var key: Int, var value: Int, var next: Node? = null, var prev: Node? = null)

    var left = Node(0, 0)
    var right = Node(0, 0)
    var map = HashMap<Int, Node>()

    init {
        left.next = right
        right.prev = left
    }

    fun get(key: Int): Int {
        if (map[key] == null) {
            return -1
        }
        val node = map[key]!!

        removeNode(node)
        addRight(node)

        return node.value
    }

    fun put(key: Int, value: Int) {
        if (map[key] == null) {
            val node = Node(key, value)
            if (map.size == capacity) {
                removeNode(left.next)
            }
            addRight(node)
        } else {
            val node = map[key]!!
            node.value = value
            removeNode(node)
            addRight(node)
        }
    }

    private fun addRight(node: Node?) {
        if (node == null) {
            return
        }
        val prev = right.prev
        val next = right

        node.next = next
        node.prev = prev
        prev?.next = node
        next.prev = node

        map[node.key] = node
    }

    fun isEmpty() = left.next == right

    private fun removeNode(node: Node?) {
        if (node == null || isEmpty()) {
            return
        }
        val prev = node.prev
        val next = node.next
        prev?.next = next
        next?.prev = prev

        map.remove(node.key)
    }

}