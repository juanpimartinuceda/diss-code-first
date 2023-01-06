package com.shethap.tech.graphql.model;

public class CheckQuery {
    String jarName;
    String className;
    String methodName;
    String parametersType;
    String outputType;

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

    public String getParametersType() {
        return parametersType;
    }

    public void setParametersType(String parametersType) {
        this.parametersType = parametersType;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }
}
