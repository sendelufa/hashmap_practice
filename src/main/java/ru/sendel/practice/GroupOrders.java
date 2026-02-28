package ru.sendel.practice;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class GroupOrders {
    enum Status {CONFIRMED, PACKED, SHIPPED, DELIVERED}

    record StatusEvent(String orderId, Status status, Instant at) {
    }

    public static void main(String[] args) {
        var events = List.of(
                new StatusEvent("A-1", Status.CONFIRMED, Instant.parse("2026-02-27T10:00:00Z")),
                new StatusEvent("A-1", Status.PACKED, Instant.parse("2026-02-27T10:05:00Z")),
                new StatusEvent("B-2", Status.PACKED, Instant.parse("2026-02-20T10:05:00Z")),
                new StatusEvent("C-999", Status.SHIPPED, Instant.parse("2026-02-27T10:40:00Z"))

        );

        var byOrder = new HashMap<String, TreeSet<StatusEvent>>(16, 0.75f);
        for (var e : events) {
            byOrder.computeIfAbsent(e.orderId(), st ->
                    new TreeSet<>(Comparator.comparing(StatusEvent::at).reversed()))
                    .add(e);
        }

        System.out.println(byOrder);
    }
}
