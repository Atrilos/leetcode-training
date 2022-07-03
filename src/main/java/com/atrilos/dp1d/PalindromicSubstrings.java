package com.atrilos.dp1d;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, return the number of palindromic substrings in it.
 * <p>
 * A string is a palindrome when it reads the same backward as forward.
 * <p>
 * A substring is a contiguous sequence of characters within the string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * Example 2:
 * <p>
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.
 */
public class PalindromicSubstrings {
    public static void main(String[] args) {
        System.out.println(new PalindromicSubstrings().countSubstrings("abc"));
    }
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += calculatePalindromes(s, i, i) + calculatePalindromes(s, i, i + 1);
        }
        return count;
    }

    private int calculatePalindromes(String s, int left, int right) {
        int res = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            res++;
        }
        return res;
    }
}
