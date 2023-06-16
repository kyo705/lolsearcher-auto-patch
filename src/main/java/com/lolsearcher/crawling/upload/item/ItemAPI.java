package com.lolsearcher.crawling.upload.item;

import java.util.Map;

public interface ItemAPI {

    Map<Long, String> findItems(String version);
}
