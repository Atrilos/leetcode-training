package com.atrilos.linkedList

/**
 * [86](https://leetcode.com/problems/partition-list)
 */
fun partition(head: ListNode?, x: Int): ListNode? {
    val lesserDummy = ListNode(0)
    var lesser: ListNode? = lesserDummy
    val greaterDummy = ListNode(0)
    var greater: ListNode? = greaterDummy
    var curr = head

    while (curr != null) {
        if (curr.`val` < x) {
            lesser?.next = curr
            lesser = lesser?.next
        } else {
            greater?.next = curr
            greater = greater?.next
        }
        curr = curr.next
    }
    lesser?.next = greaterDummy.next
    greater?.next = null

    return lesserDummy.next
}

