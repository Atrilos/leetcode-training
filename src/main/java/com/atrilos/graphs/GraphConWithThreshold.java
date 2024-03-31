package com.atrilos.graphs;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/graph-connectivity-with-threshold/description/">1627</a>
 */
public class GraphConWithThreshold {
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        List<Boolean> res = new ArrayList<>();
        UnionFind unionFind = new UnionFind(n + 1);
        fillUF(unionFind, n, threshold);
        for (int[] q : queries) {
            res.add(unionFind.isSame(q[0], q[1]));
        }
        return res;
    }

    private void fillUF(UnionFind unionFind, int n, int threshold) {
        boolean[] seen = new boolean[n + 1];
        for (int i = threshold + 1; i <= n; i++) {
            if (!seen[i]) {
                for (int j = i + i; j <= n; j += i) {
                    seen[j] = true;
                    unionFind.union(i, j);
                }
            }
        }
    }

    private static class UnionFind {
        int[] parent;
        int[] rank;

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

        public void union(int x, int y) {
            if (!isSame(x, y)) {
                int parentX = find(x), parentY = find(y);
                if (rank[parentX] > rank[parentY]) {
                    parent[parentY] = x;
                } else {
                    parent[parentX] = y;
                }
                if (rank[parentX] == rank[parentY]) rank[parentY]++;
            }
        }

        public boolean isSame(int x, int y) {
            return find(x) == find(y);
        }
    }
}
