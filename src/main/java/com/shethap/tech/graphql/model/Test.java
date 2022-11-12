package com.shethap.tech.graphql.model;

public class Test {
    private String jarName;
    private String className;
    private String methodName;
    private Object[] constructorArguments;
    private Object[] methodArguments;
    private Object expectedOutput;

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getConstructorArguments() {
        return constructorArguments;
    }

    public void setConstructorArguments(Object[] constructorArguments) {
        this.constructorArguments = constructorArguments;
    }

    public Object[] getMethodArguments() {
        return methodArguments;
    }

    public void setMethodArguments(Object[] methodArguments) {
        this.methodArguments = methodArguments;
    }

    public Object getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(Object expectedOutput) {
        this.expectedOutput = expectedOutput;
    }
}
