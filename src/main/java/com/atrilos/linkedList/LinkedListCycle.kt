package com.atrilos.linkedList

/**
 * (141)[https://leetcode.com/problems/linked-list-cycle/description/]
 */
class LinkedListCycle {
    fun hasCycle(head: ListNode?): Boolean {
        var slow = head
        var fast = head?.next
        while (slow != null && fast?.next != null) {
            if (slow === fast) {
                return true
            }
            slow = slow.next
            fast = fast.next?.next
        }
        return false
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
}