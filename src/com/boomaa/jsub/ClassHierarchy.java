package com.boomaa.jsub;

import com.boomaa.jsub.parseobj.Class;

import java.util.ArrayList;

public class ClassHierarchy extends ArrayList<Class> {
    public ClassHierarchy(Class master) {
        super.add(master);
    }

    public Class current() {
        return super.get(size() - 1);
    }

    public Class master() {
        return super.get(0);
    }

    public Class getRelativeClass(RelativeSelector selector, Class focus) {
        switch (selector) {
            case SUPER:
                return super.get(indexOf(focus) - 1);
            case SUB:
                return super.get(indexOf(focus) + 1);
        }
        return master();
    }

    public void addRelativeClass(RelativeSelector selector, Class toAdd) {
        addRelativeClass(selector, current(), toAdd);
    }

    public void addRelativeClass(RelativeSelector selector, Class c1, Class c2) {
        switch (selector) {
            case SUPER:
                super.add(super.indexOf(c1) - 1, c2);
                c2.createSubclass(c1);
            case SUB:
                super.add(super.indexOf(c1) + 1, c2);
                c1.createSubclass(c2);
        }
    }

    public static void writeHierarchy(String outputFilename) {
        //TODO implement hierarchy (classes, methods, vars) write to file
    }
}
