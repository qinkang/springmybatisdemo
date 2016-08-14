package com.qingofun.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Created by qinkang on 16/6/24.
 */
@Repository("quartzTestJob")
//@Scope("prototype")
public class QuartzTestJob {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private long count = 0;

    public void executeJob() {
        try {
            Thread.sleep(1000L);
            logger.info("Spring Quartz Test " + count++);
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
