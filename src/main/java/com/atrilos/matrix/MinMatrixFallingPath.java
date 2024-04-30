package com.atrilos.matrix;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/minimum-falling-path-sum-ii/description/">1289. Minimum Falling Path Sum II</a>
 */
public class MinMatrixFallingPath {
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n][n];

        int[] min = new int[2], secondMin = new int[2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    dp[i][j] = grid[i][j];
                } else {
                    dp[i][j] = grid[i][j] + (min[1] != j ? min[0] : secondMin[0]);
                }
            }

            min = new int[]{Integer.MAX_VALUE, -1};
            secondMin = new int[]{Integer.MAX_VALUE, -1};

            for (int j = 0; j < n; j++) {
                if (dp[i][j] < min[0]) {
                    secondMin[0] = min[0];
                    secondMin[1] = min[1];
                    min[0] = dp[i][j];
                    min[1] = j;
                } else if (dp[i][j] < secondMin[0]) {
                    secondMin[0] = dp[i][j];
                    secondMin[1] = j;
                }
            }
        }

        return Arrays.stream(dp[n - 1]).min().orElseThrow();
    }
}
