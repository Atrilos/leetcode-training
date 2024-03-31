package com.atrilos.graphs;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/greatest-common-divisor-traversal/description/">2709</a>
 */
public class GCDTraversal {
    public boolean canTraverseAllPairs(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        if (Arrays.stream(nums).min().orElseThrow() == 1) {
            return false;
        }
        int maxElement = Arrays.stream(nums).max().orElseThrow();
        int[] factorArray = factorsCalculator(maxElement);

        UnionFind uf = new UnionFind(maxElement + 1);

        for (int num : nums) {
            int x = num;
            while (x > 1) {
                int p = factorArray[x];
                uf.union(p, num);
                while (x % p == 0) {
                    x = x / p;
                }
            }
        }

        int p = uf.find(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (uf.find(nums[i]) != p) return false;
        }

        return true;
    }

    private int[] factorsCalculator(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = i;
        }
        for (int i = 2; i <= n; i++) {
            if (dp[i] == i) {
                for (int j = i * 2; j <= n; j += i) {
                    if (dp[j] == j) dp[j] = i;
                }
            }
        }
        return dp;
    }

    private static class UnionFind {
        private final int[] parent;
        private final int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int a, int b) {
            int x = find(a), y = find(b);
            if (x != y) {
                if (rank[x] > rank[y]) {
                    parent[y] = x;
                } else {
                    parent[x] = y;
                }
                if (rank[x] == rank[y]) rank[y]++;
            }
        }
    }

}
