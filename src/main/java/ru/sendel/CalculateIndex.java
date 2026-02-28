package ru.sendel;

public class CalculateIndex {

    static void main() {
        var key = "key";
        System.out.println(key.hashCode());

        String s1 = "Aa";
        String s2 = "BB";
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        // Условия коллизии
        //s1.hashCode() == s2.hashCode(); // true
        //s1.equals(s2);                  // false — разные строки!
    }
}
