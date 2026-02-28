package ru.sendel;

import java.lang.reflect.Field;
import java.util.HashMap;

public class MapReflectUtils {
    public static int capacityOf(HashMap<?, ?> map) throws Exception {
        Object[] table = tableOf(map);
        return table == null ? 0 : table.length;
        // Note: before first insertion, table can be null.
    }

    public static int thresholdOf(HashMap<?, ?> map) throws Exception {
        Field thr = HashMap.class.getDeclaredField("threshold");
        thr.setAccessible(true);
        return (int) thr.get(map);
    }

    public static Object[] tableOf(HashMap<?, ?> map) throws Exception {
        Field table = HashMap.class.getDeclaredField("table");
        table.setAccessible(true);
        return (Object[]) table.get(map);
    }

    public static int findFirstNonNullBinIndex(HashMap<?, ?> map) throws Exception {
        Object[] table = tableOf(map);
        if (table == null) return -1;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) return i;
        }
        return -1;
    }

    public static Object binHead(HashMap<?, ?> map, int index) throws Exception {
        Object[] table = tableOf(map);
        if (table == null || index < 0 || index >= table.length) return null;
        return table[index];
    }

    public static int binLength(HashMap<?, ?> map, int index) throws Exception {
        Object head = binHead(map, index);
        if (head == null) return 0;

        // HashMap bins are either Node chain or TreeNode chain.
        // Both have a "next" field linking nodes in iteration order.
        int len = 0;
        Object cur = head;
        while (cur != null) {
            len++;
            cur = getField(cur, "next");
        }
        return len;
    }

    public static Object getField(Object obj, String name) throws Exception {
        Class<?> c = obj.getClass();
        while (c != null) {
            try {
                Field f = c.getDeclaredField(name);
                f.setAccessible(true);
                return f.get(obj);
            } catch (NoSuchFieldException ignored) {
                c = c.getSuperclass();
            }
        }
        throw new NoSuchFieldException(name);
    }
}
