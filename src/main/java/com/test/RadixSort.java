package com.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class RadixSort {

    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    static void countSort(int[] arr, int exp, int radix) {
        int length = arr.length;
        int[] output = new int[length];
        int i;
        int[] count = new int[radix];

        // Store count of occurrences in count[]
        for (i = 0; i < length; i++)
            count[(arr[i] / exp) % radix]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < radix; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = length - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % radix] - 1] = arr[i];
            count[(arr[i] / exp) % radix]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current digit
        for (i = 0; i < length; i++)
            arr[i] = output[i];
    }

    // The main function to that sorts arr[] of size n using
    // Radix Sort
    static void radixSort(int[] arr, int radix) {
        if (radix < 2)
            throw new NumberFormatException("radix " + radix + " less than 2");
        // Find the maximum number to know number of digits
        int m = getMax(arr);

        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m / exp > 0; exp *= radix)
            countSort(arr, exp, radix);
    }

    private static int getMax(int[] arr) {
        return Arrays.stream(arr).parallel().max().orElseThrow();
    }


    /*Driver function to check for above function*/
    public static void main(String[] args) {
        Metrics metrics = new Metrics();
        int[] arr = generate();
        radixSort(arr, 1024 * 16);
        metrics.printMetrics();
    }

    private static int[] generate() {
        Integer[] res = IntStream.iterate(1, i -> i < 1000000, i -> i + 1).boxed().toArray(Integer[]::new);
        Collections.shuffle(Arrays.asList(res));
        return Arrays.stream(res).mapToInt(Integer::intValue).toArray();
    }
}
