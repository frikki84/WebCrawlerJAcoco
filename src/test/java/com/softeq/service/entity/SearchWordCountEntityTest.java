package com.softeq.service.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SearchWordCountEntityTest {
    private String searchWord = "s";
    private long count = 1L;
    private SearchWordCountEntity entity = new SearchWordCountEntity(searchWord, count);

    @Test
    void getSearchWord() {
        assertEquals(searchWord, entity.getSearchWord());

    }

    @Test
    void getCount() {
        assertEquals(count, entity.getCount());
    }
}