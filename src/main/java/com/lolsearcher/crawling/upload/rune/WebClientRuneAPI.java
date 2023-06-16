package com.lolsearcher.crawling.upload.rune;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lolsearcher.crawling.upload.rune.RuneConstant.LATEST_RUNE_DATA_URI;

@RequiredArgsConstructor
@Component
public class WebClientRuneAPI implements RuneAPI {

    private final WebClient webClient;

    @Override
    public Map<Integer, String> findRunes(String version) {

        Map<Integer, String> answer = new HashMap<>();

        List list = webClient.get()
                .uri(LATEST_RUNE_DATA_URI, version)
                .retrieve()
                .bodyToMono(List.class)
                .block();

        list.forEach(map -> answer.put((Integer)((Map)map).get("id"),(String)((Map)map).get("name")));

        list.stream()
                .flatMap(map -> ((List)((Map)map).get("slots")).stream())
                .flatMap(map -> ((List)((Map)map).get("runes")).stream())
                .forEach(map -> answer.put((Integer)((Map)map).get("id"),(String)((Map)map).get("name")));

        return answer;
    }
}
