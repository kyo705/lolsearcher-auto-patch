package com.lolsearcher.crawling.upload.item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import static com.lolsearcher.crawling.utils.RiotGamesDataCacheKeyUtils.getItemKey;

@RequiredArgsConstructor
@Repository
public class RedisItemRepository implements ItemRepository {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void upload(Long key, String value) {

        redisTemplate.opsForValue().append(getItemKey(key), value);
    }
}
