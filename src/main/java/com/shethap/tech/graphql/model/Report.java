package com.shethap.tech.graphql.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Report {
    JarFile jarFile;

    public Report(JarFile jarFile) {
        this.jarFile = jarFile;
    }
}
