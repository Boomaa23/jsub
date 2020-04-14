package com.boomaa.jsub;

import com.boomaa.jsub.parseobj.Class;

import java.util.ArrayList;

//TODO a note: super/subclasses as they are are not actually sub/superclasses, instead they're nested classes. perhaps redo terminology.
//TODO use this instead of subclass list OR vice versa, make this perhaps more inclusive for nested classes (many-to-many relationship)
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
        try {
            switch (selector) {
                case SUPER:
                    return super.get(indexOf(focus) - 1);
                case SUB:
                    return super.get(indexOf(focus) + 1);
            }
        } catch (IndexOutOfBoundsException e) {
            return null;
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
                c2.assignSubclass(c1);
                break;
            case SUB:
                super.add(super.indexOf(c1) + 1, c2);
                c1.assignSubclass(c2);
                break;
        }
    }

    public static void writeHierarchy(String outputFilename) {
        //TODO implement hierarchy (classes, methods, vars) write to file
    }
}
