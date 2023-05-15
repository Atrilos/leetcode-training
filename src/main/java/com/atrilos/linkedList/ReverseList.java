package com.atrilos.linkedList;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2]
 * Output: [2,1]
 * Example 3:
 * <p>
 * Input: head = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 * <p>
 * <p>
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseList {

    public static void main(String[] args) {
        ListNodeJava head = new ListNodeJava(2);
        head.next = new ListNodeJava(4);
        head.next.next = new ListNodeJava(6);
        head.next.next.next = new ListNodeJava(8);
        head.next.next.next.next = new ListNodeJava(10);

        ReverseList rl = new ReverseList();
        ListNodeJava result = rl.reverseList2(head);
        System.out.print("Nodes of the reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    //iterative
    public ListNodeJava reverseList(ListNodeJava head) {
        if (head == null)
            return null;

        ListNodeJava previous = null;
        ListNodeJava current = head;

        while (current != null) {
            ListNodeJava nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }

        return previous;
    }

    //recursive
    public ListNodeJava reverseList2(ListNodeJava head) {
        if (head == null || head.next == null)
            return head;
        ListNodeJava newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }
}

class ListNodeJava {
    int val;
    ListNodeJava next;

    ListNodeJava() {
    }

    ListNodeJava(int val) {
        this.val = val;
    }

    ListNodeJava(int val, ListNodeJava next) {
        this.val = val;
        this.next = next;
    }
}
