package com.atrilos.linkedList

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

fun main() {
    val head = ListNode(1)
    head.next = ListNode(2)
    head.next!!.next = ListNode(3)
    head.next!!.next!!.next = ListNode(4)
    head.next!!.next!!.next!!.next = ListNode(5)
    traverse(reverseList(head))
}