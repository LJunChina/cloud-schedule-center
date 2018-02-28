package com.cloud.base.schedule.job.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 远程调用定时任务
 *
 * @author Jon_China
 * @create 2018/2/28
 */
public class RemoteJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("------------远程调用任务执行------------");
    }
}
