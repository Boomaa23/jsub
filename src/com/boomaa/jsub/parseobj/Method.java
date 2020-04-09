package com.boomaa.jsub.parseobj;

import java.util.List;

public class Method<T, K> {
    private final String methodName;
    private final String action;
    private final Class superclass;
    private final List<java.lang.Class<?>> parameterTypes;

    public Method(String methodName, String action, Class superclass, List<java.lang.Class<?>> parameterTypes) {
        this.methodName = methodName;
        this.action = action;
        this.superclass = superclass;
        this.parameterTypes = parameterTypes;
        superclass.checkMethodDuplicates(this);
        superclass.registerMethod(this);
    }

    public K run(List<T> parameters) {
        //TODO check passed parameters against types
        //TODO implement java compiler API for string action parsing
        return null;
    }

    public String getHeader(HeaderPortion portion) {
        if (portion == HeaderPortion.USER_DECLARED) {
            return "'" + methodName + "(" + csvParamTypes() + ")' already implemented in '" + superclass.getFullName() + "'";
        } else {
            return null;
            //TODO implement final, static, access modifiers, etc
        }
    }

    private String csvParamTypes() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < parameterTypes.size();i++) {
            sb.append(parameterTypes.get(i).getName());
            if (i != parameterTypes.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        Method<T, K> compare = (Method<T, K>) obj;
        return compare.methodName.equals(this.methodName)
                && compare.parameterTypes.containsAll(this.parameterTypes);
    }

    public enum HeaderPortion {
        USER_DECLARED, RESERVED
    }
}
