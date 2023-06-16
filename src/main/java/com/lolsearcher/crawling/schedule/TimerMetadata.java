package com.lolsearcher.crawling.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimerMetadata {

    private int repeatCnt;
    private boolean runForever;
    private long repeatIntervalMs;
    private long initOffsetMs;
    private Object data;
}
