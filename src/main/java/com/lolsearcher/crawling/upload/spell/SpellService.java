package com.lolsearcher.crawling.upload.spell;

import com.lolsearcher.crawling.upload.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpellService implements UploadService {

    private final SpellAPI spellAPI;
    private final SpellRepository spellRepository;

    @Override
    public void upload(String version) {

        spellAPI.findSpells(version).forEach(spellRepository::upload);
    }
}
