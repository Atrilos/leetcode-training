package com.atrilos.mathGeometry;

public class CountIntegerEvenDigitSum {
    public static void main(String[] args) {
        System.out.println(new CountIntegerEvenDigitSum().countEven(4));
    }

    public int countEven(int num) {
        int count = 0;
        for (int i = 2; i <= num; i++) {
            if (findSum(i) % 2 == 0)
                count++;
        }
        return count;
    }

    public int findSum(int i) {
        int result = 0;
        while (i != 0) {
            result += i % 10;
            i /= 10;
        }
        return result;
    }
}
