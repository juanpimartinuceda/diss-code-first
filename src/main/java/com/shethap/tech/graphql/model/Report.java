package com.shethap.tech.graphql.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class Report {
    JarFile jarFile;

    boolean answer;

    public Report(JarFile jarFile, boolean answer) {
        this.jarFile = jarFile;
        this.answer = answer;
    }

    public void checkQuery(Query query) {
        boolean _answer = true;
        ClassInfo _class = checkClass(query);
        if (_class == null) {
            _answer = false;
        }
        else {
            if (query.get_method() != null) {
                MethodInfo _method = checkMethod(query, _class);
                if (_method == null) {
                    _answer = false;
                }
                else {
                    if (query.get_parameters() != null) {
                        _answer = checkParameters(query, _method);
                    }
                }
            }
        }
        this.answer = _answer;
    }

    private boolean checkParameters(Query query, MethodInfo _method) {
        String[] input = query.get_parameters().split(",", 0);
        ArrayList<String> expectedInput = _method.getInput();
        boolean correctInput = true;
        for (int i=0; i<expectedInput.size(); i++) {
            correctInput = correctInput && input[i].equals(expectedInput.get(i));
        }
        if(query.get_parameters() != null) {
            if (_method.getOutput().equals(query.get_output())) {
                return correctInput;
            }
        }
        return false;
    }

    private MethodInfo checkMethod(Query query, ClassInfo _class) {
        if(query.get_method() != null) {
            for(MethodInfo m: _class.getMethods()) {
                if (m.getName().equals(query.get_method())) {
                    return m;
                }
            }
        }
        return null;
    }

    public ClassInfo checkClass(Query query) {
        if(query.get_class() != null) {
            for(ClassInfo c: jarFile.getJarClasses()) {
                if (c.getName().equals(query.get_class())) {
                    return c;
                }
            }
        }
        return null;
    }
}
