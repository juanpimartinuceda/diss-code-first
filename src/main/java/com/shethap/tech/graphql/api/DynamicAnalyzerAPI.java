package com.shethap.tech.graphql.api;

import com.shethap.tech.graphql.model.Query;
import com.shethap.tech.graphql.model.Report;
import com.shethap.tech.graphql.model.Test;
import com.shethap.tech.graphql.model.TestResult;
import com.shethap.tech.graphql.service.DynamicAnalyzerService;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Component;


@GraphQLApi
@Component
public class DynamicAnalyzerAPI {

    DynamicAnalyzerService dynamicAnalyzerService;

    public DynamicAnalyzerAPI(DynamicAnalyzerService dynamicAnalyzerService) {
        this.dynamicAnalyzerService = dynamicAnalyzerService;
    }

    @GraphQLQuery(name = "dynamicAnalyzerQuery", description = "to get a report of the specified file")
    public Report dynamicAnalyzerQuery(Query query) {
        return dynamicAnalyzerService.dynamicAnalyzerQuery(query);
    }

    @GraphQLQuery(name = "testQuery", description = "test a specific method")
    public TestResult testQuery(Test test) {
        return dynamicAnalyzerService.testQuery(test);
    }
}
