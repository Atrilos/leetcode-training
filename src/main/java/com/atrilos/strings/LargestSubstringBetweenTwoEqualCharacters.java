package com.atrilos.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, return the length of the longest substring between two equal characters, excluding the two characters.
 * If there is no such substring return -1.
 * <p>
 * A substring is a contiguous sequence of characters within a string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aa"
 * Output: 0
 * Explanation: The optimal substring here is an empty substring between the two 'a's.
 * Example 2:
 * <p>
 * Input: s = "abca"
 * Output: 2
 * Explanation: The optimal substring here is "bc".
 * Example 3:
 * <p>
 * Input: s = "cbzxy"
 * Output: -1
 * Explanation: There are no characters that appear twice in s.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 300
 * s contains only lowercase English letters.
 */
public class LargestSubstringBetweenTwoEqualCharacters {

    public int maxLengthBetweenEqualCharacters(String s) {
        if (s.length() < 2)
            return -1;

        Map<Character, Integer> characterDistance = new HashMap<>();
        int max = -1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!characterDistance.containsKey(ch)) {
                characterDistance.put(ch, i);
            } else {
                max = Math.max(max, i - characterDistance.get(ch) - 1);
            }
        }

        return max;
    }
}
