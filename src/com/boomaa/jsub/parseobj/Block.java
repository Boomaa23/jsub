package com.boomaa.jsub.parseobj;

public abstract class Block {
    private final String name;
    private boolean closed = false;

    public Block(String name) {
        this.name = name;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed() {
        setClosed(true);
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
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
