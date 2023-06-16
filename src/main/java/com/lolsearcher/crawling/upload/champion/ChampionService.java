package com.lolsearcher.crawling.upload.champion;

import com.lolsearcher.crawling.upload.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChampionService implements UploadService {

    private final ChampionAPI championAPI;
    private final ChampionRepository championRepository;

    @Override
    public void upload(String version) {

        championAPI.findChampions(version).forEach(championRepository::upload);
    }
}
