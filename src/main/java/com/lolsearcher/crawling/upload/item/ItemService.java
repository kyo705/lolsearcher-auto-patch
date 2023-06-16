package com.lolsearcher.crawling.upload.item;

import com.lolsearcher.crawling.upload.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemService implements UploadService {

    private final ItemAPI itemAPI;
    private final ItemRepository itemRepository;

    @Override
    public void upload(String version) {

        itemAPI.findItems(version).forEach(itemRepository::upload);
    }
}
