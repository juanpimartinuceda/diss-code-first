package com.shethap.tech.graphql.api;

import com.shethap.tech.graphql.model.*;
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

    @GraphQLQuery(name = "checkQuery", description = "to check if a code feature is implemented")
    public Check checkQuery(CheckQuery dataDefinition) {
        return dynamicAnalyzerService.checkQuery(dataDefinition);
    }

    @GraphQLQuery(name = "testQuery", description = "test a specific method")
    public TestResult testQuery(Test test) {
        return dynamicAnalyzerService.testQuery(test);
    }

    @GraphQLQuery(name = "descriptionQuery", description = "to get a description of an implementation feature")
    public Description descriptionQuery(DescriptionQuery dataDefinition) {
        return dynamicAnalyzerService.descriptionQuery(dataDefinition);
    }
}
