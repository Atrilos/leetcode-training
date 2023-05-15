package com.atrilos.linkedList;

/**
 * You are given the heads of two sorted linked lists list1 and list2.
 * <p>
 * Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Return the head of the merged linked list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * Example 2:
 * <p>
 * Input: list1 = [], list2 = []
 * Output: []
 * Example 3:
 * <p>
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        ListNodeJava list1 = new ListNodeJava(2);
        list1.next = new ListNodeJava(4);
        list1.next.next = new ListNodeJava(6);

        ListNodeJava list2 = new ListNodeJava(1);
        list2.next = new ListNodeJava(3);
        list2.next.next = new ListNodeJava(5);
        list2.next.next.next = new ListNodeJava(7);

        MergeTwoLists mtl = new MergeTwoLists();
        ListNodeJava result = mtl.mergeTwoLists(list1, list2);
        System.out.print("Nodes of the merged LinkedList are: ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    public ListNodeJava mergeTwoLists(ListNodeJava list1, ListNodeJava list2) {
        final ListNodeJava root = new ListNodeJava();
        ListNodeJava prev = root;

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
