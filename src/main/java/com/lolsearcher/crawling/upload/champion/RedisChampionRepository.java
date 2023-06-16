package com.lolsearcher.crawling.upload.champion;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import static com.lolsearcher.crawling.utils.RiotGamesDataCacheKeyUtils.getChampionKey;

@RequiredArgsConstructor
@Repository
public class RedisChampionRepository implements ChampionRepository {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void upload(Long key, String value) {

        redisTemplate.opsForValue().append(getChampionKey(key), value);
    }
}
