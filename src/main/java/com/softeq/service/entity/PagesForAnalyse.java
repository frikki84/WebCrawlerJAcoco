package com.softeq.service.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagesForAnalyse {

    private Set<String> visitedPage;
    private List<String> pagesToVisitNext;

    public PagesForAnalyse() {
        this.visitedPage = new HashSet<>();
        this.pagesToVisitNext = new ArrayList<>();
    }

}
