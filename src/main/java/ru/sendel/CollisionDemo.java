package ru.sendel;

import ru.sendel.keys.BadKey;
import ru.sendel.keys.GoodKey;

import java.util.HashMap;

public class CollisionDemo {
    public static void main(String[] args) throws Exception {
        collisionDemo();
    }

    private static void collisionDemo() throws Exception {
        System.out.println("1) COLLISIONS: GoodKey vs BadKey performance and bin length");

        final int n = 10_000;

        // Good keys
        var goodMap = new HashMap<GoodKey, Integer>(n, 0.75f);
        long t1 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            goodMap.put(new GoodKey(i), i);
        }
        long t2 = System.nanoTime();

        // Bad keys (all collide into one bin)
        var badMap = new HashMap<BadKey, Integer>(n, 0.75f);
        long t3 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            badMap.put(new BadKey(i), i);
        }
        long t4 = System.nanoTime();

        // Read a few gets to show lookup cost difference too
        long gGetStart = System.nanoTime();
        for (int i = 0; i < 5_000; i++) {
            goodMap.get(new GoodKey(i));
        }
        long gGetEnd = System.nanoTime();

        long bGetStart = System.nanoTime();
        for (int i = 0; i < 5_000; i++) {
            badMap.get(new BadKey(i));
        }
        long bGetEnd = System.nanoTime();

        System.out.printf("  put GoodKey: %.2f ms%n", (t2 - t1) / 1_000_000.0);
        System.out.printf("  put BadKey : %.2f ms%n", (t4 - t3) / 1_000_000.0);
        System.out.printf("  get GoodKey: %.2f ms%n", (gGetEnd - gGetStart) / 1_000_000.0);
        System.out.printf("  get BadKey : %.2f ms%n", (bGetEnd - bGetStart) / 1_000_000.0);

        // Inspect bin length at some index (all BadKey collide into a single bin)
        int badCapacity = MapReflectUtils.capacityOf(badMap);
        int foundIndex = MapReflectUtils.findFirstNonNullBinIndex(badMap);
        int len = MapReflectUtils.binLength(badMap, foundIndex);

        System.out.printf("  badMap capacity=%d," +
                        "firstNonNullBinIndex=%d, binLength=%d%n",
                badCapacity, foundIndex, len);

        System.out.println("  Idea: same functionality," +
                " wildly different performance because collisions.");
    }
}
