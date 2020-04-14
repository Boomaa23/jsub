package com.boomaa.jsub;

import com.boomaa.jsub.parseobj.Block;

public class Relation<T extends Block> {
    @SafeVarargs
    public Relation(T focus, T... other) {
    }

    public enum RelationType {
        SUPER, SUB
    }
}
