package com.atrilos.slidingWindow;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 10^4
 * s consists of English letters, digits, symbols and spaces.
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;

        Set<Character> set = new LinkedHashSet<>();
        int n = s.length();
        int maxLength = 0;
        int left = 0, right = 0;

        while (right < n) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                ++right;
                maxLength = Math.max(maxLength, right - left);
            } else {
                set.remove(s.charAt(left));
                ++left;
            }
        }
        System.out.println(set);
        return maxLength;
    }
}
