package com.boomaa.jsub.parseobj;

public abstract class Block {
    private final String name;

    public Block(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
