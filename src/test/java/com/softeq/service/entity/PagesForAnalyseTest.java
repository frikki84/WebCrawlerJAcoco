package com.softeq.service.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class PagesForAnalyseTest {

    private Set<String> visitedPage = new HashSet<>();
    private List<String> pagesToVisitNext = new ArrayList<>();

    PagesForAnalyse pagesForAnalyse = new PagesForAnalyse(visitedPage, pagesToVisitNext);
    @Test
    void getVisitedPage() {
        assertEquals(visitedPage, pagesForAnalyse.getVisitedPage());
    }

    @Test
    void getPagesToVisitNext() {
        assertEquals(pagesToVisitNext, pagesForAnalyse.getPagesToVisitNext());
    }
}