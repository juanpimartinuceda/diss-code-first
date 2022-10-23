package com.shethap.tech.graphql.dao;

import com.shethap.tech.graphql.model.JarFile;
import com.shethap.tech.graphql.model.Query;
import com.shethap.tech.graphql.model.Report;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class DynamicAnalyzer {

    private ArrayList<Report> reports;

    public DynamicAnalyzer() {
        this.reports = new ArrayList<>();
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
}
