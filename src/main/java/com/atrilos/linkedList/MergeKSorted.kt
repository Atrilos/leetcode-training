package com.atrilos.linkedList

/**
 * [23](https://leetcode.com/problems/merge-k-sorted-lists/)
 */
class MergeKSorted {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        if (lists.isEmpty()) return null

        fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
            if (l1 == null) return l2
            if (l2 == null) return l1

            return if (l1.`val` < l2.`val`) {
                l1.next = mergeTwoLists(l1.next, l2)
                l1
            } else {
                l2.next = mergeTwoLists(l1, l2.next)
                l2
            }
        }

        fun merge(left: Int, right: Int): ListNode? {
            if (left == right) return lists[left]
            if (left + 1 == right) return mergeTwoLists(lists[left], lists[right])

            val mid = (left + right) / 2
            val leftMerged = merge(left, mid)
            val rightMerged = merge(mid + 1, right)

            return mergeTwoLists(leftMerged, rightMerged)
        }

        return merge(0, lists.size - 1)
    }
}