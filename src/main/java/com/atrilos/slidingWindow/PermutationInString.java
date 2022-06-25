package com.atrilos.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s1.length, s2.length <= 10^4
 * s1 and s2 consist of lowercase English letters.
 */
public class PermutationInString {

    public static void main(String[] args) {
        System.out.println(new PermutationInString().checkInclusion("ab",
                "eidboaoo"));
    }

    public boolean checkInclusion(String s1, String s2) {
        int[] dp = new int[26];

        s1.chars().forEach(c -> dp[c - 'a']++);

        int start = 0, end = 0;
        int matches = s1.length();

        while (end < s2.length()) {
            int ch = s2.charAt(end++) - 'a';
            if (dp[ch] > 0)
                matches--;
            dp[ch]--;
            if (end >= s1.length()) {
                if (matches == 0)
                    return true;
                int startChar = s2.charAt(start++) - 'a';
                dp[startChar]++;
                if (dp[startChar] > 0)
                    matches++;
            }
        }
        return false;
    }
}
