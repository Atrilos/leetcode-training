package com.test;

import java.util.Random;

public class QuickSelect {

    protected int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right)
            return nums[left];

        int pIndex = new Random().nextInt(left, right + 1);
        pIndex = partition(nums, left, right, pIndex);

        if (pIndex == k)
            return nums[k];
        else if (pIndex < k)
            return quickSelect(nums, pIndex + 1, right, k);
        return quickSelect(nums, left, pIndex - 1, k);
    }

    private int partition(int[] nums, int left, int right, int pIndex) {
        int pivot = nums[pIndex];
        swap(nums, pIndex, right);
        pIndex = left;

        for (int i = left; i <= right; i++)
            if (nums[i] <= pivot)
                swap(nums, i, pIndex++);

        return pIndex - 1;
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
