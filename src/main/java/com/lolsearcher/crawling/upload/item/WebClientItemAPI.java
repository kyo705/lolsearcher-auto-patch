package com.lolsearcher.crawling.upload.item;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

import static com.lolsearcher.crawling.upload.item.ItemConstant.LATEST_ITEM_DATA_URI;

@RequiredArgsConstructor
@Component
public class WebClientItemAPI implements ItemAPI {

    private final WebClient webClient;

    @Override
    public Map<Long, String> findItems(String version) {

        return webClient.get()
                .uri(LATEST_ITEM_DATA_URI, version)
                .retrieve()
                .bodyToMono(JSONObject.class)
                .flatMapIterable(obj -> ((Map<String, Map>)obj.get("data")).entrySet())
                .collectMap(entry -> Long.parseLong(entry.getKey()), entry -> (String) entry.getValue().get("name"))
                .block();
    }
}
