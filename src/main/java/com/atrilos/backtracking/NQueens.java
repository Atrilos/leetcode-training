package com.atrilos.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * <p>
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * <p>
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: [["Q"]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 9
 */
public class NQueens {
    public static void main(String[] args) {
        System.out.println(new NQueens().solveNQueens(4));
    }

    public List<List<String>> solveNQueens(int n) {
        if (n == 1) {
            return new ArrayList<>(List.of(new ArrayList<>(List.of("Q"))));
        }
        List<List<String>> res = new ArrayList<>();
        char[][] field = new char[n][n];
        for (char[] chars : field) {
            Arrays.fill(chars, '.');
        }
        dfs(res, field, 0, 0, 0, 0);
        return res;
    }

    /**
     * Backtracking
     *
     * @param res    reference to result list
     * @param field  chessboard representation
     * @param column representation of available columns as bitset
     * @param posD   representation of available positive diagonals as bitset
     * @param negD   representation of available negative diagonals as bitset
     * @param i      current row
     */
    private void dfs(List<List<String>> res, char[][] field, int column, int posD, int negD, int i) {
        if (i >= field.length) {
            collectResult(res, field, new ArrayList<>());
            return;
        }
        for (int j = 0; j < field.length; j++) {
            if (isAvailable(field, i, j, column, posD, negD)) {
                field[i][j] = 'Q';
                dfs(res, field,
                        column | (1 << j),
                        posD | (1 << (i + j)),
                        negD | (1 << (field.length - 1 - i + j)),
                        i + 1);
                field[i][j] = '.';
            }
        }
    }

    private void collectResult(List<List<String>> res, char[][] field, ArrayList<String> entry) {
        for (char[] chars : field) {
            entry.add(String.valueOf(chars));
        }
        res.add(entry);
    }

    // Checking if bits representing columns, positive and negative diagonals are set.
    private boolean isAvailable(char[][] field, int i, int j, int column, int posD, int negD) {
        return (column & 1 << j) == 0
                && (posD & 1 << (i + j)) == 0
                && (negD & 1 << (field.length - 1 - i + j)) == 0;
    }
}
