package com.cloud.base.schedule.job.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * bean加载
 *
 * @author Jon_China
 * @create 2018/2/28
 */
@Configuration
public class BeanLoader {

    @Bean
    public SchedulerFactoryBean createSchedule(){
        return new SchedulerFactoryBean();
    }
}
