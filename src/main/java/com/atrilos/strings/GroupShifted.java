package com.atrilos.strings;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/group-shifted-strings/description/">249</a>
 */
public class GroupShifted {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strings) {
            int[] countArr = new int[s.length()];
            int normal = s.charAt(0) - 'a';
            for (int i = 0; i < s.length(); i++) {
                int code = (s.charAt(i) - 'a' - normal + 26) % 26;
                countArr[i] = code;
            }
            map
                    .computeIfAbsent(Arrays.toString(countArr), k -> new ArrayList<>())
                    .add(s);
        }

        return new ArrayList<>(map.values());
    }
}
