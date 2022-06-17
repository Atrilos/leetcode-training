package com.atrilos.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character.
 * You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 *
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s consists of only uppercase English letters.
 * 0 <= k <= s.length
 */
public class CharacterReplacement {

    public static void main(String[] args) {
        System.out.println(characterReplacement("AABABBA", 1));
    }

    public static int characterReplacement(String s, int k) {
        Map<Character, Integer> characterCount = new HashMap<>();
        int result = 0;
        int windowStart = 0;
        int maxFreq = 0;

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char endChar = s.charAt(windowEnd);
            characterCount.merge(endChar, 1, Integer::sum);
            maxFreq = Math.max(characterCount.get(endChar), maxFreq);

            while ((windowEnd - windowStart + 1) - maxFreq > k) {
                char startChar = s.charAt(windowStart);
                characterCount.merge(startChar, -1, Integer::sum);
                ++windowStart;
            }
            result = Math.max(result, windowEnd - windowStart + 1);
        }
        return result;
    }
}
