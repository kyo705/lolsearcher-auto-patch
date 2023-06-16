package com.lolsearcher.crawling.upload.queue;

import com.lolsearcher.crawling.upload.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueueService implements UploadService {

    private final QueueAPI queueAPI;
    private final QueueRepository queueRepository;

    @Override
    public void upload(String version) {

        queueAPI.findQueues(version).forEach(queueRepository::upload);
    }
}
