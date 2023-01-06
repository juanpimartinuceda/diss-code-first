package com.shethap.tech.graphql.dao;

import com.shethap.tech.graphql.inspector.TestRunner;
import com.shethap.tech.graphql.model.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class DynamicAnalyzer {

    private ArrayList<Check> checks;
    private TestRunner testRunner;

    public DynamicAnalyzer() {
        this.testRunner = new TestRunner();
    }

    public Check checkQuery(CheckQuery checkQuery) { //The report is done just once, then returns what you ask for in the query.
        String jarName = checkQuery.getJarName();
        Check check = new Check(JarFile.getByName(jarName), true);
        check.checkQuery(checkQuery);
        return check;
    }

    public TestResult testQuery(Test test) {
        return testRunner.runTest(test);
    }

    public Description descriptionQuery(DescriptionQuery query) {
        String jarName = query.getJarName();
        Description description = Description.getDescription(JarFile.getByName(jarName), query);
        return description;
    }
}
