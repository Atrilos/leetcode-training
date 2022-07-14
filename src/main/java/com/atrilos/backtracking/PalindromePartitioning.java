package com.atrilos.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 * <p>
 * A palindrome string is a string that reads the same backward as forward.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 * <p>
 * Input: s = "a"
 * Output: [["a"]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 */
public class PalindromePartitioning {
    public static void main(String[] args) {
        System.out.println(new PalindromePartitioning().partition("aabb"));
    }
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), 0, s);
        return res;
    }

    private void dfs(List<List<String>> res, List<String> entry, int start, String s) {
        if (start >= s.length())
            res.add(new ArrayList<>(entry));
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                entry.add(s.substring(start, i + 1));
                dfs(res, entry, i + 1, s);
                entry.remove(entry.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int lo, int hi) {
        while (lo < hi) {
            if (s.charAt(lo++) != s.charAt(hi--))
                return false;
        }
        return true;
    }
}
