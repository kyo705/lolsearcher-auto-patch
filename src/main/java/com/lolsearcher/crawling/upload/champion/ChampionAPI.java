package com.lolsearcher.crawling.upload.champion;

import java.util.Map;

public interface ChampionAPI {

    Map<Long, String> findChampions(String version);
}
