package com.atrilos.stack;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 *
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: true
 * Example 2:
 *
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: s = "(]"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * s consists of parentheses only '()[]{}'.
 */
public class ValidParentheses {

    //~600ms
    public boolean isValid(String s) {
        int n = s.length();
        s = s.replaceAll("\\(\\)|\\{}|\\[]", "");
        return s.length() != n && (s.length() == 0 || isValid(s));
    }


    //~1ms
    public boolean isValid2(String s) {
        ArrayDeque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '[' -> deque.offerLast(']');
                case '{' -> deque.offerLast('}');
                case '(' -> deque.offerLast(')');
                case ']', '}', ')' -> {
                    if (deque.isEmpty() || deque.pollLast() != ch)
                        return false;
                }
                default -> throw new IllegalStateException("Unexpected value: " + ch);
            }
        }

        return deque.isEmpty();
    }
}
