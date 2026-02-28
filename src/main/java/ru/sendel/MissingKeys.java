package ru.sendel;

import java.util.HashMap;
import java.util.Objects;

public class MissingKeys {
    public static void main(String[] args) {
        var map = new HashMap<MutKey, String>();
        var key = new MutKey(1);
        map.put(key, "v");
        System.out.println(map.get(key));
        key.value = 2;
        System.out.println(map.get(key));

        System.out.println(map.containsValue("v"));

        map.put(null,"d");
        System.out.println(map.get(null));
    }

    private static class MutKey {
        int value;

        public MutKey(int value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }
    }
}
