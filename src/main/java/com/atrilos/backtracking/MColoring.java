package com.atrilos.backtracking;

import java.util.ArrayList;
import java.util.Arrays;

public class MColoring {
    boolean graphColor(int u, ArrayList<ArrayList<Integer>> adj, int m,
                       int[] vertices, boolean[] seen) {

        seen[u] = true;
        int i = 0;
        while (i < m) {
            vertices[u] = i;
            int k = i;
            for (int v : adj.get(u)) {
                if (vertices[v] == vertices[u]) {
                    i++;
                    break;
                }
            }
            if (i == k) {
                for (int v : adj.get(u)) {
                    if (!seen[v]) {
                        boolean b = graphColor(v, adj, m, vertices, seen);
                        if (!b) {
                            vertices[v] = -1;
                            seen[v] = false;
                            i++;
                            break;
                        }
                    }
                }
                //All nodes will only be marked as seen if
                //their coresponding paint value is correct
                if (i == k) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean graphColoring(boolean[][] graph, int m, int n) {

        if (m == 1 && n > 1) {
            return false;
        }

        int[] vertices = new int[n];
        boolean[] seen = new boolean[n];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j]) {
                    adj.get(i).add(j);
                }
            }
        }

        Arrays.fill(vertices, -1);
        for (int i = 0; i < n; i++) {
            if (!seen[i]) {
                if (!graphColor(i, adj, m, vertices, seen)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new MColoring().graphColoring(
                new boolean[][]{{false, true, true, false}, {false, false, true, false}, {false, false, false, true}, {true, false, false, false}},
                3, 4));
    }
}
