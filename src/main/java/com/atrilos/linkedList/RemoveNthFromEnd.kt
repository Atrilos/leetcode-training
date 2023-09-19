package com.atrilos.linkedList

/**
 * [19](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)
 */
fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    val dummy = ListNode(0).apply { next = head }
    var fast: ListNode? = dummy
    var slow: ListNode? = dummy
    repeat(n) { fast = fast?.next }
    while (fast?.next != null) {
        fast = fast?.next
        slow = slow?.next
    }
    slow?.next = slow?.next?.next

    return dummy.next
}