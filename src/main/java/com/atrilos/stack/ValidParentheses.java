package com.atrilos.stack;

import java.util.ArrayDeque;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "()"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 * <p>
 * Input: s = "(]"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
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
