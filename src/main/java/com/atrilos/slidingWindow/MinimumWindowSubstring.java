package com.atrilos.slidingWindow;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 * <p>
 * The testcases will be generated such that the answer is unique.
 * <p>
 * A substring is a contiguous sequence of characters within the string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 * <p>
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 * <p>
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 10^5
 * s and t consist of uppercase and lowercase English letters.
 * <p>
 * <p>
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        System.out.println(new MinimumWindowSubstring().minWindow("cabwefgewcwaefgcf", "cae"));
    }

    public String minWindow(String s, String t) {
        int[] dp = new int[57];
        t.chars().forEach(e -> dp[e - 'A']++);

        int start = 0, end = 0;
        int matches = t.length();
        String res = "";

        while (end < s.length()) {
            int ch = s.charAt(end++) - 'A';
            if (dp[ch] > 0)
                matches--;
            dp[ch]--;
            if (matches == 0) {
                while (end - start >= t.length()) {
                    ch = s.charAt(start++) - 'A';
                    dp[ch]++;
                    if (dp[ch] > 0) {
                        if (res.isEmpty() || end - start < res.length())
                            res = s.substring(start - 1, end);
                        matches++;
                        break;
                    }
                }
            }
        }

        return res;
    }
}
