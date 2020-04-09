package com.boomaa.jsub.parseobj;

public class Variable<T> {
    private final String name;
    private T value;

    public Variable(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public Variable(String name) {
        this.name = name;
    }

    public T getValue() {
        if (value == null) {
            throw new NullPointerException(name + " has not yet been initialized");
        }
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
