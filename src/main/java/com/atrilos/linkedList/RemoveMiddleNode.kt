package com.atrilos.linkedList

class ListNode(var `val`: Int) {
    var next: ListNode? = null

    constructor(`val`: Int, next: ListNode?) : this(`val`) {
        this.next = next
    }
}

fun IntArray.toListNode(): ListNode? {
    if (isEmpty()) return null
    val dummyHead = ListNode(0)
    var current = dummyHead
    for (i in this) {
        val nextNode = ListNode(i)
        current.next = nextNode
        current = current.next!!
    }

    return dummyHead.next
}

class RemoveMiddleNode {
    fun deleteMiddle(head: ListNode?): ListNode? {
        if (head?.next == null) return null
        var current = head
        var length = 0

        while (current != null) {
            length++
            current = current.next
        }

        val mid = length / 2
        current = head

        for (i in 0 until mid) {
            if (i == mid - 1) {
                current?.next = current?.next?.next
            }
            current = current?.next
        }
        return head
    }
}