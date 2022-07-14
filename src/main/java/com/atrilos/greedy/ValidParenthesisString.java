package com.atrilos.greedy;

/**
 * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
 * <p>
 * The following rules define a valid string:
 * <p>
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "()"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "(*)"
 * Output: true
 * Example 3:
 * <p>
 * Input: s = "(*))"
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 100
 * s[i] is '(', ')' or '*'.
 */
public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        int lo = 0, hi = 0;
        for (char c : s.toCharArray()) {
            lo += c == '(' ? 1 : -1; // Minimum open brackets
            hi += c != ')' ? 1 : -1; // Maximum open brackets
            if (hi < 0) break;
            lo = Math.max(lo, 0); // Min can't be less than zero
        }
        return lo == 0;
    }

    public boolean checkValidString2(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '*')
                cnt++;
            else
                cnt--;

            if (cnt < 0)
                return false;
        }
        if (cnt == 0)
            return true;
        cnt = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == ')' || ch == '*')
                cnt++;
            else
                cnt--;
            if (cnt < 0)
                return false;
        }
        return true;
    }
}
