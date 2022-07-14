package com.atrilos.graphs;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges
 * (each edge is a pair of nodes), write a function to find
 * the number of connected components in an undirected graph.
 * Example 1:
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 * 0          3
 * |          |
 * 1 --- 2    4
 * Output: 2
 * Example 2:
 * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 * 0           4
 * |           |
 * 1 --- 2 --- 3
 * Output:  1
 * Note:
 * You can assume that no duplicate edges will appear in edges.
 * Since all edges are undirected, [0, 1] is the same as [1, 0] and
 * thus will not appear together in edges.
 */
public class NumberOfConnectedComponentsInUndirectedGraph {
    public int countComponents(int n, int[][] edges) {
        if (n == 0) {
            return 0;
        } else if (edges == null || edges.length == 0) {
            return n;
        }

        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        for (int[] edge : edges) {
            int parentA = find(parent, edge[0]);
            int parentB = find(parent, edge[1]);
            if (parentA != parentB)
                union(parent, parentA, parentB);
        }
        return (int) IntStream.of(parent).filter(i -> i < 0).count();
    }

    private void union(int[] parent, int a, int b) {
        if (parent[a] > parent[b]) {
            union(parent, b, a);
        } else {
            parent[a] += parent[b];
            parent[b] = a;
        }
    }

    private int find(int[] parent, int x) {
        if (parent[x] < 0)
            return x;
        return find(parent, parent[x]);
    }
}
