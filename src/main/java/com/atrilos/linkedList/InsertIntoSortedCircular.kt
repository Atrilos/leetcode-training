package com.atrilos.linkedList

/**
 * [708](https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/)
 */
class InsertIntoSortedCircular {

    class Node(var `val`: Int) {
        var next: Node? = null
    }

    fun insert(head: Node?, insertVal: Int): Node {
        if (head == null) return Node(insertVal).apply { next = this }

        var prev: Node = head
        var curr: Node = head.next!!
        var fullCircle = false

        while (true) {
            if (curr === head) {
                fullCircle = true
            }
            if ((insertVal >= prev.`val` && insertVal <= curr.`val`)
                || (prev.`val` >= curr.`val` && fullCircle)
            ) {
                val newNode = Node(insertVal)
                prev.next = newNode
                newNode.next = curr
                break
            }
            prev = curr
            curr = curr.next!!
        }

        return head
    }
}