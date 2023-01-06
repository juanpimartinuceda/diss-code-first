package com.shethap.tech.graphql.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Description {

    JarFile jarFile;

    ArrayList<ClassInfo> classDescription;

    ArrayList<MethodInfo> methodDescription;//Que pasa cuando hay mas de un constructor? overrided methdos

    ArrayList<FieldInfo> fieldDescription;

    //Populate just the information you want to return. information from class and method are required to identify a field, same for method regarding its class.
    // Then the user is free to request any information they want by using GraphQL.

    public JarFile getJarFile() {
        return jarFile;
    }

    public void setJarFile(JarFile jarFile) {
        this.jarFile = jarFile;
    }

    public ArrayList<ClassInfo> getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(ArrayList<ClassInfo> classDescription) {
        this.classDescription = classDescription;
    }

    public ArrayList<MethodInfo> getMethodDescription() {
        return methodDescription;
    }

    public void setMethodDescription(ArrayList<MethodInfo> methodDescription) {
        this.methodDescription = methodDescription;
    }

    public ArrayList<FieldInfo> getFieldDescription() {
        return fieldDescription;
    }

    public void setFieldDescription(ArrayList<FieldInfo> fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public static Description getDescription(JarFile jarFile, DescriptionQuery query) {
        String className = query.getClassName();
        ArrayList<ClassInfo> classes = new ArrayList<>();
        ArrayList<MethodInfo> methods = new ArrayList<>();
        ArrayList<FieldInfo> fields = new ArrayList<>();
        if (className == null) {
            for(ClassInfo c : jarFile.getJarClasses()) {
                classes.add(c);
            }
        } else {
            for (ClassInfo c : jarFile.getJarClasses()) {
                if (className.equals(c.getName())) {
                    classes.add(c);
                }
            }
            String methodName = query.getMethodName();
            if (methodName == null) {
                for (MethodInfo m : classes.get(0).getMethods()) {
                    methods.add(m);
                }
            } else {
                for (MethodInfo m : classes.get(0).getMethods()) {
                    if (methodName.equals(m.getName())) {
                        methods.add(m);
                    }
                }
                String fieldName = query.getFieldName();
                if (fieldName == null) {
                    for (FieldInfo f : classes.get(0).getFields()) {
                        fields.add(f);
                    }
                } else {
                    for (FieldInfo f : classes.get(0).getFields()) {
                        if (fieldName.equals(f.getName())) {
                            fields.add(f);
                        }
                    }
                }
            }
        }
        Description description = new Description(jarFile, classes, methods, fields);
        return description;
    }

    public Description(JarFile jarFile, ArrayList<ClassInfo> classDescription, ArrayList<MethodInfo> methodDescription, ArrayList<FieldInfo> fieldDescription) {
        this.jarFile = jarFile;
        this.classDescription = classDescription;
        this.methodDescription = methodDescription;
        this.fieldDescription = fieldDescription;
    }
}
