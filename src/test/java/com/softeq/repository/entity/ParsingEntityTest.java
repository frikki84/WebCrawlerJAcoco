package com.softeq.repository.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ParsingEntityTest {

    private long id = 1l;
    private String url = "url";
    private String searchWord = "searckWord";
    private long count=1l;
    private ParsingRequest parsingRequest = new ParsingRequest();

    private ParsingEntity parsingEntity = new ParsingEntity(id, url, searchWord, count, parsingRequest);

    @Test
    void getId() {
        assertEquals(id, parsingEntity.getId());
    }

    @Test
    void getUrl() {
        assertEquals(url, parsingEntity.getUrl());
    }

    @Test
    void getSearchWord() {
        assertEquals(searchWord, parsingEntity.getSearchWord());
    }

    @Test
    void getCount() {
        assertEquals(count, parsingEntity.getCount());
    }

    @Test
    void getParsingRequest() {
        assertEquals(parsingRequest, parsingEntity.getParsingRequest());
    }

}