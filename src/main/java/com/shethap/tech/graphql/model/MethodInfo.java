package com.shethap.tech.graphql.model;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class MethodInfo {
    String name;
    String output;
    ArrayList<String> input;
    boolean isPublic;

    public MethodInfo(Method m) {
        this.name = m.getName();
        this.output = m.getGenericReturnType().getTypeName();
        Class[] inputs = m.getParameterTypes();
        ArrayList<String> inputInfo = new ArrayList<>();
        for (Class i: inputs) {
            inputInfo.add(i.getName());
        }
        this.input = inputInfo;
        this.isPublic = Modifier.isPublic(m.getModifiers());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public ArrayList<String> getInput() {
        return input;
    }

    public void setInput(ArrayList<String> input) {
        this.input = input;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
