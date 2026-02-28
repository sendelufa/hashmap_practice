package ru.sendel;

import java.util.HashMap;

public class ResizeSpikeDemo {
    public static void main(String[] args) throws Exception {
        resizeSpikeDemo();
    }

    private static void resizeSpikeDemo() throws Exception {
        System.out.println("3) RESIZE SPIKES: detect expensive puts during resize and show capacity/threshold");

        // Start small to trigger multiple resizes.
        var map = new HashMap<Integer, Integer>(16, 0.75f);

        int lastCap = MapReflectUtils.capacityOf(map);
        int lastThr = MapReflectUtils.thresholdOf(map);

        System.out.printf("  start: capacity=%d threshold=%d%n", lastCap, lastThr);

        // We'll insert enough elements to force several resizes.
        // Track per-put time; print only "spikes" and resize moments.
        final int n = 100_000;
        final double spikeMs = 0.10; // threshold for "noticeable" put time on most machines

        long worstNs = 0;
        int worstI = -1;

        for (int i = 1; i <= n; i++) {
            long s = System.nanoTime();
            map.put(i, i);
            long e = System.nanoTime();
            long dt = e - s;

            if (dt > worstNs) {
                worstNs = dt;
                worstI = i;
            }

            int cap = MapReflectUtils.capacityOf(map);
            int thr = MapReflectUtils.thresholdOf(map);

            boolean resized = cap != lastCap;
            boolean spike = (dt / 1_000_000.0) >= spikeMs;

            if (resized) {
                System.out.printf("  RESIZE detected at size=%d: capacity %d -> %d, threshold %d -> %d%n",
                        map.size(), lastCap, cap, lastThr, thr);
                lastCap = cap;
                lastThr = thr;
            }

            // Print only interesting spikes to keep output readable while recording.
            if (spike) {
                System.out.printf("  spike put at i=%d: %.3f ms (capacity=%d, size=%d)%n",
                        i, dt / 1_000_000.0, cap, map.size());
            }
        }

        System.out.printf("  worst put: i=%d, %.3f ms%n", worstI, worstNs / 1_000_000.0);
        System.out.println("  Note: spikes usually correlate with resize and internal re-bucketing work.");
    }
}
