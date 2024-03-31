package com.atrilos.linkedList

/**
 * [24](https://leetcode.com/problems/swap-nodes-in-pairs/)
 */
fun swapPairs(head: ListNode?): ListNode? {
    if (head == null) return null

    val dummyHead = ListNode(0).apply { next = head }
    var pre = dummyHead

    while (pre.next?.next != null) {
        val start = pre.next!!
        val end = pre.next!!.next!!
        val tmp = end.next
        end.next = start
        start.next = tmp
        pre.next = end
        pre = start
    }

    return dummyHead.next
}