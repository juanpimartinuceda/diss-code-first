package com.shethap.tech.graphql.api;

import com.shethap.tech.graphql.model.Report;
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

    @GraphQLQuery(name = "getReport", description = "to get a report of the specified file")
    public Report getReport(String jarName) {
        return dynamicAnalyzerService.getReport(jarName);
    }

}
