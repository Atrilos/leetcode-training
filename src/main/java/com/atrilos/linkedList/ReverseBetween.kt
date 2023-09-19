package com.atrilos.linkedList

/**
 * [92](https://leetcode.com/problems/reverse-linked-list-ii/)
 */
fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
    var h = ListNode(-1).apply { next = head }
    repeat(left - 1) { h = h.next!! }
    val tail = h.next!!

    repeat(right - left) {
        val (tn, tnn) = tail.next to tail.next!!.next
        tn?.next = h.next
        tail.next = tnn
        h.next = tn
    }
    return if (left == 1) h.next else head
}