package com.atrilos.linkedList

import com.atrilos.trees.TreeNode

fun oddEvenList(head: ListNode?): ListNode? {
    if (head?.next == null) return head

    var odd = head
    var even = head.next
    val evenHead = even

    while (even?.next != null) {
        odd?.next = odd?.next?.next
        odd = odd?.next

        even.next = even.next?.next
        even = even.next
    }
    odd?.next = evenHead

    return head
}

fun traverse(head: ListNode?) {
    if (head == null) println("null")

    var current = head
    while (current != null) {
        println(current.`val`)
        current = current.next
    }
}

fun reverseList(head: ListNode?): ListNode? {
    if (head?.next == null) return head

    var newHead = head

    while (head.next != null) {
        val tmp = head.next
        head.next = head.next?.next
        tmp?.next = newHead
        newHead = tmp
    }

    return newHead
}