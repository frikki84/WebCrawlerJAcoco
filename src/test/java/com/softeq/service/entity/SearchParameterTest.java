package com.softeq.service.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class SearchParameterTest {
    private String url = "url";
    private List<String> searchWord = new ArrayList<>();
    private SearchParameter parameter = new SearchParameter(url, searchWord);

    @Test
    void getUrl() {
        assertEquals(url, parameter.getUrl());
    }

    @Test
    void getSearchWord() {
        assertEquals(searchWord, parameter.getSearchWord());
    }
}