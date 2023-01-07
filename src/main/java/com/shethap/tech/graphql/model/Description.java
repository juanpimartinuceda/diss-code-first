package com.shethap.tech.graphql.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Description {

    JarFile jarFile;

    ArrayList<ClassInfo> classDescription;

    ArrayList<MethodInfo> methodDescription;//Que pasa cuando hay mas de un constructor? overrided methdos

    ArrayList<FieldInfo> fieldDescription;

    //Podrias adaptarlo para pdoer buscar varias clases, metodos y fields. Simplemente aplica lo que has usado para method input en el Check y add un for loop para cada item del query

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
        ArrayList<ClassInfo> classes = new ArrayList<>();
        ArrayList<MethodInfo> methods = new ArrayList<>();
        ArrayList<FieldInfo> fields = new ArrayList<>();
        String className = query.getClassName();
        if (className == null) {
            classes = jarFile.getJarClasses();
        } else {
            if(className.startsWith("Regex: ")){
                classes = findClassByRegex(jarFile, className);
            } else {
                classes= findClassByName(jarFile, className);
            }
            methods = findMethods(classes, query.getMethodName());
            fields = findFields(classes, query.getFieldName());
        }
        return new Description(jarFile, classes, methods, fields);
    }

    private static ArrayList<ClassInfo> findClassByRegex(JarFile jarFile, String className) {
        ArrayList<ClassInfo> classes = new ArrayList<>();
        String regex = className.split(" ", 0)[1];
        Pattern pattern = Pattern.compile(regex);
        for(ClassInfo c : jarFile.getJarClasses()) {
            if(pattern.matcher(c.getName()).find()) {
                classes.add(c);
            }
        }
        return classes;
    }

    private static ArrayList<ClassInfo> findClassByName(JarFile jarFile, String className) {
        ArrayList<ClassInfo> classes = new ArrayList<>();
        for (ClassInfo c : jarFile.getJarClasses()) {
            if (className.equals(c.getName())) {
                classes.add(c);
            }
        }
        return classes;
    }

    private static ArrayList<MethodInfo> findMethods(ArrayList<ClassInfo> classes, String methodName) {
        if(methodName != null && methodName.startsWith("Regex: ")){
            return findMethodsByRegex(classes, methodName);
        } else {
            return findMethodsByName(classes, methodName);
        }
    }

    private static ArrayList<MethodInfo> findMethodsByName(ArrayList<ClassInfo> classes, String methodName) {
        ArrayList<MethodInfo> methods = new ArrayList<>();
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
        }
        return methods;
    }

    private static ArrayList<MethodInfo> findMethodsByRegex(ArrayList<ClassInfo> classes, String methodName) {
        ArrayList<MethodInfo> methods = new ArrayList<>();
        String regex = methodName.split(" ", 0)[1];
        Pattern pattern = Pattern.compile(regex);
        for (MethodInfo m : classes.get(0).getMethods()) {
            if (pattern.matcher(m.getName()).find()) {
                methods.add(m);
            }
        }
        return methods;
    }

    private static ArrayList<FieldInfo> findFields(ArrayList<ClassInfo> classes, String fieldName) {
        if(fieldName != null && fieldName.startsWith("Regex: ")){
            return findFieldsByRegex(classes, fieldName);
        } else {
            return findFieldsByName(classes, fieldName);
        }
    }

    private static ArrayList<FieldInfo> findFieldsByName(ArrayList<ClassInfo> classes, String fieldName) {
        ArrayList<FieldInfo> fields = new ArrayList<>();
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
        return fields;
    }

    private static ArrayList<FieldInfo> findFieldsByRegex(ArrayList<ClassInfo> classes, String fieldName) {
        ArrayList<FieldInfo> fields = new ArrayList<>();
        String regex = fieldName.split(" ", 0)[1];
        Pattern pattern = Pattern.compile(regex);
        for (FieldInfo f : classes.get(0).getFields()) {
            if (pattern.matcher(f.getName()).find()) {
                fields.add(f);
            }
        }
        return fields;
    }
    public Description(JarFile jarFile, ArrayList<ClassInfo> classDescription, ArrayList<MethodInfo> methodDescription, ArrayList<FieldInfo> fieldDescription) {
        this.jarFile = jarFile;
        this.classDescription = classDescription;
        this.methodDescription = methodDescription;
        this.fieldDescription = fieldDescription;
    }
}
