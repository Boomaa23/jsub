package com.boomaa.jsub.parseobj;

import com.boomaa.jsub.Scheduleable;

import java.util.List;

@Scheduleable
public class Method<T, K> extends Block {
    private final String action;
    private final Class enclosingClass;
    private final List<java.lang.Class<?>> parameterTypes;

    public Method(String name, String action, Class enclosingClass, List<java.lang.Class<?>> parameterTypes) {
        super(name);
        this.action = action;
        this.enclosingClass = enclosingClass;
        this.parameterTypes = parameterTypes;
        enclosingClass.checkMethodDuplicates(this);
        enclosingClass.registerMethod(this);
    }

    public K run(List<T> parameters) {
        //TODO check passed parameters against types
        //TODO implement java compiler API for string action parsing
        return null;
    }

    public String getHeader(HeaderPortion portion) {
        if (portion == HeaderPortion.USER_DECLARED) {
            return "'" + getSimpleName() + "(" + csvParamTypes() + ")' already implemented in '" + enclosingClass.getName(NameVerbosity.FULL) + "'";
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
        return compare.getSimpleName().equals(this.getSimpleName())
                && compare.parameterTypes.containsAll(this.parameterTypes);
    }

    public enum HeaderPortion {
        USER_DECLARED, RESERVED
    }

    public static class Builder extends Block {
        private String action;
        private Class enclosingClass;
        private List<java.lang.Class<?>> parameterTypes;

        public Builder(String name) {
            super(name);
        }

        public Builder setAction(String action) {
            this.action = action;
            return this;
        }

        public Builder setEnclosingClass(Class enclosingClass) {
            this.enclosingClass = enclosingClass;
            return this;
        }

        public Builder setParameterTypes(List<java.lang.Class<?>> parameterTypes) {
            this.parameterTypes = parameterTypes;
            return this;
        }

        public <H, B> Method<H, B> build() {
            if (action != null && enclosingClass != null && parameterTypes != null) {
                return new Method<>(getSimpleName(), action, enclosingClass, parameterTypes);
            } else {
                throw new IllegalStateException("Cannot build incomplete method");
            }
        }
    }
}
