package ru.sendel;

public class CalculateIndex {

    public static void main(String[] args) {
        var key = "key";
        System.out.println(key.hashCode());

        String s1 = "Aa";
        String s2 = "BB";
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        // Условия коллизии
        System.out.println(s1.hashCode() == s2.hashCode()); // true
        System.out.println(s1.equals(s2));                  // false — разные строки!
    }
}
