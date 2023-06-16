package com.lolsearcher.crawling.upload.queue;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

import static com.lolsearcher.crawling.upload.queue.QueueConstant.LATEST_QUEUE_DATA_URI;

@RequiredArgsConstructor
@Component
public class WebClientQueueAPI implements QueueAPI {

    private final WebClient webClient;

    @Override
    public Map<Integer, String> findQueues(String version) {

        return webClient.get()
                .uri(LATEST_QUEUE_DATA_URI, version)
                .retrieve()
                .bodyToFlux(JSONObject.class)
                .filter(object -> object.get("description") != null)
                .collectMap(object -> (Integer) object.get("queueId"), object -> (String) object.get("description"))
                .block();
    }
}
