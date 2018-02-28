package com.cloud.base.schedule.job.config;

import com.cloud.base.schedule.job.exception.QuartzTaskException;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 调度任务管理
 *
 * @author Jon_China
 * @create 2018/2/27
 */
public class QuartzManager {

    private static final Logger logger = LoggerFactory.getLogger(QuartzManager.class);
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    /**
     * 添加定时任务
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     * @param jobClass
     * @param cron
     */
    public static void addJob(String jobName, String jobGroupName,
                              String triggerName, String triggerGroupName, Class jobClass, String cron) {
        try {
            Scheduler sched = schedulerFactory.getScheduler();
            // 任务名，任务组，任务执行类
            JobDetail jobDetail= JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();
            // 调度容器设置JobDetail和Trigger
            sched.scheduleJob(jobDetail, trigger);
            // 启动
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            logger.error("exception occurred in add job:",e);
            throw new QuartzTaskException(e);
        }
    }
}
