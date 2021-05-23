package com.softeq.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.softeq.service.entity.PagesForAnalyse;
import com.softeq.service.entity.ParsingEntityDto;
import com.softeq.service.entity.SearchParameter;

/**
 * Bean for parsing. Uses jsoup.
 */
@Service
public class Searcher {

    private static final int MAX_PAGES_TO_SEARCH = 10000;
    private static final int MAX_DEEP_PARSING = 8;

    /**
     * method use list of links, create SearchLine class, which makes parsing on one page,
     * if the parsing is not enough deep or wide method continues to parse other pages
     * and add links to list
     * @param parameter
     * @return
     */
    public List<ParsingEntityDto> search(SearchParameter parameter) {
        PagesForAnalyse pages = new PagesForAnalyse();
        List<ParsingEntityDto> results = new LinkedList<>();

        while (pages.getVisitedPage().size() < MAX_PAGES_TO_SEARCH ) {
            SearchLine searchLine = new SearchLine();
            String currentUrl;
            if (pages.getVisitedPage().isEmpty()) {
                currentUrl = parameter.getUrl();
                pages.getVisitedPage().add(parameter.getUrl());
            } else {
                currentUrl = nextUrl(pages);
            }
            if (searchLine.crawl(currentUrl)) {
                for (String searchWord : parameter.getSearchWord()) {
                    long count = searchLine.searchForWord(searchWord);
                    ParsingEntityDto entity = new ParsingEntityDto();
                    entity.setUrl(currentUrl);
                    entity.setSearchWord(searchWord);
                    entity.setCount(count);
                    results.add(entity);

                    if (pages.getPagesToVisitNext().size() < MAX_PAGES_TO_SEARCH) {
                        pages.getPagesToVisitNext().addAll(searchLine.getLinks());
                    }
                }
            }
        }
        return results;
    }

    /**
     * method make next step by link-list
     * @param pages
     * @return
     */
    private String nextUrl(PagesForAnalyse pages) {
        String nextUrl = null;
        do {
            nextUrl = pages.getPagesToVisitNext().remove(0);
        } while (pages.getVisitedPage().contains(nextUrl));
        pages.getVisitedPage().add(nextUrl);
        return nextUrl;
    }


}
