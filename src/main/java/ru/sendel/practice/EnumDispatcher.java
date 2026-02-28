package ru.sendel.practice;

import java.util.EnumMap;

public class EnumDispatcher {

    enum EventType { CREATED, PAID, CANCELLED }
    record Event(EventType type, String payload) {}
    interface Handler { void handle(Event e); }

    public static void main(String[] args) {
        var handlers = new EnumMap<EventType, Handler>(EventType.class);
        handlers.put(EventType.CREATED, e -> System.out.println("CREATED " + e.payload()));
        handlers.put(EventType.PAID, e -> System.out.println("PAID " + e.payload()));
        handlers.put(EventType.CANCELLED, e -> System.out.println("CANCELLED " + e.payload()));

        var event = new Event(EventType.PAID, "orderId=A-1");

        handlers.get(event.type).handle(event);
    }
}
