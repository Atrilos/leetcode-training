package com.atrilos.binarySearch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeMapTest {
    TimeMap timeMap;
    @BeforeEach
    void setUp() {
        timeMap = new TimeMap();
        timeMap.set("love", "high", 10);
        timeMap.set("love", "low", 20);
    }

    @Test
    void get() {
        assertEquals("", timeMap.get("love", 5));
        assertEquals("high", timeMap.get("love", 10));
        assertEquals("high", timeMap.get("love", 15));
        assertEquals("low", timeMap.get("love", 20));
        assertEquals("low", timeMap.get("love", 25));
    }
}