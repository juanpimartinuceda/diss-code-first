package com.shethap.tech.graphql.service;

import com.shethap.tech.graphql.dao.DynamicAnalyzer;
import com.shethap.tech.graphql.model.Query;
import com.shethap.tech.graphql.model.Report;
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
}
