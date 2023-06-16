package com.lolsearcher.crawling.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.lolsearcher.crawling.schedule.TimerUtils.buildJobDetail;
import static com.lolsearcher.crawling.schedule.TimerUtils.buildTrigger;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final Scheduler scheduler;

    @PostConstruct
    public void init() throws SchedulerException {

        log.info("scheduler starts!!");
        scheduler.start();
    }

    @PreDestroy
    public void preDestroy() throws SchedulerException {

        log.info("scheduler shutdowns");
        scheduler.shutdown();
    }

    @PostConstruct
    public void schedule() throws SchedulerException {

        TimerMetadata timer = TimerMetadata.builder()
                .runForever(true)
                .repeatIntervalMs(1000*60*60*24)
                .build();

        JobDetail jobDetail = buildJobDetail(UpdatingDocsJob.class, timer);
        Trigger trigger = buildTrigger(UpdatingDocsJob.class, timer);

        scheduler.scheduleJob(jobDetail, trigger);
    }

}
