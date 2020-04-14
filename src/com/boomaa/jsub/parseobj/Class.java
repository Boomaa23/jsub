package com.boomaa.jsub.parseobj;

import com.boomaa.jsub.Scheduleable;
import com.boomaa.jsub.Scheduler;

import java.util.ArrayList;
import java.util.List;

@Scheduleable
public class Class extends Block {
    private final List<Method<?, ?>> methodList;
    private final List<Variable<?>> variableList;
    private Class subclass;

    public Class(String name) {
        super(name);
        this.methodList = new ArrayList<>();
        this.variableList = new ArrayList<>();
        this.subclass = null;
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

    //TODO see about deleting this subclass thing and using ClassHierarchy instead
    public Class getSubclass() {
        return subclass;
    }

    public void checkMethodDuplicates(Method<?, ?> method) {
        if (!methodList.contains(method)) {
            throw new IllegalArgumentException(method.getHeader(Method.HeaderPortion.USER_DECLARED) + "is already defined");
        }
    }

    public <T, K> void registerMethod(Method<T, K> method) {
        methodList.add(method);
    }

    public Class createSubclass(Class subclass) {
        if (this.subclass != null) {
            this.subclass = subclass;
            return subclass;
        } else {
            throw new IllegalArgumentException("Class " + getFullName() + " cannot have multiple subclasses");
        }
    }

    public void schedule() {
        Scheduler.addEvent(this);
    }

    @Override
    public boolean equals(Object obj) {
        return ((Class) obj).getName().equals(this.getName());
    }
}
