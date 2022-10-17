package com.shethap.tech.graphql.dao;

import com.shethap.tech.graphql.model.JarFile;
import com.shethap.tech.graphql.model.Report;
import org.springframework.stereotype.Repository;

@Repository
public class DynamicAnalyzer {

    private Report report;

    public DynamicAnalyzer() {
        this.report = new Report(JarFile.getByName("ilp"));
    }

    public Report getReport(String jarName) {
        return this.report;
    }
}
