package com.softeq.service.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvEntityTest {

    @Test
    void compareTo() {
        CsvEntity csvEntity = new CsvEntity();
        CsvEntity csvEntity2 = new CsvEntity();
        csvEntity.setFullCount(20);
        csvEntity2.setFullCount(30);
        assertEquals(-1, csvEntity.compareTo(csvEntity2));
    }

    @Test
    void getUrl() {
        String url = "http";
        CsvEntity csvEntity = new CsvEntity();
        csvEntity.setUrl(url);
        assertEquals(url, csvEntity.getUrl());
    }

    @Test
    void getFullCount() {
        long count = 1000;
        CsvEntity csvEntity = new CsvEntity();
        csvEntity.setFullCount(count);
        assertEquals(count, csvEntity.getFullCount());
    }
}