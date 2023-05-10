package com.atrilos.slidingWindow;

/**
 * <a href="https://leetcode.com/problems/greatest-common-divisor-of-strings/">...</a>
 */
public class GcdOfStrings {

    public static void main(String[] args) {
        System.out.println(new GcdOfStrings().gcdOfStrings("ABCABC", "ABC"));
    }

    public String gcdOfStrings(String str1, String str2) {

        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        int l1 = str1.length();
        int l2 = str2.length();

        while (l2 != 0) {
            int tmp = l2;
            l2 = l1 % l2;
            l1 = tmp;
        }

        return str1.substring(0, l1);
    }
}
