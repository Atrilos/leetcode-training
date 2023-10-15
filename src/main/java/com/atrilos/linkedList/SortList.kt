package com.atrilos.linkedList

/**
 * [148](https://leetcode.com/problems/sort-list/description/)
 */
class SortList {
    fun sortList(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }

        val midNode = getMid(head)
        val left = sortList(head)
        val right = sortList(midNode)

        return merge(left, right)
    }

    private fun merge(left: ListNode?, right: ListNode?): ListNode {
        val dummyHead = ListNode(0)
        var leftPointer = left
        var rightPointer = right
        var current = dummyHead
        while (leftPointer != null && rightPointer != null) {
            if (leftPointer.`val` < rightPointer.`val`) {
                current.next = leftPointer
                current = current.next!!
                leftPointer = leftPointer.next
            } else {
                current.next = rightPointer
                current = current.next!!
                rightPointer = rightPointer.next
            }
        }
        current.next = leftPointer ?: rightPointer

        return dummyHead.next!!
    }

    private fun getMid(head: ListNode): ListNode? {
        var fast = head.next
        var slow: ListNode? = head
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        val res = slow?.next
        slow?.next = null

        return res
    }
}