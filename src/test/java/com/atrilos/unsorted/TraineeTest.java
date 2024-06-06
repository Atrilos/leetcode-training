package com.atrilos.unsorted;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TraineeTest {

    @Test
    void solution() {
        for (int j = 0; j < 10000; j++) {
            Random random = new Random();
            Trainee out = new Trainee();
            int n = 1000;
            int n1 = random.nextInt(0, n + 1);
            int n2 = random.nextInt(0, n + 1);
            int n3 = random.nextInt(0, n + 1);

            List<Integer> arr1 = new ArrayList<>();
            List<Integer> arr2 = new ArrayList<>();
            List<Integer> arr3 = new ArrayList<>();

            for (int i = 0; i < n1; i++) {
                arr1.add(random.nextInt(-1, 1));
            }
            for (int i = 0; i < n2; i++) {
                arr2.add(random.nextInt(-1, 1));
            }
            for (int i = 0; i < n3; i++) {
                arr3.add(random.nextInt(-1, 1));
            }
            arr1.sort((a, b) -> Integer.compare(b, a));
            arr2.sort((a, b) -> Integer.compare(b, a));
            arr3.sort((a, b) -> Integer.compare(b, a));
            List<Integer> expected = new ArrayList<>();
            expected.addAll(arr1);
            expected.addAll(arr2);
            expected.addAll(arr3);
            expected.sort(Comparator.comparingInt(a -> a));

            int[] arr1a = arr1.stream().mapToInt(it -> it).toArray();
            int[] arr2a = arr2.stream().mapToInt(it -> it).toArray();
            int[] arr3a = arr3.stream().mapToInt(it -> it).toArray();

            int[] solution = out.solution(arr1a, arr2a, arr3a);
            assertArrayEquals(expected.stream().mapToInt(it -> it).toArray(), solution);
        }
    }
}