package com.atrilos.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: ["()"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 8
 */
public class GenerateParentheses {

    private final ArrayDeque<Character> stack = new ArrayDeque<>();
    private final List<String> res = new ArrayList<>();
    private int n;

    public static void main(String[] args) {
        System.out.println(new GenerateParentheses().generateParenthesis(3));
    }

    //recursion
    public List<String> generateParenthesis(int n) {
        this.n = n;

        backtrack(0, 0);
        return res;
    }

    private void backtrack(int openCount, int closedCount) {
        if (openCount == n && closedCount == n) {
            res.add(
                    stack.stream()
                            .map(Object::toString)
                            .collect(Collectors.joining())
            );
        }
        if (openCount < n) {
            stack.offerLast('(');
            backtrack(openCount + 1, closedCount);
            stack.removeLast();
        }
        if (closedCount < openCount) {
            stack.offerLast(')');
            backtrack(openCount, closedCount + 1);
            stack.removeLast();
        }
    }

    //iterative
    public List<String> generateParenthesis2(int n) {
        if (n < 0) {
            return new ArrayList<>();
        }

        List<List<String>> lists = new ArrayList<>();
        lists.add(Collections.singletonList(""));

        for (int i = 1; i <= n; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                for (String first : lists.get(j)) {
                    for (String second : lists.get(i - 1 - j)) {
                        list.add("(" + first + ")" + second);
                    }
                }
            }
            lists.add(list);
        }

        return lists.get(n);
    }

}
