package com.lolsearcher.crawling.crawl;

public class CrawlingConstant {

    public static final String RIOT_DOCS_URI = "https://developer.riotgames.com/docs/lol";
    public static final int TIME_OUT = 10000;
    public static final String LATEST_VER_SELECTOR = "body > div.container > div.layout > div.content-container > div.content > p:nth-child(56) > a";
    public static final String LATEST_VER_ATTR_KEY =  "abs:href";

}
