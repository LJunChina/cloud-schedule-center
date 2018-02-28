package com.cloud.base.schedule.job.config;

import org.springframework.beans.factory.InitializingBean;

/**
 * spring boot启动完成后加载定时任务‘
 *
 * @author Jon_China
 * @create 2018/2/28
 */
public class JobBootstrap implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
