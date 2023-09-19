package com.atrilos.linkedList

/**
 * [61](https://leetcode.com/problems/rotate-list/)
 */
class RotateList {
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head?.next == null) return head

        var size = 1
        var curr = head
        while (curr?.next != null) {
            size++
            curr = curr.next
        }

        curr?.next = head
        repeat(size - (k % size)) {
            curr = curr?.next
        }

        val res = curr?.next
        curr?.next = null

        return res
    }
}