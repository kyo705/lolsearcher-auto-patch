package com.lolsearcher.crawling.upload.queue;

public interface QueueRepository {

    void upload(Integer key, String value);
}
