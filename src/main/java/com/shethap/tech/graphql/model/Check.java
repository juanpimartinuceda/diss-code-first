package com.shethap.tech.graphql.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class Check {
    JarFile jarFile;
    boolean answer;

    public Check(JarFile jarFile, boolean answer) {
        this.jarFile = jarFile;
        this.answer = answer;
    }

    public void checkQuery(CheckQuery checkQuery) {
        boolean _answer = true;
        ClassInfo _class = checkClass(checkQuery);
        if (_class == null) {
            _answer = false;
        }
        else {
            if (checkQuery.getMethodName() != null) {
                MethodInfo _method = checkMethod(checkQuery, _class);
                if (_method == null) {
                    _answer = false;
                }
                else {
                    if (checkQuery.getParametersType() != null) {
                        _answer = checkParameters(checkQuery, _method);
                    }
                }
            }
        }
        this.answer = _answer;
    }

    private boolean checkParameters(CheckQuery checkQuery, MethodInfo _method) {
        String[] input = checkQuery.getParametersType().split(",", 0);
        ArrayList<String> expectedInput = _method.getInput();
        boolean correctInput = true;
        for (int i=0; i<expectedInput.size(); i++) {
            correctInput = correctInput && input[i].equals(expectedInput.get(i));
        }
        if(checkQuery.getOutputType() != null) {
            if (!_method.getOutput().equals(checkQuery.getOutputType())) {
                return false;
            }
        }
        return correctInput;
    }

    private MethodInfo checkMethod(CheckQuery checkQuery, ClassInfo _class) {
        if(checkQuery.getMethodName() != null) {
            for(MethodInfo m: _class.getMethods()) {
                if (m.getName().equals(checkQuery.getMethodName())) {
                    return m;
                }
            }
        }
        return null;
    }

    public ClassInfo checkClass(CheckQuery checkQuery) {
        if(checkQuery.getClassName() != null) {
            for(ClassInfo c: jarFile.getJarClasses()) {
                if (c.getName().equals(checkQuery.getClassName())) {
                    return c;
                }
            }
        }
        return null;
    }
}
