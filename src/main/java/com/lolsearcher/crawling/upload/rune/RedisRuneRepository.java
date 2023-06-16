package com.lolsearcher.crawling.upload.rune;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import static com.lolsearcher.crawling.utils.RiotGamesDataCacheKeyUtils.getRuneKey;

@RequiredArgsConstructor
@Repository
public class RedisRuneRepository implements RuneRepository {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void upload(Integer key, String value) {

        redisTemplate.opsForValue().append(getRuneKey(key), value);
    }
}
