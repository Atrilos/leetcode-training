package com.atrilos.linkedList

fun pairSum(head: ListNode?): Int {
    var slow = head
    var fast = head

    // Get middle of the linked list.
    while (fast?.next != null) {
        fast = fast.next!!.next
        slow = slow?.next
    }

    // Reverse second half of the linked list.
    var nextNode: ListNode?
    var prev: ListNode? = null
    while (slow != null) {
        nextNode = slow.next
        slow.next = prev
        prev = slow
        slow = nextNode
    }
    var start = head
    var maximumSum = 0
    while (prev != null) {
        maximumSum = maximumSum.coerceAtLeast(start!!.`val` + prev.`val`)
        prev = prev.next
        start = start.next
    }
    return maximumSum
}