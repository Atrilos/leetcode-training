package com.atrilos.graphs;

/**
 * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
 * <p>
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * Explanation: Surrounded regions should not be on the border, which means that any 'O' on the border of the board are
 * not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped
 * to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
 * Example 2:
 * <p>
 * Input: board = [["X"]]
 * Output: [["X"]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is 'X' or 'O'.
 */
public class SurroundedRegions {
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O')
                mapArea(board, i, 0);
            if (board[i][m - 1] == 'O')
                mapArea(board, i, m - 1);
        }
        for (int j = 0; j < m; j++) {
            if (board[0][j] == 'O')
                mapArea(board, 0, j);
            if (board[n - 1][j] == 'O')
                mapArea(board, n - 1, j);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '2')
                    board[i][j] = 'O';
                else if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }
    }

    private void mapArea(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O')
            return;
        board[i][j] = '2';
        mapArea(board, i, j + 1);
        mapArea(board, i, j - 1);
        mapArea(board, i + 1, j);
        mapArea(board, i - 1, j);
    }
}
