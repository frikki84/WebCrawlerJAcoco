package com.softeq.repository.entity;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.junit.jupiter.api.Test;

class SearchWordTest {
    private long id = 1L;
    private String searchWord = "search";
    private ParsingRequest request = new ParsingRequest();
    private SearchWord searchEntity = new SearchWord(id, searchWord, request);

    @Test
    void getId() {
        assertEquals(id, searchEntity.getId());
    }

    @Test
    void getSearchWord() {
        assertEquals(searchWord, searchEntity.getSearchWord());
    }

    @Test
    void getRequest() {
        assertEquals(request, searchEntity.getRequest());
    }
}