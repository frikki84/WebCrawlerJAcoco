package com.softeq.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

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
//            if (connection.response().statusCode() == 200) // 200 is the HTTP OK status code
//            // indicating that everything is great.
//            {
//                System.out.println("\n**Visiting** Received web page at " + url);
//            }
            if (!connection.response().contentType().contains("text/html")) {
               // System.out.println("**Failure** Retrieved something other than HTML");
                return false;
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
           // System.out.println("Found (" + linksOnPage.size() + ") links");
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
        // Defensive coding. This method should only be used after a successful crawl.
        long count = 0;
        if (this.htmlDocument == null) {
            //System.out.println("ERROR! Call crawl() before performing analysis on the document");
            return count;
        }
        //System.out.println("Searching for the word " + searchWord + "...");
        String bodyText = this.htmlDocument.body().text().toLowerCase();
        searchWord = searchWord.toLowerCase();
        //if (bodyText.contains(searchWord)) {
        String finalSearchWord = searchWord;
        count = Arrays.stream(bodyText.split("\\W")).filter(str -> str.equals(finalSearchWord)).count();
//            for (String item : list) {
//                if (item.equals(searchWord)) {
//                    count++;
//                }
//            }
    //}
        return count;
    }

    public List<String> getLinks() {
        return this.links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}

