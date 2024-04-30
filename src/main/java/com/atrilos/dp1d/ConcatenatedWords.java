package com.atrilos.dp1d;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/concatenated-words/description/">472. Concatenated Words</a>
 */
public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        List<String> res = new ArrayList<>();
        Set<String> preWords = new HashSet<>();

        for (final String word : words) {
            if (helper(word, preWords)) {
                res.add(word);
            }
            preWords.add(word);
        }

        return res;
    }

    private boolean helper(String s, Set<String> words) {
        if (words.isEmpty()) {
            return false;
        }
        int n = s.length();
        BitSet dp = new BitSet(n + 1);
        dp.set(0);

        for (int i = 0; i < n; i++) {
            if (!dp.get(i)) {
                continue;
            }
            for (int j = i + 1; j <= n; j++) {
                if (words.contains(s.substring(i, j))) {
                    dp.set(j);
                }
            }
        }

        return dp.get(n);
    }
}
