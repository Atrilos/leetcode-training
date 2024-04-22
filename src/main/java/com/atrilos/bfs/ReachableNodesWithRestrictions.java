package com.atrilos.bfs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.com/problems/reachable-nodes-with-restrictions/">2368. Reachable Nodes With Restrictions</a>
 */
public class ReachableNodesWithRestrictions {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            final int u = edge[0];
            final int v = edge[1];
            graph.computeIfAbsent(u, it -> new ArrayList<>());
            graph.computeIfAbsent(v, it -> new ArrayList<>());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        final Set<Integer> restrictedSet = Arrays.stream(restricted).boxed().collect(Collectors.toSet());
        int steps = 0;
        final Deque<Integer> deque = new ArrayDeque<>();

        deque.offer(0);

        while (!deque.isEmpty()) {
            Integer node = deque.removeFirst();
            if (restrictedSet.contains(node)) {
                continue;
            }
            restrictedSet.add(node);
            steps++;
            for (int next : graph.get(node)) {
                if (!restrictedSet.contains(next)) {
                    deque.offerLast(next);
                }
            }
        }

        return steps;
    }
}
