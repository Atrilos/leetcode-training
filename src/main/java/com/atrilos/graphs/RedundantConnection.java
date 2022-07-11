package com.atrilos.graphs;

import java.util.Arrays;

/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added.
 * The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.
 * The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge
 * between nodes ai and bi in the graph.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers,
 * return the answer that occurs last in the input.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: edges = [[1,2],[1,3],[2,3]]
 * Output: [2,3]
 * Example 2:
 *
 *
 * Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
 * Output: [1,4]
 *
 *
 * Constraints:
 *
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai < bi <= edges.length
 * ai != bi
 * There are no repeated edges.
 * The given graph is connected.
 */
public class RedundantConnection {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new RedundantConnection().findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}})));
    }

    /**
     * Union find
     * TC - N
     * SC - N
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parents = new int[n + 1];
        Arrays.fill(parents, -1);

        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            int parentA = find(parents, a);
            int parentB = find(parents, b);
            if (parentA == parentB) {   // cycle
                return edge;
            } else {
                union(parents, parentA, parentB);
            }
        }

        throw new AssertionError();
    }

    private int find(int[] parents, int v) {
        if (parents[v] < 0)
            return v;
        return find(parents, parents[v]);
    }

    private void union(int[] parents, int a, int b) {
        if (parents[a] > parents[b]) {
            union(parents, b, a);
        }
        parents[b] = a;
    }
}
