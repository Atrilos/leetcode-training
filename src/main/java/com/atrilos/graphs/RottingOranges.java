package com.atrilos.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * You are given an m x n grid where each cell can have one of three values:
 * <p>
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * <p>
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * Example 2:
 * <p>
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * Example 3:
 * <p>
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] is 0, 1, or 2.
 */
public class RottingOranges {
    public static void main(String[] args) {
        System.out.println(new RottingOranges().orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
    }

    private final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int res = 0;
        Queue<int[]> q = new ArrayDeque<>();
        int fresh = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2)
                    q.add(new int[]{i, j});
                else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        while (!q.isEmpty() && fresh != 0) {
            for (int counter = 0, size = q.size(); counter < size; counter++) {
                int i = q.element()[0];
                int j = q.remove()[1];
                for (int[] dir : DIRS) {
                    fresh += mapOneTurn(grid, i + dir[0], j + dir[1], q);
                }
            }
            res++;
        }
        return fresh == 0 ? res : -1;
    }

    private int mapOneTurn(int[][] grid, int i, int j, Queue<int[]> q) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == 0)
            return 0;
        if (grid[i][j] == 1) {
            grid[i][j] = 2;
            q.add(new int[]{i, j});
            return -1;
        }
        return 0;
    }
}
