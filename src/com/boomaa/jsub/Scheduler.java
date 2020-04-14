package com.boomaa.jsub;

import java.util.List;

public class Scheduler {
    public static List<Event<?>> events;

    public static <T> void addEvent(T event) {
        if (event.getClass().isAnnotationPresent(Scheduleable.class)) {
            events.add(new Event<>(event));
        } else {
            throw new IllegalArgumentException("Cannot create event from non-parseable type");
        }
    }

    public static void execute() {
        //TODO implement schedule execution
    }

    public static class Event<T> {
        private final T event;

        public Event(T event) {
            this.event = event;
        }

        public T get() {
            return event;
        }
    }
}
