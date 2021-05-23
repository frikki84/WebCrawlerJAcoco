package com.softeq.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SearchLineTest {

    private String url = "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html";
    private String wrongUrl = "https://habr1212121.com/ru/company/haulmont/blog/454970/";
    private String searchWord = "JdbcAccessor";

    @InjectMocks
    private SearchLine searchLine;

    @Test
    void crawl() {
        assertTrue(searchLine.crawl(url));
    }

    @Test
    void crawlNegative() {
        assertFalse(searchLine.crawl(wrongUrl));
    }

    @Test
    void searchForWord() {
        SearchLine searchLine = new SearchLine();
        searchLine.crawl(url);
        assertEquals(6, searchLine.searchForWord(searchWord));

    }

    @Test
    void getLinks() {
    }
}