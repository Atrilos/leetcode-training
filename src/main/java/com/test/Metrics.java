package com.test;

public class Metrics {

    private static final Runtime runtime = Runtime.getRuntime();
    private static final double memoryUsed = ((double)runtime.totalMemory() - runtime.freeMemory()) / 1024 /1024;
    private static final long time = System.currentTimeMillis();

    public Metrics() {
    }

    public void printMetrics() {
        System.out.println(System.currentTimeMillis() - time + " ms");
        System.out.println(((double)runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024 - memoryUsed + " MB");
    }
}
