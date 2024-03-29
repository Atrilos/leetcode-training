package com.atrilos.linkedList;

/**
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,2,1]
 * Output: true
 * Example 2:
 * <p>
 * Input: head = [1,2]
 * Output: false
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [1, 10^5].
 * 0 <= Node.val <= 9
 * <p>
 * Follow up: Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {
    public static void main(String[] args) {
        ListNodeJava head = new ListNodeJava(1);
        System.out.println(new PalindromeLinkedList().isPalindrome(head));
    }
    public boolean isPalindrome(ListNodeJava head) {
        ListNodeJava fast = head;
        ListNodeJava slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        fast = head;
        slow = reverseList(slow);

        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }

        return true;
    }

    private ListNodeJava reverseList(ListNodeJava head) {
        ListNodeJava prev = null;
        ListNodeJava curr = head;
        while (curr != null) {
            ListNodeJava next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
