package com.boomaa.jsub.parseobj;

import com.boomaa.jsub.*;

import java.util.*;

@Scheduleable
public class Class extends Block {
    private static final Map<String, Class> classesList;
    private final List<Method<?, ?>> methodList;
    private final List<Variable<?>> variableList;
    private final List<String> subclassList;
    private final List<String> nestedClassList;
    private String enclosingClass;
    private String superclass;

    static {
        classesList = new LinkedHashMap<>();
    }

    public Class(String name) {
        super(name);
        this.methodList = new ArrayList<>();
        this.variableList = new ArrayList<>();
        this.subclassList = new ArrayList<>();
        this.nestedClassList = new ArrayList<>();
        classesList.put(super.uuid, this);
    }

    public List<Method<?, ?>> getMethods() {
        return methodList;
    }

    public List<Variable<?>> getVariables() {
        return variableList;
    }

    public Class getSuperclass() {
        return classesList.get(superclass);
    }

    public Class getEnclosingClass() {
        return classesList.get(enclosingClass);
    }

    public List<Class> getSubclasses() {
        return getMultiRelation(RelationSelector.SUB);
    }

    public List<Class> getNestedClasses() {
        return getMultiRelation(RelationSelector.NESTED);
    }

    private List<Class> getMultiRelation(RelationSelector selector) {
        List<String> inputList = null;
        if (selector == RelationSelector.SUB) {
            inputList = subclassList;
        } else if (selector == RelationSelector.NESTED) {
            inputList = nestedClassList;
        }

        List<Class> output = new ArrayList<>();
        assert inputList != null;
        for (String uuid : inputList) {
            output.add(classesList.get(uuid));
        }
        return output;
    }

    public static Class master() {
        return classesList.entrySet().iterator().next().getValue();
    }

    public void addRelation(RelationSelector selector, Class toAdd) {
        addRelation(selector, this, toAdd);
    }

    public static void addRelation(RelationSelector selector, Class c1, Class c2) {
        switch (selector) {
            case SUPER:
                c2.subclassList.add(c1.uuid);
                c1.superclass = c2.uuid;
                break;
            case SUB:
                c1.subclassList.add(c2.uuid);
                c2.superclass = c1.uuid;
                break;
            case NESTED:
                c1.nestedClassList.add(c2.uuid);
                c2.enclosingClass = c1.uuid;
                break;
        }
    }

    public <T, K> void checkMethodDuplicates(Method<T, K> method) {
        if (!methodList.contains(method)) {
            throw new IllegalArgumentException(method.getHeader(Method.HeaderPortion.USER_DECLARED) + "is already defined");
        }
    }

    public <T, K> void registerMethod(Method<T, K> method) {
        methodList.add(method);
    }

    public static void writeHierarchy(String outputFilename) {
        //TODO implement hierarchy (classes, methods, vars) write to file
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
        return "Class{" +
                "name=" + name +
                ", uuid=" + uuid +
                ", methodList=" + methodList +
                ", variableList=" + variableList +
                ", subclassList=" + subclassList +
                ", nestedClassList=" + nestedClassList +
                ", enclosedClassList=" + enclosingClass +
                ", superclass='" + superclass +
                '}';
    }
}
