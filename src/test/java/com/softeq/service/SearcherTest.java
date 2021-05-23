package com.softeq.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.softeq.service.entity.SearchParameter;

class SearcherTest {

    @Test
    void search() {
        SearchParameter paramete = new SearchParameter("https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/support/JdbcAccessor.html",
                Arrays.asList("JdbcAccessor"));
        Searcher searcher = new Searcher();

        assertEquals(1, searcher.search(paramete).size());
    }
}