package com.softeq.service.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.softeq.repository.entity.ParsingRequest;

class ParsingEntityDtoTest {
    private long id = 1L;
    private String url = "url";
    private String searchWord = "search";
    private long count = 1L;
    private ParsingRequest request = new ParsingRequest();

    ParsingEntityDto dto = new ParsingEntityDto(id, url, searchWord, count, request);

    @Test
    void getId() {
        assertEquals(id, dto.getId());
    }

    @Test
    void getUrl() {
        assertEquals(url, dto.getUrl());
    }

    @Test
    void getSearchWord() {
        assertEquals(searchWord, dto.getSearchWord());
    }

    @Test
    void getCount() {
        assertEquals(count, dto.getCount());
    }

    @Test
    void getRequest() {
        assertEquals(request, dto.getRequest());
    }
}