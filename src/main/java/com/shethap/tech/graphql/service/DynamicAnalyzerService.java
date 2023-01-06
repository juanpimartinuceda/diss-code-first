package com.shethap.tech.graphql.service;

import com.shethap.tech.graphql.dao.DynamicAnalyzer;
import com.shethap.tech.graphql.model.*;
import org.springframework.stereotype.Service;

@Service
public class DynamicAnalyzerService {

    private DynamicAnalyzer dynamicAnalyzer;

    public DynamicAnalyzerService(DynamicAnalyzer dynamicAnalyzer) {
        this.dynamicAnalyzer = dynamicAnalyzer;
    }

    public Check checkQuery(CheckQuery checkQuery) {
        return dynamicAnalyzer.checkQuery(checkQuery);
    }
    public TestResult testQuery(Test test) { return dynamicAnalyzer.testQuery(test); }

    public Description descriptionQuery(DescriptionQuery query) { return dynamicAnalyzer.descriptionQuery(query);
    }
}
