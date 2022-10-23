package com.shethap.tech.graphql.model;

import lombok.Builder;
import lombok.Data;

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
        ClassInfo _class = null;
        MethodInfo _method = null;
        boolean _answer = true;

        if(query.get_class() != null) {
            for(ClassInfo c: jarFile.getJarClasses()) {
                if (c.getName().equals(query.get_class())) {
                    _answer = true;
                    _class = c;
                    break;
                }
                else {
                    _answer = false;
                }
            }
        }
        if(query.get_method() != null && _answer) {
            for(MethodInfo m: _class.getMethods()) {
                if (m.getName().equals(query.get_method())) {
                    _answer = true;
                    _method = m;
                    break;
                }
                else {
                    _answer = false;
                }
            }
        }
        if(query.get_parameters() != null && _answer) {
            if (_method.getOutput().equals(query.get_parameters())) {
                _answer = true;
            }
            else {
                _answer = false;
            }
        }
        this.answer = _answer;
    }
}
