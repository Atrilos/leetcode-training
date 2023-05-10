package com.atrilos.dp2d;

public class MinPathSum {

    public static void main(String[] args) {
        System.out.println(new MinPathSum().minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    }

    public int minPathSum(int[][] grid) {
        if (grid.length == 0) return 0;
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int leftSum = (j > 0) ? grid[i][j - 1] : Integer.MAX_VALUE;
                int topSum = (i > 0) ? grid[i - 1][j] : Integer.MAX_VALUE;
                if (i == 0 && j == 0) continue;

                grid[i][j] += Math.min(leftSum, topSum);
            }
        }
        return grid[n - 1][m - 1];
    }
}
