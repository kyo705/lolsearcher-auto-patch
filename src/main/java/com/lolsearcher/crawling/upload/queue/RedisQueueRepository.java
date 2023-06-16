package com.lolsearcher.crawling.upload.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import static com.lolsearcher.crawling.utils.RiotGamesDataCacheKeyUtils.getQueueKey;

@RequiredArgsConstructor
@Repository
public class RedisQueueRepository implements QueueRepository {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void upload(Integer key, String value) {

        redisTemplate.opsForValue().append(getQueueKey(key), value);
    }
}
