package com.atrilos.heapPrioQueue;

import java.util.*;

/**
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task.
 * Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 * <p>
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array),
 * that is that there must be at least n units of time between any two same tasks.
 * <p>
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 * Example 2:
 * <p>
 * Input: tasks = ["A","A","A","B","B","B"], n = 0
 * Output: 6
 * Explanation: On this case any permutation of size 6 would work since n = 0.
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * And so on.
 * Example 3:
 * <p>
 * Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * Output: 16
 * Explanation:
 * One possible solution is
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= task.length <= 10^4
 * tasks[i] is upper-case English letter.
 * The integer n is in the range [0, 100].
 */
public class TaskScheduler {

    public static void main(String[] args) {
        System.out.println(new TaskScheduler().leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C'}, 3));
    }

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int cycle = 0;

        for (char task : tasks) {
            map.merge(task, 1, Integer::sum);
        }

        maxHeap.addAll(map.values());

        while (!maxHeap.isEmpty()) {
            List<Integer> temp = new ArrayList<>();

            for (int i = 0; i < n + 1; i++) {
                if (!maxHeap.isEmpty()) {
                    temp.add(maxHeap.poll());
                }
            }

            for (Integer i : temp) {
                if (i - 1 > 0) {
                    maxHeap.add(i - 1);
                }
            }

            cycle += maxHeap.isEmpty() ? temp.size() : n + 1;
        }

        return cycle;
    }

    public int leastIntervalMath(char[] tasks, int n) {
        int[] map = new int[26];

        for (char c : tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int max = map[25] - 1;
        int idle = max * n;
        for (int i = 24; i >= 0; i--)
            idle -= Math.min(map[i], max);
        return idle > 0 ? idle + tasks.length : tasks.length;
    }
}
