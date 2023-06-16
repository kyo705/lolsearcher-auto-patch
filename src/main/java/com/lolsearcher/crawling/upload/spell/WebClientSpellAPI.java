package com.lolsearcher.crawling.upload.spell;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

import static com.lolsearcher.crawling.upload.spell.SpellConstant.LATEST_SUMMONER_SPELL_DATA_URI;

@RequiredArgsConstructor
@Component
public class WebClientSpellAPI implements SpellAPI {

    private final WebClient webClient;

    @Override
    public Map<Integer, String> findSpells(String version) {

        return webClient.get()
                .uri(LATEST_SUMMONER_SPELL_DATA_URI, version)
                .retrieve()
                .bodyToMono(JSONObject.class)
                .flatMapIterable(obj -> ((Map<String, Map>)obj.get("data")).entrySet())
                .collectMap(entry -> Integer.parseInt((String) entry.getValue().get("key")), Map.Entry::getKey)
                .block();
    }
}
