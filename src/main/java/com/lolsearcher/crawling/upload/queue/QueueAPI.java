package com.lolsearcher.crawling.upload.queue;

import java.util.Map;

public interface QueueAPI {

    Map<Integer, String> findQueues(String version);
}
