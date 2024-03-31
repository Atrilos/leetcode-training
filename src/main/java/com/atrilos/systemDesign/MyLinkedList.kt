package com.atrilos.systemDesign

/**
 * [707](https://leetcode.com/problems/design-linked-list/description/)
 */
class MyLinkedList {

    private var head: Node? = null
    private var size: Int = 0


    fun get(index: Int): Int {
        return findAtIndex(index)?.value ?: -1
    }

    fun addAtHead(`val`: Int) {
        addAtIndex(0, `val`)
    }

    fun addAtTail(`val`: Int) {
        addAtIndex(size, `val`)
    }

    fun addAtIndex(index: Int, `val`: Int) {
        if (index !in 0..size) return

        val oldNode = findAtIndex(index - 1)
        val node = Node(`val`)
        when {
            oldNode == null -> head = node
            index == 0 -> {
                node.next = head
                head = node
            }
            else -> {
                node.next = oldNode.next
                oldNode.next = node
            }
        }
        size++
    }

    fun deleteAtIndex(index: Int) {
        when {
            size == 0 || index !in 0..<size -> return
            index == 0 -> head = head?.next
            else -> {
                findAtIndex(index -1)?.let {
                    it.next = it.next?.next
                }
            }
        }
        size--
    }

    private fun findAtIndex(index: Int): Node? {
        return when {
            size == 0 || index > size - 1 -> null
            index <= 0 -> head
            else -> {
                var current = head
                repeat (index) {
                    current = current?.next
                }
                current
            }
        }
    }


    private data class Node(val value: Int, var next: Node? = null)
}
