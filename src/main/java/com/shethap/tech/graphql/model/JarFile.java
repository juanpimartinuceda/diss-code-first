package com.shethap.tech.graphql.model;

import com.shethap.tech.graphql.inspector.GetClassesFromJar;

import java.util.ArrayList;

public class JarFile {
    String jarName;
    ArrayList<ClassInfo> jarClasses;

    public JarFile(String jarName, ArrayList<ClassInfo> jarClasses) {
        this.jarName = jarName;
        this.jarClasses = jarClasses;
    }

    public String getJarName() {
        return jarName;
    }

    public ArrayList<ClassInfo> getJarClasses() {
        return jarClasses;
    }

    public static JarFile getByName (String name) {
        GetClassesFromJar jarLoader = new GetClassesFromJar();
        ArrayList<ClassInfo> classesInfo = new ArrayList<>();
        String jarPath = name + ".jar";
        try {
            ArrayList<Class> classes = jarLoader.loadJarFile(jarPath);
            for (Class c: classes) {
                classesInfo.add(new ClassInfo(c));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JarFile jar = new JarFile(name, classesInfo);
        return jar;
    }
}
