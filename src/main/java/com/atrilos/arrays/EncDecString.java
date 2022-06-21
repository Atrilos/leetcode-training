package com.atrilos.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.
 *
 * Please implement encode and decode
 *
 * Example1
 *
 * Input: ["lint","code","love","you"]
 * Output: ["lint","code","love","you"]
 * Explanation:
 * One possible encode method is: "lint:;code:;love:;you"
 *
 * Example2
 *
 * Input: ["we", "say", ":", "yes"]
 * Output: ["we", "say", ":", "yes"]
 * Explanation:
 * One possible encode method is: "we:;say:;:::;yes"
 */
public class EncDecString {

    public static void main(String[] args) {
        EncDecString ed = new EncDecString();
        String encoded = ed.encode(List.of("linterrrrrrrr","codeeeeeeeeeeee","love","you"));
        System.out.println(ed.decode(encoded));
    }
    /**
     * @param strs: a list of strings
     * @return encodes a list of strings to a single string.
     */
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();

        for (String str : strs) {
            sb.append(str.length()).append('#').append(str);
        }

        return sb.toString();
    }

    /**
     * @param str: A string
     * @return decodes a single string to a list of strings
     */
    public List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        int index = 0;

        while (index < str.length()) {
            int j = index;
            while (str.charAt(j) != '#') {
                ++j;
            }
            int length = Integer.parseInt(str, index, j, 10);
            res.add(str.substring(j + 1, j + length + 1));
            index = j + 1 + length;
        }

        return res;
    }
}
