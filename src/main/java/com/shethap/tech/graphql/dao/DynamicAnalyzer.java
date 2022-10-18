package com.shethap.tech.graphql.dao;

import com.shethap.tech.graphql.model.JarFile;
import com.shethap.tech.graphql.model.Report;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class DynamicAnalyzer {

    private ArrayList<Report> reports;

    public DynamicAnalyzer() {
        this.reports = new ArrayList<>();
    }

    public Report getReport(String jarName) { //The report is done just once, then returns what you ask for in the query.
        while (true) {
        for (Report r : this.reports) {
            if (r.getJarFile().getJarName().equals(jarName)) {
                return r;
            }
        }
        addReport(jarName);
        }
    }

    public void addReport(String jarName) {
        this.reports.add(new Report(JarFile.getByName(jarName)));
    }
}
