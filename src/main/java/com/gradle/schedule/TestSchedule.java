package com.gradle.schedule;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnProperty(prefix = "schedule.cron",value = "test")
public class TestSchedule {


    @Scheduled(cron ="${schedule.cron.test}")
    public void runSchedule(){
          log.info("running");
    }
}
