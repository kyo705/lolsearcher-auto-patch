package com.lolsearcher.crawling.upload.spell;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import static com.lolsearcher.crawling.utils.RiotGamesDataCacheKeyUtils.getSpellKey;

@RequiredArgsConstructor
@Repository
public class RedisSpellRepository implements SpellRepository {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void upload(Integer key, String value) {

        redisTemplate.opsForValue().append(getSpellKey(key), value);
    }
}
