package com.atrilos.bfs;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/open-the-lock/description/">752. Open the Lock</a>
 */
public class OpenTheLock {
    public int openLock(String[] deadends, String target) {
        int steps = 0;
        final Set<String> seen = new HashSet<>(Arrays.asList(deadends));
        final Deque<String> deque = new ArrayDeque<>();
        deque.add("0000");


        while (!deque.isEmpty()) {
            for (int i = 0, size = deque.size(); i < size; i++) {
                String entry = deque.removeFirst();
                if (seen.contains(entry)) {
                    continue;
                }
                if (target.equals(entry)) {
                    return steps;
                }
                seen.add(entry);
                for (int j = 0; j < 4; j++) {
                    for (int k = -1; k <= 1; k += 2) {
                        final char[] arr = entry.toCharArray();
                        arr[j] = (char) ((arr[j] - '0' + k + 10) % 10 + '0');
                        String next = new String(arr);
                        if (!seen.contains(next)) {
                            seen.add(next);
                            deque.offerLast(next);
                        }
                    }
                }
            }
            steps++;
        }

        return -1;
    }
}
