package com.atrilos.dp1d;

/**
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * <p>
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways).
 * For example, "11106" can be mapped into:
 * <p>
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 * <p>
 * Given a string s containing only digits, return the number of ways to decode it.
 * <p>
 * The test cases are generated so that the answer fits in a 32-bit integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 * <p>
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * Example 3:
 * <p>
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 */
public class DecodeWays {
    public static void main(String[] args) {
        System.out.println(new DecodeWays().numDecodings("1235457234463144645"));
    }

    // bottom up dynamic programming approach
    // time complexity: O(n)
    // space complexity: O(1)
    public boolean isValid(char c1, char c2) {
        return c1 == '1' || (c1 == '2' && c2 <= '6');
    }

    public int numDecodings(String s) {
        int curr, doubled = 0;
        int single = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 1; i < s.length(); i++) {
            curr = single;
            single = s.charAt(i) == '0' ? 0 : doubled + curr;
            doubled = isValid(s.charAt(i - 1), s.charAt(i)) ? curr : 0;
        }

        return doubled + single;
    }
}
