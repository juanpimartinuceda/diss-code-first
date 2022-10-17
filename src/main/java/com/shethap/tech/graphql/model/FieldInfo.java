package com.shethap.tech.graphql.model;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldInfo {
    String name;
    String fieldClassName;
    boolean isPublic;

    public FieldInfo(Field f) {
        this.name = f.getName();
        this.fieldClassName = f.getClass().getSimpleName();
        this.isPublic = Modifier.isPublic(f.getModifiers());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldClassName() {
        return fieldClassName;
    }

    public void setFieldClassName(String fieldClassName) {
        this.fieldClassName = fieldClassName;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
