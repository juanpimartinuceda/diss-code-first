package com.shethap.tech.graphql.model;

public class TestResult {
    private Object methodOutput;
    private Object expectedOutput;
    private boolean result;

    public TestResult(Object methodOutput, Object expectedOutput, boolean result) {
        this.methodOutput = methodOutput;
        this.expectedOutput = expectedOutput;
        this.result = result;
    }

    public Object getMethodOutput() {
        return methodOutput;
    }

    public void setMethodOutput(Object methodOutput) {
        this.methodOutput = methodOutput;
    }

    public Object getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(Object expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
