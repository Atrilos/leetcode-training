package com.atrilos.dp1d;

/**
 * Given a string s, return the longest palindromic substring in s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: s = "cbbd"
 * Output: "bb"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        String max = "";
        for (int i = 0; i < s.length(); i++) {
            String odd = findPalindrome(s, i, i);  //odd length palindrome
            String even = findPalindrome(s, i, i + 1);  //even length palindrome
            if (odd.length() > max.length()) {
                max = odd;
            }
            if (even.length() > max.length()) {
                max = even;
            }
        }
        return max;
    }

    private String findPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    public String findPalindromeWithManachersAlgorithm(String s) {
        // Insert bogus characters in-between s and at outer boundary to make a string t.
        String BOGUS = "|";
        StringBuilder sb = new StringBuilder();
        sb.append(BOGUS);
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            sb.append(BOGUS);
        }
        String t = sb.toString();

        // The radius of the longest palindrome centered at each place in t.
        int[] radii = new int[t.length()];

        int center = 0;
        int radius = 0;

        while (center < t.length()) {
            /*
             * At the start of each iteration, radius is already set to the lower bound for the longest radius.
             * In the first iteration, radius is set to 0, but can be higher.
             * */

            // Expand radius from center to find the longest palindrome
            // of t.substring(center - radius, center + radius + 1)
            while (center - radius >= 0 && center + radius + 1 <= t.length()
                    && t.charAt(center - radius) == t.charAt(center + radius + 1 - 1)) radius++;

            // Store the longest radius of palindrome from the center.
            radii[center] = radius;

            // Increment center.
            int oldCenter = center;
            int oldRadius = radius;
            center++;

            // Reset radius.
            radius = 0;

            while (center < oldCenter + oldRadius) {
                /*
                 * If the updated center lies within the old palindrome:
                 * t.substring(oldCenter - oldRadius, oldCenter + oldRadius + 1),
                 * one can use the precomputed data for the updated center's mirror point,
                 * because every character with a palindrome has a mirrored character reflected across its center.
                 * */
                int mirroredCenter = oldCenter - (center - oldCenter);
                // The possible maximum radius of palindrome for the updated center is limited,
                // if it is a palindromic substring within the old palindrome that has a reflected mirror.
                int maxMirroredRadius = oldCenter + oldRadius - center;

                if (radii[mirroredCenter] < maxMirroredRadius) {
                    // If the precomputed radius of the mirrored center is smaller than the maximum possible radius,
                    // then the longest palindrome of center and its mirror are palindromic substring
                    // of the old palindrome, and must have radius of radii[center]
                    // that is smaller radius then maxMirroredRadius.
                    radii[center] = radii[mirroredCenter];
                    center++;
                } else if (radii[mirroredCenter] > maxMirroredRadius) {
                    // If the precomputed radius of the mirrored center is larger than the maximum possible radius,
                    // then the longest palindrome of center have the same limited boundary like the old one,
                    // which means the maximum possible mirrored radius is also the radius of the palindrome.
                    // But the mirrored center have a larger palindromic radius because its preceding characters
                    // did not limit it.
                    radii[center] = maxMirroredRadius;
                    center++;
                } else {
                    // If the mirrored palindrome has the maximum possible radius,
                    // then its preceding character is the reason that limited the old palindrome and itself.
                    // Therefore, the precomputed data is no longer useful.
                    // But for the palindrome of the updated center,
                    // its preceding characters might still match those that outside the old palindrome.
                    // One should check and growth the palindrome seed of the updated center and the know
                    // maximum radius: t.substring(center - maxMirroredRadius, center + maxMirroredRadius + 1).
                    radius = maxMirroredRadius;
                    break;
                }
            }
        }

        // Find the longest palindrome by their radius.
        radius = Integer.MIN_VALUE;
        for (int i = 0; i < t.length(); i++) {
            if (radii[i] > radius) {
                center = i;
                radius = radii[i];
            }
        }

        // Notice the radius has one overstep back in the expanding.
        return t.substring(center - radius + 1, center + radius + 1 - 1).replace(BOGUS, "");
    }
}
