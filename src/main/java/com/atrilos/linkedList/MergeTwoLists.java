package com.atrilos.linkedList;

/**
 * You are given the heads of two sorted linked lists list1 and list2.
 *
 * Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
 *
 * Return the head of the merged linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * Example 2:
 *
 * Input: list1 = [], list2 = []
 * Output: []
 * Example 3:
 *
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(2);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(6);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(5);
        list2.next.next.next = new ListNode(7);

        MergeTwoLists mtl = new MergeTwoLists();
        ListNode result = mtl.mergeTwoLists(list1, list2);
        System.out.print("Nodes of the merged LinkedList are: ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        final ListNode root = new ListNode();
        ListNode prev = root;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        prev.next = list1 == null ? list2 : list1;

        return root.next;
    }
}
