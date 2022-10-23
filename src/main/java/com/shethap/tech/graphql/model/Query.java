package com.shethap.tech.graphql.model;

public class Query {
    String jarName;
    String query;
    String _class;
    String _method;
    String _parameters;

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public String get_method() {
        return _method;
    }

    public void set_method(String _method) {
        this._method = _method;
    }

    public String get_parameters() {
        return _parameters;
    }

    public void set_parameters(String _parameters) {
        this._parameters = _parameters;
    }
}
