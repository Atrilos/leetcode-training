package com.atrilos.dp2d;

public class DungeonGame {

    public int calculateMinimumHP(int[][] dungeon) {

        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n][m];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (i == n - 1 && j == m - 1) {
                    dp[i][j] = Math.min(0, dungeon[i][j]);
                } else if (i == n - 1) {
                    dp[i][j] = Math.min(0, dungeon[i][j] + dp[i][j + 1]);
                } else if (j == m - 1) {
                    dp[i][j] = Math.min(0, dungeon[i][j] + dp[i + 1][j]);
                } else {
                    dp[i][j] = Math.min(0, dungeon[i][j] + Math.max(dp[i][j + 1], dp[i + 1][j]));
                }
            }
        }

        return Math.abs(dp[0][0]) + 1;
    }
}
