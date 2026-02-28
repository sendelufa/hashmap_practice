package ru.sendel.keys;

public record BadKey(int id) {
    @Override public int hashCode() { return 1; }

    @Override public boolean equals(Object o) {
        return (o instanceof BadKey(int id1))
                && this.id == id1;
    }
}


