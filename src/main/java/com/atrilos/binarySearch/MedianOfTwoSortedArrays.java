package com.atrilos.binarySearch;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <p>
 * The overall run time complexity should be O(log (m+n)).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 * <p>
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 */
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[]{1, 2, 3, 4, 5}, new int[]{7}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] shortArray = nums1;
        int[] longArray = nums2;
        if (nums1.length > nums2.length) {
            shortArray = nums2;
            longArray = nums1;
        }

        int shortLen = shortArray.length;
        int longLen = longArray.length;
        int half = (shortLen + longLen) / 2;

        int start = 0, end = shortLen - 1;
        while (true) {
            // binary search the shorter array
            int i = (end < 0) ? end : start + (end - start) / 2;
            int j = half - i - 2;

            int shortLeft = (i >= 0) ? shortArray[i] : Integer.MIN_VALUE;
            int shortRight = (i + 1 < shortLen) ? shortArray[i + 1] : Integer.MAX_VALUE;
            int longLeft = (j >= 0) ? longArray[j] : Integer.MIN_VALUE;
            int longRight = (j + 1 < longLen) ? longArray[j + 1] : Integer.MAX_VALUE;

            // partition is right
            if (shortLeft <= longRight && longLeft <= shortRight) {
                if ((shortLen + longLen) % 2 != 0) { //odd
                    return Math.min(shortRight, longRight);
                } else { //even
                    return (double) (Math.max(shortLeft, longLeft) + Math.min(shortRight, longRight)) / 2;
                }
            } else if (shortLeft > longRight) { // the median value of shorter array is too big
                end = i - 1; // move to the left part
            } else { // the value of longer array is too big
                start = i + 1; // move to the right part
            }
        }
    }
}
//extremely hard problem to solve in O(log(n) + log(m))
