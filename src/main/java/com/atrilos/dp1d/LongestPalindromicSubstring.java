package com.atrilos.dp1d;

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("babad"));
    }
    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        String max = "";

        for (int i = 0; i < s.length() - 1; i++) {
            int lpointer = i;
            int rpointer = i + 1;
            while (rpointer < i + 3) {
                int t = rpointer;
                System.out.println(lpointer + " " + rpointer);
                while (lpointer >= 0 && rpointer < s.length() && s.charAt(lpointer) == s.charAt(rpointer)) {
                    lpointer--;
                    rpointer++;
                }

                String temp = s.substring(lpointer + 1, rpointer);
                max = max.length() < temp.length() ? temp : max;
                rpointer = t + 1;
                lpointer = i;
                if (rpointer == s.length()) {
                    break;
                }
            }
        }

        return max.equals("") ? String.valueOf(s.charAt(0)) : max;
    }
}
