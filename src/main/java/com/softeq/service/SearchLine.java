package com.softeq.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

/**
 * Class SearchLine for parsing one page by given link
 */
@Component
public class SearchLine {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links = new LinkedList<String>();
    private Document htmlDocument;

    /**
     * This performs all the work. It makes an HTTP request, checks the response, and then gathers
     * up all the links on the page. Perform a searchForWord after the successful crawl
     *
     * @param url - The URL to visit
     * @return whether or not the crawl was successful
     */
    public boolean crawl(String url) {
        try {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;
            if (!connection.response().contentType().contains("text/html")) {
                return false;
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
            for (Element link : linksOnPage) {
                this.links.add(link.absUrl("href"));
            }
            return true;
        } catch (Exception io) {
            // We were not successful in our HTTP request
            return false;
        }
    }

    /**
     * Performs a search on the body of on the HTML document that is retrieved. This method should
     * only be called after a successful crawl.
     *
     * @param searchWord - The word or string to look for
     * @return whether or not the word was found
     */
    public long searchForWord(String searchWord) {
        long count = 0;

        if (Objects.isNull(this.htmlDocument) || Objects.isNull(this.htmlDocument.body())) {
            return count;
        }
        String bodyText = this.htmlDocument.body().text().toLowerCase();
        searchWord = searchWord.toLowerCase();
        String finalSearchWord = searchWord;
        count = Arrays.stream(bodyText.split("\\W")).filter(str -> str.equals(finalSearchWord)).count();
        return count;
    }

    public List<String> getLinks() {
        return this.links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}

