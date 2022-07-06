package com.atrilos.intervals;

import java.util.*;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.)
 * (0,8),(8,10) is not conflict at 8
 *
 * Example1
 *
 * Input: intervals = [(0,30),(5,10),(15,20)]
 * Output: 2
 * Explanation:
 * We need two meeting rooms
 * room1: (0,30)
 * room2: (5,10),(15,20)
 *
 * Example2
 *
 * Input: intervals = [(2,7)]
 * Output: 1
 * Explanation:
 * Only need one meeting room
 */
public class MeetingRoomsII {
    public int minMeetingRooms(List<Interval> intervals) {
        if(intervals.isEmpty())
            return 0;
        intervals.sort(Comparator.comparingInt(a -> a.start));
        Queue<Interval> minEndHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.end));
        int count = 0;

        for (Interval interval : intervals) {
            while(!minEndHeap.isEmpty() && interval.start >= minEndHeap.peek().end)
                minEndHeap.poll();
            minEndHeap.offer(interval);
            count = Math.max(count, minEndHeap.size());
        }

        return count;
    }
}
