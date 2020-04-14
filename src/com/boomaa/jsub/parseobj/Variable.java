package com.boomaa.jsub.parseobj;

import com.boomaa.jsub.Scheduleable;

@Scheduleable
public class Variable<T> extends Block {
    private T value;

    public Variable(String name, T value) {
        super(name);
        this.value = value;
    }

    public Variable(String name) {
        super(name);
    }

    public T getValue() {
        if (value == null) {
            throw new NullPointerException(getName() + " has not yet been initialized");
        }
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
