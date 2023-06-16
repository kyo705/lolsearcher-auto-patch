package com.lolsearcher.crawling.upload.spell;

import java.util.Map;

public interface SpellAPI {

    Map<Integer, String> findSpells(String version);
}
