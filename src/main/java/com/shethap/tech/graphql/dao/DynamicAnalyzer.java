package com.shethap.tech.graphql.dao;

import com.shethap.tech.graphql.inspector.TestRunner;
import com.shethap.tech.graphql.model.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class DynamicAnalyzer {

    private ArrayList<Report> reports;
    private TestRunner testRunner;

    public DynamicAnalyzer() {
        this.reports = new ArrayList<>();
        this.testRunner = new TestRunner();
    }

    public Report dynamicAnalyzerQuery(Query query) { //The report is done just once, then returns what you ask for in the query.
        String jarName = query.getJarName();
        Report report = getReport(jarName);
        switch(query.getQuery()){
            case("is"):
                report.checkQuery(query);
            default:
                return report;
        }
    }

    public Report getReport(String jarName) {
        for (Report r : this.reports) {
            if (r.getJarFile().getJarName().equals(jarName)) {
                return r;
            }
        }
        return(addReport(jarName));
    }

    public Report addReport(String jarName) {
        Report report = new Report(JarFile.getByName(jarName), true);
        this.reports.add(report);
        return report;
    }

    public TestResult testQuery(Test test) {
        return testRunner.runTest(test);
    }
}
