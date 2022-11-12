package com.shethap.tech.graphql.service;

import com.shethap.tech.graphql.dao.DynamicAnalyzer;
import com.shethap.tech.graphql.model.Query;
import com.shethap.tech.graphql.model.Report;
import com.shethap.tech.graphql.model.Test;
import com.shethap.tech.graphql.model.TestResult;
import org.springframework.stereotype.Service;

@Service
public class DynamicAnalyzerService {

    private DynamicAnalyzer dynamicAnalyzer;

    public DynamicAnalyzerService(DynamicAnalyzer dynamicAnalyzer) {
        this.dynamicAnalyzer = dynamicAnalyzer;
    }

    public Report dynamicAnalyzerQuery(Query query) {
        return dynamicAnalyzer.dynamicAnalyzerQuery(query);
    }
    public TestResult testQuery(Test test) { return dynamicAnalyzer.testQuery(test); }
}
