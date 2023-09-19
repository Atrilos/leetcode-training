package com.atrilos.linkedList

/**
 * [25](https://leetcode.com/problems/reverse-nodes-in-k-group/description/)
 */
fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
    val count = findSize(head)

    val dummy = ListNode(0).apply { next = head }
    var leftPrev: ListNode? = dummy
    var curr = head

    repeat(count / k) {
        var prev: ListNode? = null
        repeat(k) {
            val tmp = curr?.next
            curr?.next = prev
            prev = curr
            curr = tmp
        }
        leftPrev?.next?.next = curr
        leftPrev?.next = prev.also { leftPrev = leftPrev?.next }
    }

    return dummy.next
}

private fun findSize(head: ListNode?): Int {
    var i = 0
    var curr = head
    while (curr != null) {
        curr = curr.next
        i++
    }
    return i
}