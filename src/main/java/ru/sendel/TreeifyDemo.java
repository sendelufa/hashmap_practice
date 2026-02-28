package ru.sendel;

import ru.sendel.keys.BadKey;

import java.util.HashMap;

public class TreeifyDemo {

    public static void main(String[] args) throws Exception {
        treeifyDemo();
    }

    private static void treeifyDemo() throws Exception {
        System.out.println("2) TREEIFY: force a single bin to become a tree (HashMap$TreeNode)");

        // Ensure MIN_TREEIFY_CAPACITY condition is satisfied from the start.
        // In HashMap, treeify happens only when table length >= 64 (otherwise it resizes instead).
        var map = new HashMap<BadKey, Integer>(64);

        // Insert enough colliding keys to exceed TREEIFY_THRESHOLD (8).
        // We insert 12 to be safely above threshold.
        for (int i = 0; i < 12; i++) {
            map.put(new BadKey(i), i);
            System.out.printf("added %2d", i);printHeadClass(map);
        }

        System.out.println("  Expectation: java.util.HashMap$TreeNode (treeified bin).");
    }

    private static <K,V> void  printHeadClass(HashMap<K, V> map) throws Exception {
        int idx = MapReflectUtils.findFirstNonNullBinIndex(map);
        Object head = MapReflectUtils.binHead(map, idx);

        System.out.println("  bin head class: " + head.getClass().getName());
    }
}
