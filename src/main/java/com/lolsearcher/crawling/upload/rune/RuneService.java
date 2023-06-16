package com.lolsearcher.crawling.upload.rune;

import com.lolsearcher.crawling.upload.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RuneService implements UploadService {

    private final RuneAPI runeAPI;
    private final RuneRepository runeRepository;

    @Override
    public void upload(String version) {

        runeAPI.findRunes(version).forEach(runeRepository::upload);
    }
}
