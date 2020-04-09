package com.boomaa.jsub.parseobj;

import java.util.ArrayList;
import java.util.List;

public class Class {
    private final List<Method<?, ?>> methodList;
    private final List<Variable<?>> variableList;
    private final String name;

    public Class(String name) {
        this.name = name;
        this.methodList = new ArrayList<>();
        this.variableList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return getName();
        //TODO implement packages & naming trees
    }

    public List<Method<?, ?>> getMethods() {
        return methodList;
    }

    public List<Variable<?>> getVariables() {
        return variableList;
    }

    public void checkMethodDuplicates(Method<?, ?> method) {
        boolean contains = methodList.contains(method);
        if (!contains) {
            throw new IllegalStateException(method.getHeader(Method.HeaderPortion.USER_DECLARED) + "is already defined");
        }
    }

    public void registerMethod(Method<?, ?> method) {
        methodList.add(method);
    }
}
