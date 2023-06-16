package com.lolsearcher.crawling.upload.champion;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

import static com.lolsearcher.crawling.upload.champion.ChampionConstant.LATEST_CHAMPION_DATA_URI;

@RequiredArgsConstructor
@Component
public class WebClientChampionAPI implements ChampionAPI {

    private final WebClient webClient;

    @Override
    public Map<Long, String> findChampions(String version) {

        return webClient.get()
                .uri(LATEST_CHAMPION_DATA_URI, version)
                .retrieve()
                .bodyToMono(JSONObject.class)
                .flatMapIterable(obj -> ((Map<String, Map>)obj.get("data")).entrySet())
                .collectMap(entry -> Long.parseLong((String) entry.getValue().get("key")), Map.Entry::getKey)
                .block();
    }
}
