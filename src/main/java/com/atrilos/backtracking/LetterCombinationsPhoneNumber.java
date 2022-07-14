package com.atrilos.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * <p>
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 * <p>
 * Input: digits = ""
 * Output: []
 * Example 3:
 * <p>
 * Input: digits = "2"
 * Output: ["a","b","c"]
 */
public class LetterCombinationsPhoneNumber {

    // Array of number to letter mappings
    private final String[] NUMBER_MAP = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) {
            return res;
        }
        dfs(new StringBuilder(), res, digits, 0);

        return res;
    }

    private void dfs(StringBuilder entry, List<String> res, String s, int pos) {
        if (pos >= s.length()) {
            res.add(entry.toString());
            return;
        }
        int digit = s.charAt(pos) - '0';
        for (int j = 0; j < NUMBER_MAP[digit].length(); j++) {
            entry.append(NUMBER_MAP[digit].charAt(j));
            dfs(entry, res, s, pos + 1);
            entry.setLength(entry.length() - 1);
        }
    }
}
