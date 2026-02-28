package ru.sendel.practice;

import java.util.HashMap;

public class MetricsAggregation {
    public static void main(String[] args) {
        var counts = new HashMap<String, Long>(64);

        // имитируем поток логов:
        log(counts, "/pay", 200);
        log(counts, "/pay", 500);
        log(counts, "/pay", 200);
        log(counts, "/pay", 500);
        log(counts, "/pay", 200);
        log(counts, "/orders", 200);

        System.out.println(counts);
    }

    static void log(HashMap<String, Long> counts, String path, int code) {
        counts.merge(path + ":" + code, 1L, Long::sum);
    }
}
