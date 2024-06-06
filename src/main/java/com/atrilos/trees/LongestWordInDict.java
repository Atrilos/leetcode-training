package com.atrilos.trees;

import java.util.Map;
import java.util.TreeMap;

/**
 * <a href="https://leetcode.com/problems/longest-word-in-dictionary/description/">720</a>
 */
public class LongestWordInDict {
    private static class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<>();
        String word = null;
    }

    private final TrieNode root = new TrieNode();

    public String longestWord(String[] words) {
        for (String s : words) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
            }
            node.word = s;
        }

        return dfs(root);
    }

    private String dfs(TrieNode node) {
        String res = node.word == null ? "" : node.word;

        for (TrieNode next : node.children.values()) {
            if (next.word != null) {
                final String childWord = dfs(next);
                if (childWord.length() > res.length()) {
                    res = childWord;
                }
            }
        }

        return res;
    }
}
