package com.lolsearcher.crawling.schedule;


import com.lolsearcher.crawling.crawl.CrawlingService;
import com.lolsearcher.crawling.upload.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.lolsearcher.crawling.utils.RiotGamesDataCacheKeyUtils.getLatestDataVersionKey;

@Slf4j
@RequiredArgsConstructor
@Component
public class CrawlingJob implements Job {

    private final StringRedisTemplate redisTemplate;
    private final CrawlingService crawlingService;
    private final List<UploadService> uploadServices;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        log.info("Job : {} starts", this.getClass().getSimpleName());

        //크롤링
        String latestVersionUrl = crawlingService.getLatestDataVersion();
        int startIdx = latestVersionUrl.indexOf("dragontail-") + "dragontail-".length();
        int endIdx = latestVersionUrl.indexOf(".tgz");

        String latestVersion = latestVersionUrl.substring(startIdx, endIdx);
        String beforeLatestVersion = redisTemplate.opsForValue().get(getLatestDataVersionKey());

        //크롤링한 데이터가 최신 데이터이면 갱신
        if(beforeLatestVersion == null || !beforeLatestVersion.equals(latestVersion)) {
            uploadServices.forEach(uploadService -> uploadService.upload(latestVersion));
            redisTemplate.opsForValue().set(getLatestDataVersionKey(), latestVersion);
        }

        log.info("Job : {} is terminate successfully", this.getClass().getSimpleName());
    }
}
