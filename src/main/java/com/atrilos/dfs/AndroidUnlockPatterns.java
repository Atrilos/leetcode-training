package com.atrilos.dfs;

/**
 * <a href="https://leetcode.com/problems/android-unlock-patterns/description/">351</a>
 */
public class AndroidUnlockPatterns {
    private int[][] jumps;
    private boolean[] seen;

    public int numberOfPatterns(int m, int n) {
        jumps = new int[10][10];
        jumps[1][3] = jumps[3][1] = 2;
        jumps[4][6] = jumps[6][4] = 5;
        jumps[7][9] = jumps[9][7] = 8;
        jumps[1][7] = jumps[7][1] = 4;
        jumps[2][8] = jumps[8][2] = 5;
        jumps[3][9] = jumps[9][3] = 6;
        jumps[1][9] = jumps[9][1] = jumps[3][7] = jumps[7][3] = 5;
        seen = new boolean[10];
        int count = 0;
        count += dfs(1, 1, 0, m, n) * 4; // 1, 3, 7, 9 are symmetrical
        count += dfs(2, 1, 0, m, n) * 4; // 2, 4, 6, 8 are symmetrical
        count += dfs(5, 1, 0, m, n);
        return count;
    }

    private int dfs(int num, int len, int count, int m, int n) {
        if (len >= m) {
            count++; // only count if moves are larger than m
        }
        len++;
        if (len > n) {
            return count;
        }
        seen[num] = true;
        for (int next = 1; next <= 9; next++) {
            int jump = jumps[num][next];
            if (!seen[next] && (jump == 0 || seen[jump])) {
                count = dfs(next, len, count, m, n);
            }
        }
        seen[num] = false; // backtracking
        return count;
    }
}
