package com.atrilos.linkedList

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
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
        System.`out`
        return head
    }
}