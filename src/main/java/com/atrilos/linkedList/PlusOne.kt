package com.atrilos.linkedList

/**
 * [369](https://leetcode.com/problems/plus-one-linked-list/description/)
 */
fun plusOne(head: ListNode?): ListNode? {
    if (head == null) throw IllegalArgumentException()

    val dummyHead = ListNode(0).apply { next = head }
    var notNine: ListNode? = dummyHead
    var curr = head

    while (curr != null) {
        if (curr.`val` != 9) {
            notNine = curr
        }
        curr = curr.next
    }

    notNine!!.`val`++
    notNine = notNine.next

    while (notNine != null) {
        notNine.`val` = 0
        notNine = notNine.next
    }

    return if (dummyHead.`val` == 0) dummyHead.next else dummyHead
}