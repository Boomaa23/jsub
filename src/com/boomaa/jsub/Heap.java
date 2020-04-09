package com.boomaa.jsub;

import java.util.ArrayList;
import java.util.List;

public class Heap {
    public static List<Class<?>> objects;

    static {
        objects = new ArrayList<>();
    }

    public static void addObject(Class<?> obj) {
        objects.add(obj);
    }

    public static void writeHierarchy(String outputFilename) {
        //TODO implement hierarchy (classes, methods, vars) write to file
    }
}
