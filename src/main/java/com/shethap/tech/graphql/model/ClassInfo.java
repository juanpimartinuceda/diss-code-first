package com.shethap.tech.graphql.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ClassInfo {
    // need to add the possibility to query for specific class information, methods or fields. Listen to michael recordings
    String name;
    boolean isPublic;
    boolean isInterface;
    ArrayList<FieldInfo> fieldsInfo;
    ArrayList<MethodInfo> methods;

    public ClassInfo(Class c) {
        this.name = c.getName();
        this.isPublic = Modifier.isPublic(c.getModifiers());
        this.isInterface = c.isInterface();
        Field[] fields = c.getDeclaredFields();
        ArrayList<FieldInfo> fieldsInfo = new ArrayList<>();
        for (Field f : fields) {
            fieldsInfo.add(new FieldInfo(f));
        }
        this.fieldsInfo = fieldsInfo;
        Method[] methods = c.getDeclaredMethods();
        ArrayList<MethodInfo> methodInfo = new ArrayList<>();
        for (Method m: methods) {
            methodInfo.add(new MethodInfo(m));
        }
        this.methods = methodInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setInterface(boolean anInterface) {
        isInterface = anInterface;
    }

    public ArrayList<FieldInfo> getFields() {
        return fieldsInfo;
    }

    public void setFields(ArrayList<FieldInfo> fieldsInfo) {
        this.fieldsInfo = fieldsInfo;
    }

    public ArrayList<MethodInfo> getMethods() {
        return methods;
    }

    public void setMethods(ArrayList<MethodInfo> methods) {
        this.methods = methods;
    }
}
