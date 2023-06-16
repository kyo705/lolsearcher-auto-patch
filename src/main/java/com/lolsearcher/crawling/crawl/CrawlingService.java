package com.lolsearcher.crawling.crawl;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.lolsearcher.crawling.crawl.CrawlingConstant.*;
import static java.util.Objects.requireNonNull;

@Slf4j
@Service
public class CrawlingService {

    public String getLatestDataVersion() {

        try {
            Document document = Jsoup.connect(RIOT_DOCS_URI).timeout(TIME_OUT).get();
            Element element = document.selectFirst(LATEST_VER_SELECTOR);

            return requireNonNull(element).attr(LATEST_VER_ATTR_KEY);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
