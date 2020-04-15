package com.boomaa.jsub.parseobj;

import java.util.UUID;

public abstract class Block {
    protected final String name;
    protected final String uuid;

    public Block(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getSimpleName() {
        return getName(NameVerbosity.SIMPLE);
    }

    public String getName(NameVerbosity verbosity) {
        //TODO implement path-get
        switch (verbosity) {
            case SIMPLE:
                return name;
            case PATH:
                return name;
            case FULL:
                return name;
        }
        return name;
    }

    public enum NameVerbosity {
        SIMPLE, PATH, FULL
    }
}
