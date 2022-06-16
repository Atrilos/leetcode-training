package com.atrilos.arrays;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "rat", t = "car"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length, t.length <= 5 * 104
 * s and t consist of lowercase English letters.
 */
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        return Arrays.stream(s.split("")).parallel().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .equals(Arrays.stream(t.split("")).parallel().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }

    public boolean isAnagram2(String s, String t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;

        char[] sList = s.toCharArray();
        char[] tList = t.toCharArray();

        // Sort
        Arrays.sort(sList);
        Arrays.sort(tList);

        // compare
        return Arrays.equals(sList, tList);
    }
}
