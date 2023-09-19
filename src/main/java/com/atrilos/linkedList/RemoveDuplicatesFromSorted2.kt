package com.atrilos.linkedList

/**
 * [82](https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/)
 */
class RemoveDuplicatesFromSorted2 {
    fun deleteDuplicates(head: ListNode?): ListNode? {
        val dummy = ListNode(0).apply { next = head }
        var curr = head
        val toDelete = mutableSetOf<Int>()
        while (curr?.next != null) {
            if (curr.next?.`val` == curr.`val`) {
                toDelete += curr.`val`
            }
            curr = curr.next
        }
        curr = dummy
        while (curr?.next != null) {
            if (curr.next?.`val` in toDelete) {
                curr.next = curr.next?.next
            } else {
                curr = curr.next
            }
        }

        return dummy.next
    }
}

fun main() {
    RemoveDuplicatesFromSorted2().deleteDuplicates(ListNode(1, ListNode(2, ListNode(3, ListNode(3, ListNode(4, ListNode(4, ListNode(5))))))))
}