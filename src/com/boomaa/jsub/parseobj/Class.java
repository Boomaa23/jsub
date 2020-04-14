package com.boomaa.jsub.parseobj;

import com.boomaa.jsub.*;

import java.util.ArrayList;
import java.util.List;

@Scheduleable
public class Class extends Block {
    private final List<Method<?, ?>> methodList;
    private final List<Variable<?>> variableList;
    private final List<Class> subclassList;

    public Class(String name) {
        super(name);
        this.methodList = new ArrayList<>();
        this.variableList = new ArrayList<>();
        //TODO move to subclass list OR make classhierarchy work
        this.subclassList = new ArrayList<>();
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

    public Class assignSubclass(Class subclass) {
        if (this.subclass == null) {
            this.subclass = subclass;
            return subclass;
        }
    }

    public void schedule() {
        Scheduler.addEvent(this);
    }

    @Override
    public boolean equals(Object obj) {
        return ((Class) obj).getSimpleName().equals(this.getSimpleName());
    }

    @Override
    public String toString() {
        Class cl = Parser.getClassHierarchy()
                .getRelativeClass(RelativeSelector.SUB, this);
        String n = null;
        if (cl != null) {
            n = cl.getSimpleName();
        }
        return "Class{" +
                "name=" + getSimpleName() +
                ", methodList=" + methodList +
                ", variableList=" + variableList +
                ", subclassList=" + n +
                '}';
    }
}
