package com.lolsearcher.crawling.schedule;

import org.quartz.*;

import java.util.Date;

public class TimerUtils {

    private TimerUtils() {}

    public static JobDetail buildJobDetail(final Class JobClass, final TimerMetadata timer) {

        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(JobClass.getSimpleName(), timer);

        return JobBuilder
                .newJob(JobClass)
                .withIdentity(JobClass.getSimpleName())
                .setJobData(jobDataMap)
                .build();
    }

    public static Trigger buildTrigger(final Class jobClass, final TimerMetadata timer) {

        SimpleScheduleBuilder simpleScheduleBuilder
                = SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(timer.getRepeatIntervalMs());

        if(timer.isRunForever()) {
            simpleScheduleBuilder.repeatForever();
        }else {
            simpleScheduleBuilder.withRepeatCount(timer.getRepeatCnt());
        }

        return TriggerBuilder
                .newTrigger()
                .withIdentity(jobClass.getSimpleName())
                .withSchedule(simpleScheduleBuilder)
                .startAt(new Date(System.currentTimeMillis() + timer.getInitOffsetMs()))
                .build();
    }
}
