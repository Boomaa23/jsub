package com.boomaa.jsub;

import java.util.List;

public class Scheduler {
    public static List<Event<?>> events;

    public static <T> void addEvent(T event) {
        events.add(new Event<T>(event));
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
