package com.softeq.repository.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.junit.jupiter.api.Test;

class ParsingRequestTest {
    private long id = 1l;
    private LocalDateTime creationTime = LocalDateTime.now();
    private String searchUrl = "url";
    private List<SearchWord> searchWords =new ArrayList<>();
    ParsingRequest request = new ParsingRequest(id, creationTime, searchUrl, searchWords);
    @Test
    void getId() {
        assertEquals(id, request.getId());
    }

    @Test
    void getCreationTime() {
        assertEquals(creationTime, request.getCreationTime());
    }

    @Test
    void getSearchUrl() {
        assertEquals(searchUrl, request.getSearchUrl());
    }

    @Test
    void getSearchWords() {
        assertEquals(searchWords, request.getSearchWords());
    }
}