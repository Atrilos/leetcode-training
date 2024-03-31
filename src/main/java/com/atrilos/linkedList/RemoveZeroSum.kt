package com.atrilos.linkedList

/**
 * [1171](https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/description/)
 */
class RemoveZeroSum {
    private val prefixSum = HashMap<Int, ListNode?>()
    private val dummyHead = ListNode(0)

    fun removeZeroSumSublists(head: ListNode?): ListNode? {
        dummyHead.next = head
        buildPrefixSum()

        var sum = 0
        var node: ListNode? = dummyHead
        while (node != null) {
            sum += node.`val`
            node.next = prefixSum[sum]
            node = node.next
        }
        return dummyHead.next
    }

    private fun buildPrefixSum() {
        var sum = 0
        var node: ListNode? = dummyHead
        while (node != null) {
            sum += node.`val`
            prefixSum[sum] = node.next
            node = node.next
        }
    }
}