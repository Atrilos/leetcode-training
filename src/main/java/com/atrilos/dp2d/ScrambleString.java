package com.atrilos.dp2d;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/scramble-string/description/">87. Scramble String</a>
 */
public class ScrambleString {
    private static final Map<String, Boolean> anagramMap = new HashMap<>();
    private final Map<String, Boolean> scrambleMap = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        if (!isAnagram(s1, s2)) {
            return false;
        }

        final String key = s1 + "#" + s2;
        if (scrambleMap.containsKey(key)) {
            return scrambleMap.get(key);
        }

        final int n = s1.length();

        for (int i = 1; i < n; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i, n), s2.substring(i, n))
                    || isScramble(s1.substring(0, i), s2.substring(n - i, n)) && isScramble(s1.substring(i, n), s2.substring(0, n - i))) {
                scrambleMap.put(key, true);
                return true;
            }
        }

        scrambleMap.put(key, false);
        return false;
    }

    private boolean isAnagram(String s1, String s2) {
        final String key = s1 + "#" + s2;
        if (anagramMap.containsKey(key)) {
            return anagramMap.get(key);
        }
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        for (char ch : s1.toCharArray()) {
            freq1[ch - 'a']++;
        }
        for (char ch : s2.toCharArray()) {
            freq2[ch - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (freq1[i] != freq2[i]) {
                anagramMap.put(key, false);
                return false;
            }
        }
        anagramMap.put(key, true);

        return true;
    }
}
